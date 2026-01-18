package com.movielibrary.command;

import com.movielibrary.service.LibraryStorageService;
import com.movielibrary.service.StorageException;
import com.movielibrary.ui.ConsoleUI;

/**
 * Comando per salvare la libreria di film su un file.
 * Utilizza il LibraryStorageService per serializzare e salvare la libreria.
 */
public class SaveCommand implements Command {
    private final LibraryStorageService storageService;
    private final ConsoleUI ui;

    /**
     * Costruisce un nuovo comando SaveCommand.
     *
     * @param storageService Il servizio per la gestione del salvataggio e caricamento.
     * @param ui L'interfaccia utente da cui ottenere la libreria da salvare.
     */
    public SaveCommand(LibraryStorageService storageService, ConsoleUI ui) {
        this.storageService = storageService;
        this.ui = ui;
    }

    /**
     * Esegue il comando di salvataggio.
     *
     * @param args Argomenti non utilizzati per questo comando.
     * @return Un messaggio che indica l'esito del salvataggio.
     */
    @Override
    public String execute(String... args) {
        try {
            storageService.saveLibrary(ui.getLibrary());
            return "Libreria salvata con successo su 'movielibrary.ser'.";
        } catch (StorageException e) {
            return "Errore durante il salvataggio: " + e.getMessage();
        }
    }
}
