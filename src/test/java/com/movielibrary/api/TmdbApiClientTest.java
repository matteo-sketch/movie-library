package com.movielibrary.api;

import com.movielibrary.dto.MovieDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test di integrazione per TmdbApiClient.
 * Questo test esegue una vera chiamata di rete all'API di TMDB.
 * Richiede una connessione internet attiva e un file config.properties valido.
 */
class TmdbApiClientTest {

    @Test
    @DisplayName("Dovrebbe cercare un film e ricevere una risposta valida dall'API reale")
    void searchMovies_WithRealApiCall_ShouldReturnResults() {
        // Arrange
        // Creiamo un'istanza del client che userà la configurazione reale
        TmdbApiClient apiClient = new TmdbApiClient();
        String query = "Inception";

        // Act
        List<MovieDto> movies = null;
        try {
            movies = apiClient.searchMovies(query);
        } catch (ApiClientException e) {
            // Se il test fallisce qui, significa che c'è un problema di rete
            // o con la chiave API. `fail()` fa fallire il test immediatamente.
            fail("La chiamata API ha generato un'eccezione non prevista: " + e.getMessage());
        }

        // Assert
        // Verifichiamo che la risposta sia plausibile
        assertNotNull(movies, "La lista dei film non dovrebbe essere nulla.");
        assertFalse(movies.isEmpty(), "La ricerca per 'Inception' dovrebbe restituire almeno un risultato.");
        assertTrue(movies.get(0).title().contains("Inception"), "Il titolo del primo risultato dovrebbe contenere 'Inception'.");
    }

    @Test
    @DisplayName("Dovrebbe gestire una ricerca senza risultati")
    void searchMovies_WithNoResultsQuery_ShouldReturnEmptyList() {
        // Arrange
        TmdbApiClient apiClient = new TmdbApiClient();
        // Una query che quasi certamente non darà risultati
        String query = "asdfghjklqwertyuiop";

        // Act
        List<MovieDto> movies = null;
        try {
            movies = apiClient.searchMovies(query);
        } catch (ApiClientException e) {
            fail("La chiamata API ha generato un'eccezione non prevista: " + e.getMessage());
        }

        // Assert
        assertNotNull(movies, "La lista dei film non dovrebbe essere nulla, ma vuota.");
        assertTrue(movies.isEmpty(), "La ricerca per una stringa casuale dovrebbe restituire una lista vuota.");
    }
}

