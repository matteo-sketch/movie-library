package com.movielibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) per i dati di un film ricevuti dall'API di TMDB.
 * Questa classe è un `record` Java, che fornisce una sintassi concisa per creare
 * classi immutabili. Le annotazioni Jackson sono usate per mappare i campi JSON
 * alle proprietà del record.
 *
 * @param id L'identificativo univoco del film.
 * @param title Il titolo del film.
 * @param overview Una breve trama del film.
 * @param releaseDate La data di uscita del film.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieDto(
        int id,
        String title,
        String overview,
        @JsonProperty("release_date") String releaseDate
) {
}
