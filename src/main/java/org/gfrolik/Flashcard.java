package org.gfrolik;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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

    // constructor
    public Flashcard(URL frontURL, String backWord) {
        if (isValid()) {
            this.URL = frontURL;
            this.word = backWord;
        } else {
            try {
                URI uri = new URI("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG/280px-Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG");
                this.URL = uri.toURL();  // Convert URI to URL
            } catch (URISyntaxException | MalformedURLException e) {
                LOGGER.log(Level.SEVERE, "Error while creating the fallback URL", e);  // Log the error
            }
            this.word = "tgm";
        }
    }


    // check if the values are valid
    public boolean isValid() {
        // Check if 'word' is null
        if (this.word == null || this.word.isEmpty()) {
            return false;
        }

        // Check if 'URL' is not null and is valid
        if (this.URL != null) {
            try {
                // Convert the URL to a URI to check if it's valid
                this.URL.toURI();  // URL has a toURI() method, which throws URISyntaxException if invalid
            } catch (URISyntaxException e) {
                return false;  // Return false if the URL is invalid
            }
        }

        return true;  // If no issues, return true
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
