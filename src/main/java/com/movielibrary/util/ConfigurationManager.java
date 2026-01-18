package com.movielibrary.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Gestisce la configurazione dell'applicazione, caricando le proprietà da un file.
 * Implementa il pattern Singleton per garantire un'unica istanza e un accesso centralizzato
 * alle impostazioni di configurazione, come le chiavi API.
 */
public class ConfigurationManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);
    private static final String CONFIG_FILE = "config.properties";

    private static ConfigurationManager instance;
    private final Properties properties;

    /**
     * Costruttore privato per implementare il pattern Singleton.
     * Carica le proprietà dal file di configurazione presente nel classpath.
     */
    private ConfigurationManager() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                logger.error("FATALE: Impossibile trovare il file di configurazione: {}", CONFIG_FILE);
                throw new IllegalStateException("File di configurazione '" + CONFIG_FILE + "' non trovato nel classpath.");
            }
            properties.load(input);
            logger.info("File di configurazione '{}' caricato con successo.", CONFIG_FILE);
        } catch (IOException ex) {
            logger.error("FATALE: Errore durante la lettura del file di configurazione.", ex);
            throw new RuntimeException("Errore critico durante il caricamento della configurazione.", ex);
        }
    }

    /**
     * Restituisce l'unica istanza di ConfigurationManager (lazy initialization thread-safe).
     *
     * @return L'istanza singleton di {@link ConfigurationManager}.
     */
    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    /**
     * Recupera il valore di una proprietà di configurazione data la sua chiave.
     *
     * @param key La chiave della proprietà da cercare.
     * @return Il valore della proprietà come stringa.
     * @throws IllegalStateException se la chiave non viene trovata o il valore è vuoto.
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            logger.error("La proprietà richiesta '{}' non è stata trovata o è vuota nel file di configurazione.", key);
            throw new IllegalStateException("Proprietà mancante o vuota: " + key);
        }
        return value;
    }
}
