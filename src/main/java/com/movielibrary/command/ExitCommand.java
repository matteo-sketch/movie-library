package com.movielibrary.command;

import com.movielibrary.ui.ConsoleUI;

/**
 * Comando per terminare l'applicazione.
 * Notifica all'interfaccia utente di arrestare i servizi e chiudere il programma.
 */
public class ExitCommand implements Command {
    private final ConsoleUI ui;

    /**
     * Costruisce un nuovo comando ExitCommand.
     *
     * @param ui L'interfaccia utente da notificare per l'arresto.
     */
    public ExitCommand(ConsoleUI ui) {
        this.ui = ui;
    }

    /**
     * Esegue il comando di uscita.
     *
     * @param args Argomenti non utilizzati per questo comando.
     * @return Un messaggio di saluto.
     */
    @Override
    public String execute(String... args) {
        ui.shutdown(); // Notifica alla UI di spegnere i servizi in background
        return "Arrivederci!";
    }
}
