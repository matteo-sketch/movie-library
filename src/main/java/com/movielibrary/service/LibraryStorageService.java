package com.movielibrary.service;

import com.movielibrary.model.LibraryComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Servizio per la gestione della persistenza della libreria di film.
 * Si occupa di salvare e caricare la struttura dei componenti della libreria
 * tramite serializzazione Java.
 */
public class LibraryStorageService {
    private static final Logger logger = LoggerFactory.getLogger(LibraryStorageService.class);
    private final String filePath;

    /**
     * Costruisce un nuovo servizio di storage.
     *
     * @param filePath Il percorso del file su cui salvare e da cui caricare la libreria.
     */
    public LibraryStorageService(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Salva l'intera libreria su file.
     *
     * @param library Il componente radice della libreria da salvare.
     * @throws StorageException Se si verifica un errore durante l'operazione di salvataggio.
     */
    public void saveLibrary(LibraryComponent library) throws StorageException {
        logger.info("Tentativo di salvataggio della libreria su '{}'...", filePath);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(library);
            logger.info("Libreria salvata con successo.");
        } catch (IOException e) {
            logger.error("Errore durante il salvataggio della libreria.", e);
            throw new StorageException("Impossibile salvare la libreria su file.", e);
        }
    }

    /**
     * Carica la libreria da un file.
     *
     * @return Il componente radice della libreria caricata, o {@code null} se il file non esiste.
     * @throws StorageException Se si verifica un errore durante l'operazione di caricamento.
     */
    public LibraryComponent loadLibrary() throws StorageException {
        logger.info("Tentativo di caricamento della libreria da '{}'...", filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            logger.warn("File '{}' non trovato. Verrà creata una nuova libreria.", filePath);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            LibraryComponent library = (LibraryComponent) ois.readObject();
            logger.info("Libreria caricata con successo.");
            return library;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Errore durante il caricamento della libreria.", e);
            throw new StorageException("Impossibile caricare la libreria dal file.", e);
        }
    }
}
