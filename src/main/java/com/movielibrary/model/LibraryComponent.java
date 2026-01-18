package com.movielibrary.model;

import java.io.Serializable;

/**
 * Interfaccia per il pattern Composite, che rappresenta un componente della libreria.
 * Un componente può essere un singolo film ({@link Movie}) o una collezione di film ({@link MovieCollection}).
 * Estende {@link Serializable} per permettere la persistenza dell'intera struttura.
 */
public interface LibraryComponent extends Serializable {

    /**
     * Restituisce una rappresentazione testuale del componente, formattata con un'indentazione.
     *
     * @param indent La stringa di indentazione da usare per la formattazione.
     * @return Una stringa che rappresenta il componente.
     */
    String display(String indent);

    /**
     * Restituisce il numero totale di film contenuti in questo componente.
     * Per un film singolo, restituisce 1. Per una collezione, restituisce la somma
     * dei film in essa contenuti, ricorsivamente.
     *
     * @return Il numero di film.
     */
    int getMovieCount();
}
