package com.movielibrary.command;

import com.movielibrary.dto.MovieDto;
import com.movielibrary.factory.MovieFactory;
import com.movielibrary.model.Movie;
import com.movielibrary.ui.ConsoleUI;

import java.util.List;

/**
 * Comando per aggiungere un film alla libreria dai risultati di una ricerca.
 * L'utente specifica l'indice del film da aggiungere.
 */
public class AddCommand implements Command {
    private final ConsoleUI ui;
    private final MovieFactory movieFactory;

    /**
     * Costruisce un nuovo comando AddCommand.
     *
     * @param ui L'interfaccia utente per interagire con l'utente e la libreria.
     * @param movieFactory La factory per creare oggetti Movie.
     */
    public AddCommand(ConsoleUI ui, MovieFactory movieFactory) {
        this.ui = ui;
        this.movieFactory = movieFactory;
    }

    /**
     * Esegue il comando per aggiungere un film.
     *
     * @param args Gli argomenti del comando, dove il primo argomento è l'indice del film da aggiungere.
     * @return Un messaggio che indica l'esito dell'operazione.
     */
    @Override
    public String execute(String... args) {
        if (args.length < 1) {
            return "Errore: Specifica il numero del film da aggiungere. Esempio: add 1";
        }

        List<MovieDto> searchResults = ui.getLastSearchResults();
        if (searchResults == null || searchResults.isEmpty()) {
            return "Nessun risultato di ricerca da cui aggiungere. Esegui prima una ricerca.";
        }

        try {
            int movieIndex = Integer.parseInt(args[0]) - 1;
            if (movieIndex >= 0 && movieIndex < searchResults.size()) {
                MovieDto movieToAddDto = searchResults.get(movieIndex);
                Movie movie = movieFactory.createMovieFromDto(movieToAddDto);
                ui.getLibrary().add(movie);
                return String.format("'%s' aggiunto alla libreria.", movie.getTitle());
            } else {
                return "Numero non valido. Seleziona un numero dalla lista dei risultati.";
            }
        } catch (NumberFormatException e) {
            return "Errore: Inserisci un numero valido.";
        }
    }
}
