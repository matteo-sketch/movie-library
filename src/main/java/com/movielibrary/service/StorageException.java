package com.movielibrary.service;

/**
 * Eccezione personalizzata per incapsulare errori relativi alla persistenza (salvataggio/caricamento).
 * Questa classe astrae i dettagli di implementazione delle eccezioni di I/O (es. {@link java.io.IOException}),
 * fornendo un tipo di errore specifico del dominio dell'applicazione.
 */
public class StorageException extends Exception {

    /**
     * Costruisce una nuova StorageException con un messaggio di errore e una causa specificati.
     *
     * @param message Il messaggio di dettaglio dell'errore.
     * @param cause L'eccezione originale che ha causato questo errore.
     */
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
