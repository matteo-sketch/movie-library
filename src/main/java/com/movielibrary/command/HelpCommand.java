package com.movielibrary.command;

/**
 * Comando per visualizzare un messaggio di aiuto.
 * Attualmente, fornisce un testo segnaposto.
 */
public class HelpCommand implements Command {

    /**
     * Esegue il comando di aiuto.
     *
     * @param args Argomenti non utilizzati per questo comando.
     * @return Un messaggio di aiuto.
     */
    @Override
    public String execute(String... args) {
        return "Questo è un placeholder per l'aiuto. La dashboard mostra già tutti i comandi.";
    }
}
