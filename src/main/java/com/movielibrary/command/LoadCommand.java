package com.movielibrary.command;

import com.movielibrary.model.LibraryComponent;
import com.movielibrary.model.MovieCollection;
import com.movielibrary.service.LibraryStorageService;
import com.movielibrary.service.StorageException;
import com.movielibrary.ui.ConsoleUI;

/**
 * Comando per caricare la libreria di film da un file.
 * Utilizza il LibraryStorageService per deserializzare la libreria.
 */
public class LoadCommand implements Command {
    private final LibraryStorageService storageService;
    private final ConsoleUI ui;

    /**
     * Costruisce un nuovo comando LoadCommand.
     *
     * @param storageService Il servizio per la gestione del salvataggio e caricamento.
     * @param ui L'interfaccia utente per aggiornare la libreria caricata.
     */
    public LoadCommand(LibraryStorageService storageService, ConsoleUI ui) {
        this.storageService = storageService;
        this.ui = ui;
    }

    /**
     * Esegue il comando di caricamento.
     *
     * @param args Argomenti non utilizzati per questo comando.
     * @return Un messaggio che indica l'esito del caricamento.
     */
    @Override
    public String execute(String... args) {
        try {
            LibraryComponent loaded = storageService.loadLibrary();
            if (loaded instanceof MovieCollection) {
                ui.setLibrary((MovieCollection) loaded);
                return "Libreria caricata con successo da 'movielibrary.ser'.";
            } else if (loaded == null) {
                return "Nessun file di salvataggio trovato. È stata creata una nuova libreria.";
            } else {
                return "Errore: il file di salvataggio non contiene una collezione valida.";
            }
        } catch (StorageException e) {
            return "Errore durante il caricamento: " + e.getMessage();
        }
    }
}
