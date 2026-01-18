package com.movielibrary.iterator;

import com.movielibrary.model.LibraryComponent;
import com.movielibrary.model.Movie;
import com.movielibrary.model.MovieCollection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iteratore per attraversare una struttura composita di {@link LibraryComponent}.
 * Questo iteratore implementa una visita in profondità (depth-first) della gerarchia,
 * permettendo di iterare su tutti i componenti, incluse le collezioni annidate.
 */
public class MovieCollectionIterator implements Iterator<LibraryComponent> {

    private final Deque<Iterator<LibraryComponent>> iterators = new ArrayDeque<>();

    /**
     * Costruisce un nuovo iteratore a partire da una lista di componenti radice.
     *
     * @param components La lista di componenti iniziali da cui iniziare l'iterazione.
     */
    public MovieCollectionIterator(List<LibraryComponent> components) {
        if (components != null) {
            this.iterators.push(components.iterator());
        }
    }

    /**
     * Verifica se ci sono altri elementi da iterare.
     *
     * @return {@code true} se l'iteratore ha altri elementi, {@code false} altrimenti.
     */
    @Override
    public boolean hasNext() {
        if (iterators.isEmpty()) {
            return false;
        }

        Iterator<LibraryComponent> currentIterator = iterators.peek();
        while (!currentIterator.hasNext()) {
            iterators.pop();
            if (iterators.isEmpty()) {
                return false;
            }
            currentIterator = iterators.peek();
        }
        return true;
    }

    /**
     * Restituisce il prossimo elemento nell'iterazione.
     *
     * @return il prossimo {@link LibraryComponent}.
     * @throws NoSuchElementException se non ci sono più elementi da iterare.
     */
    @Override
    public LibraryComponent next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Iterator<LibraryComponent> currentIterator = iterators.peek();
        LibraryComponent component = currentIterator.next();

        if (component instanceof MovieCollection) {
            MovieCollection collection = (MovieCollection) component;
            iterators.push(collection.getComponents().iterator());
        }
        return component;
    }
}
