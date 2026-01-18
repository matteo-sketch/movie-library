package com.movielibrary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movielibrary.dto.MovieSearchResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servizio per il parsing di stringhe JSON in oggetti DTO (Data Transfer Objects).
 * Questa classe incapsula l'uso della libreria Jackson per la deserializzazione,
 * fornendo un punto centralizzato per la logica di parsing.
 */
public class JsonParser {

    private static final Logger logger = LoggerFactory.getLogger(JsonParser.class);
    private final ObjectMapper objectMapper;

    /**
     * Costruisce un nuovo parser JSON, inizializzando l'ObjectMapper di Jackson.
     */
    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Esegue il parsing di una stringa JSON che rappresenta la risposta di una ricerca di film.
     *
     * @param json La stringa JSON da parsare.
     * @return Un oggetto {@link MovieSearchResponseDto} che contiene i risultati della ricerca.
     * @throws JsonProcessingException se il JSON non è valido o non corrisponde al DTO atteso.
     */
    public MovieSearchResponseDto parseMovieSearchResponse(String json) throws JsonProcessingException {
        try {
            return objectMapper.readValue(json, MovieSearchResponseDto.class);
        } catch (JsonProcessingException e) {
            logger.error("Errore durante il parsing della risposta JSON.", e);
            throw e;
        }
    }
}
