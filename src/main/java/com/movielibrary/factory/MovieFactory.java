package com.movielibrary.factory;

import com.movielibrary.dto.MovieDto;
import com.movielibrary.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory per la creazione di oggetti del dominio, come {@link Movie},
 * a partire da Data Transfer Objects (DTO).
 * Questo pattern disaccoppia la rappresentazione dei dati dell'API
 * dal modello di dominio dell'applicazione.
 */
public class MovieFactory {

    /**
     * Converte una lista di {@link MovieDto} in una lista di {@link Movie}.
     *
     * @param dtoList La lista di DTO da convertire.
     * @return Una nuova lista contenente gli oggetti {@link Movie} del dominio.
     */
    public List<Movie> createMovieListFromDto(List<MovieDto> dtoList) {
        return dtoList.stream()
                .map(this::createMovieFromDto)
                .collect(Collectors.toList());
    }

    /**
     * Converte un singolo {@link MovieDto} in un oggetto {@link Movie}.
     *
     * @param dto Il DTO da convertire.
     * @return Un'istanza di {@link Movie} creata a partire dal DTO.
     */
    public Movie createMovieFromDto(MovieDto dto) {
        return new Movie(
                dto.id(),
                dto.title(),
                dto.overview(),
                dto.releaseDate()
        );
    }
}
