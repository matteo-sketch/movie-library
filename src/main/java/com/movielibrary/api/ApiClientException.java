package com.movielibrary.api;

/**
 * Rappresenta un'eccezione personalizzata per gli errori che si verificano nel client API.
 * Questa classe serve a incapsulare e gestire in modo uniforme le eccezioni
 * provenienti dalle interazioni con l'API esterna, come problemi di rete o risposte inattese.
 */
public class ApiClientException extends Exception {

    /**
     * Costruisce una nuova ApiClientException con un messaggio di errore specificato.
     *
     * @param message Il messaggio di dettaglio dell'errore.
     */
    public ApiClientException(String message) {
        super(message);
    }

    /**
     * Costruisce una nuova ApiClientException con un messaggio di errore e una causa specificati.
     *
     * @param message Il messaggio di dettaglio dell'errore.
     * @param cause L'eccezione originale che ha causato questo errore.
     */
    public ApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
