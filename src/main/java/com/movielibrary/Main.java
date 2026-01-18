package com.movielibrary;

import com.movielibrary.ui.ConsoleUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe principale dell'applicazione Movie Library.
 * Contiene il punto di ingresso (metodo `main`) che avvia l'interfaccia utente a console.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Il punto di ingresso principale dell'applicazione.
     * Crea e avvia l'interfaccia utente a console.
     *
     * @param args Argomenti della riga di comando (non utilizzati).
     */
    public static void main(String[] args) {
        logger.info("Avvio dell'applicazione Movie Library...");
        try {
            ConsoleUI ui = new ConsoleUI();
            ui.run();
        } catch (Exception e) {
            logger.error("Si è verificato un errore critico e non gestito. L'applicazione verrà terminata.", e);
        }
        logger.info("Applicazione terminata correttamente.");
    }
}
