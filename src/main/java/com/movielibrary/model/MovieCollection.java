package com.movielibrary.model;

import com.movielibrary.iterator.MovieCollectionIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Rappresenta una collezione di {@link LibraryComponent}, che possono essere
 * singoli film o altre collezioni. Questa classe è il "Composite" nel pattern omonimo.
 * Implementa {@link Iterable} per consentire l'iterazione sui suoi componenti.
 */
public class MovieCollection implements LibraryComponent, Iterable<LibraryComponent> {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final List<LibraryComponent> components = new ArrayList<>();

    /**
     * Costruisce una nuova collezione di film con un nome specificato.
     *
     * @param name Il nome della collezione.
     */
    public MovieCollection(String name) {
        this.name = name;
    }

    /**
     * Restituisce il nome della collezione.
     *
     * @return Il nome della collezione.
     */
    public String getName() {
        return name;
    }

    /**
     * Aggiunge un componente (film o collezione) a questa collezione.
     *
     * @param component Il componente da aggiungere.
     */
    public void add(LibraryComponent component) {
        components.add(component);
    }

    /**
     * Restituisce una rappresentazione testuale della collezione,
     * mostrando il nome e il numero totale di elementi.
     *
     * @param indent L'indentazione da applicare alla stringa.
     * @return Una stringa formattata che rappresenta la collezione.
     */
    @Override
    public String display(String indent) {
        return String.format("%s+ Collezione: %s (%d elementi)", indent, name, getMovieCount());
    }

    /**
     * Calcola e restituisce il numero totale di film in questa collezione,
     * sommando ricorsivamente i film di tutte le sottocollezioni.
     *
     * @return Il numero totale di film.
     */
    @Override
    public int getMovieCount() {
        return components.stream().mapToInt(LibraryComponent::getMovieCount).sum();
    }

    /**
     * Restituisce un iteratore per attraversare i componenti di questa collezione.
     *
     * @return Un'istanza di {@link MovieCollectionIterator}.
     */
    @Override
    public Iterator<LibraryComponent> iterator() {
        return new MovieCollectionIterator(components);
    }

    /**
     * Restituisce una copia della lista dei componenti di questa collezione.
     *
     * @return Una lista di {@link LibraryComponent}.
     */
    public List<LibraryComponent> getComponents() {
        return new ArrayList<>(components);
    }

    /**
     * Rimuove un componente dalla collezione in base al suo indice (1-based).
     *
     * @param index L'indice del componente da rimuovere.
     * @return Il componente rimosso, o {@code null} se l'indice non è valido.
     */
    public LibraryComponent removeComponentByIndex(int index) {
        int actualIndex = index - 1;
        if (actualIndex >= 0 && actualIndex < components.size()) {
            return components.remove(actualIndex);
        }
        return null;
    }
}
