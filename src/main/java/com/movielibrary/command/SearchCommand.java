package com.movielibrary.command;

import com.movielibrary.api.ApiClientException;
import com.movielibrary.api.TmdbApiClient;
import com.movielibrary.dto.MovieDto;
import com.movielibrary.ui.ConsoleUI;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Comando per cercare film utilizzando l'API di TMDB.
 * Prende una query di ricerca, contatta l'API e formatta i risultati per la visualizzazione.
 */
public class SearchCommand implements Command {

    private final TmdbApiClient apiClient;
    private final ConsoleUI ui;

    /**
     * Costruisce un nuovo comando SearchCommand.
     *
     * @param apiClient Il client per comunicare con l'API di TMDB.
     * @param ui L'interfaccia utente per memorizzare e visualizzare i risultati.
     */
    public SearchCommand(TmdbApiClient apiClient, ConsoleUI ui) {
        this.apiClient = apiClient;
        this.ui = ui;
    }

    /**
     * Esegue il comando di ricerca.
     *
     * @param args Gli argomenti del comando, che costituiscono la query di ricerca.
     * @return Una stringa formattata con i risultati della ricerca o un messaggio di errore.
     */
    @Override
    public String execute(String... args) {
        if (args.length == 0) {
            return "Specifica un titolo da cercare. Esempio: search Interstellar";
        }

        String query = String.join(" ", args);
        try {
            List<MovieDto> results = apiClient.searchMovies(query);
            ui.setLastSearchResults(results);

            if (results.isEmpty()) {
                return String.format("Nessun film trovato per '%s'.", query);
            }

            String resultString = IntStream.range(0, results.size())
                    .mapToObj(i -> String.format("%d: %s (%s)", i + 1, results.get(i).title(), results.get(i).releaseDate()))
                    .collect(Collectors.joining("\n"));

            return "Risultati trovati:\n" + resultString + "\n\nUsa il comando 'add <numero>' per aggiungere un film.";
        } catch (ApiClientException e) {
            return "Errore durante la comunicazione con l'API: " + e.getMessage();
        }
    }
}
