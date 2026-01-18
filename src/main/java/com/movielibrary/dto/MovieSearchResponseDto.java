package com.movielibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO per la risposta completa della ricerca di film dall'API di TMDB.
 * Questa classe modella la struttura della risposta JSON, che contiene
 * una lista di risultati di film.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchResponseDto {

    /**
     * La lista di film ottenuti come risultato della ricerca.
     * Il campo JSON 'results' viene mappato a questa proprietà.
     */
    @JsonProperty("results")
    private List<MovieDto> results;

    /**
     * Restituisce la lista dei risultati dei film.
     *
     * @return Una lista di {@link MovieDto}.
     */
    public List<MovieDto> getResults() {
        return results;
    }

    /**
     * Imposta la lista dei risultati dei film.
     *
     * @param results La lista di {@link MovieDto} da impostare.
     */
    public void setResults(List<MovieDto> results) {
        this.results = results;
    }
}
