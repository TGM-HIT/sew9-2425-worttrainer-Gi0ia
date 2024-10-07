package org.gfrolik;
import java.net.URL;
import java.net.MalformedURLException;

/**
 * MODEL LAYER
 * Flashcard object that is part of a flashcard deck
 * @author gioia
 * @version 2024-09-30
 */
public class Flashcard {
    private String frontURL;
    private String backAnswer;

    // constructor
    public Flashcard(String frontURL, String backAnswer) {
        if(isValid()){
            this.frontURL = frontURL;
            this.backAnswer = backAnswer;
        }
        else {
            this.frontURL = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG/280px-Brigittenau_%28Wien%29_-_TGM-Hauptgeb%C3%A4ude.JPG";
            this.backAnswer = "tgm";
        }
    }

    // check if the values are valid
    public boolean isValid() {
        if(this.backAnswer==null)
            return false;
        if(this.frontURL!=null){
            try {
                new URL(this.frontURL);         // Try to create a URL object
            } catch (MalformedURLException e) {
                return false;                   // If a MalformedURLException is thrown, the URL is invalid
            }
        }
        return true;                            // If no exception is thrown, the URL is valid
    }

    // return the URL (front)
    public String getFrontURL() {
        return frontURL;
    }


    // return the correct answer (back)
    public String getBackAnswer() {
        return backAnswer;
    }

}
