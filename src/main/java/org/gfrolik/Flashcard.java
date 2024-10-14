package org.gfrolik;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MODEL LAYER
 * Flashcard object that is part of a flashcard deck
 * @author gioia
 * @version 2024-09-30
 */
public class Flashcard {
    private static final Logger LOGGER = Logger.getLogger(Flashcard.class.getName()); // Logger instance

    private URL URL;
    private String word;


    // Constructor
    public Flashcard(URL frontURL, String backWord) {
        if (isValid(frontURL, backWord)) {
            this.URL = frontURL;
            this.word = backWord;
        } else {
            // Fallback URL and word
            try {
                URI uri = new URI("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG/280px-Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG");
                this.URL = uri.toURL();  // Convert URI to URL
            } catch (URISyntaxException | MalformedURLException e) {
                LOGGER.log(Level.SEVERE, "Error while creating the fallback URL", e);  // Log the error
            }
            this.word = "tgm";
        }
    }

    // Validate the provided URL and word
    public boolean isValid(URL frontURL, String backWord) {
        // Check if 'backWord' is not null and not empty
        if (backWord == null || backWord.isEmpty()) {
            return false;
        }

        // Check if 'frontURL' is not null and is valid
        if (frontURL != null) {
            try {
                // Convert the URL to a URI to check if it's valid
                frontURL.toURI();
                // Establish a connection to check if the URL is reachable
                URLConnection connection = frontURL.openConnection();
                connection.setConnectTimeout(5000); // 5 Sekunden Timeout
                connection.connect();  // Connect to check validity
            } catch (URISyntaxException | IOException e) {
                // URL is invalid
                return false;
            }
        } else {
            return false;
        }

        // If all checks pass, the URL and word are valid
        return true;
    }

    // return the URL (front)
    public java.net.URL getURL() {
        return URL;
    }


    // return the correct answer (back)
    public String getWord() {
        return word;
    }

}
