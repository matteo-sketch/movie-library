package com.movielibrary.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movielibrary.dto.MovieDto;
import com.movielibrary.dto.MovieSearchResponseDto;
import com.movielibrary.service.JsonParser;
import com.movielibrary.util.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
//import java.util.Collections;
import java.util.List;

/**
 * Client per interagire con l'API di The Movie Database (TMDB).
 * Questa classe gestisce la costruzione delle richieste API, l'invio delle stesse
 * e il parsing delle risposte per ottenere informazioni sui film.
 */
public class TmdbApiClient {

    private static final Logger logger = LoggerFactory.getLogger(TmdbApiClient.class);
    private static final String API_BASE_URL = "https://api.themoviedb.org/3";

    private final String apiKey;
    private final HttpClient httpClient;
    private final JsonParser jsonParser;

    /**
     * Costruisce un nuovo client per l'API di TMDB.
     * Inizializza la chiave API, il client HTTP e il parser JSON.
     * La chiave API viene recuperata dal file di configurazione.
     */
    public TmdbApiClient() {
        this.apiKey = ConfigurationManager.getInstance().getProperty("tmdb.api.key");
        this.httpClient = HttpClient.newHttpClient();
        this.jsonParser = new JsonParser();
    }

    /**
     * Cerca film utilizzando una query di testo.
     *
     * @param query Il termine di ricerca per i film.
     * @return Una lista di oggetti {@link MovieDto} che corrispondono alla ricerca.
     * @throws ApiClientException Se si verifica un errore durante la comunicazione con l'API
     *                            o durante il parsing della risposta.
     */
    public List<MovieDto> searchMovies(String query) throws ApiClientException {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String endpoint = "/search/movie";
        String uriString = String.format("%s%s?api_key=%s&query=%s", API_BASE_URL, endpoint, apiKey, encodedQuery);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uriString))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            logger.info("Invio richiesta GET a: {}", uriString);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("Errore API TMDB. Status: {}, Body: {}", response.statusCode(), response.body());
                throw new ApiClientException("Errore API: TMDB ha risposto con codice di stato " + response.statusCode());
            }

            logger.debug("Risposta ricevuta, avvio parsing del JSON.");
            MovieSearchResponseDto searchResponse = jsonParser.parseMovieSearchResponse(response.body());
            return searchResponse.getResults();

        } catch (JsonProcessingException e) {
            logger.error("Impossibile parsare la risposta JSON da TMDB.", e);
            throw new ApiClientException("Formato della risposta da TMDB non valido.", e);
        } catch (Exception e) {
            logger.error("Errore imprevisto durante la chiamata API a TMDB.", e);
            throw new ApiClientException("Fallimento della comunicazione con l'API di TMDB.", e);
        }
    }
}
