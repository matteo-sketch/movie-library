package com.movielibrary.command;

/**
 * Interfaccia che rappresenta un comando eseguibile.
 * Definisce un modello comune per tutti i comandi dell'applicazione,
 * garantendo che ciascuno abbia un metodo `execute`.
 */
public interface Command {
    /**
     * Esegue il comando con gli argomenti specificati.
     *
     * @param args Gli argomenti necessari per l'esecuzione del comando.
     * @return Una stringa che rappresenta il risultato o l'output del comando,
     *         da mostrare all'utente.
     */
    String execute(String... args);
}
