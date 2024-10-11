package org.gfrolik;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import java.util.Random;


/**
 * MODEL LAYER
 * Flashcard deck
 * @author gioia
 * @version 2024-09-30
 */
public class FlashcardDeck {
    private String deckName;
    private String language;
    private static final Logger LOGGER = Logger.getLogger(Flashcard.class.getName());

    private Flashcard currentCard;
    private List<Flashcard> cards;
    private Statistics stats;

    private Random random;


    // Empty constructor (no initialization)
    public FlashcardDeck() {
        this.cards = new ArrayList<>();
        this.stats = new Statistics();
        this.random = new Random();this.random = new Random();
    }

    public void createNewSession() {
        this.cards.clear(); // Clear existing cards if any
        this.stats.resetStatistics(); // Reset statistics

        // Add flashcards (you can dynamically set different flashcards)
        try {
            this.cards.add(new Flashcard(new URL("https://i.pinimg.com/originals/a4/4b/af/a44baf314bd7fda48430259e7365bf7f.jpg"), "spaghetti"));
            this.cards.add(new Flashcard(new URL("https://www.radsport-wagner.at/wp-content/uploads/2023/06/516caf81670d0566f088d5f4fed58b30-e1704898281552.png"), "bicicletta"));
            this.cards.add(new Flashcard(new URL("https://perfectdailygrind.com/es/wp-content/uploads/sites/2/2019/11/espresso.jpg"), "espresso"));
            this.cards.add(new Flashcard(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8XEpsxtXNlG8t10J3TomRvioh46sRXUg9GBtEQOft7JVJgCCQu7KEHiWa4ZlrL6TmQgk&usqp=CAU"), "verdura"));
            this.cards.add(new Flashcard(new URL("https://www.spokin.com/wp-content/uploads/2022/06/gelato-hero-final.png"), "gelato"));
            this.cards.add(new Flashcard(new URL("https://sothebys-com.brightspotcdn.com/a6/83/0d19eb4a429d991ff433f0cd94f3/ferrari-f40-3.jpg"), "ferarri"));

        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error while creating a URL for a flashcard", e);
        }

        this.deckName = "ITALIANO DECK 1";
        this.language = "italian";
    }


    // set current card
    public void setCurrentCard(Flashcard currentCard) {
        this.currentCard = currentCard;
    }

    // select random card from deck
    public void selectRandomCard() {
        if (!cards.isEmpty()) {
            int randomIndex = random.nextInt(cards.size());
            this.currentCard = cards.get(randomIndex);
        } else {
            this.currentCard = null; // No more cards left
        }
    }

    public List<Flashcard> getFlashcards() {
        return cards;
    }


    // returns true if user input is correct & increments the count
    public boolean checkUserInput(String input) {

        if (this.currentCard.getWord().equalsIgnoreCase(input)) {
            stats.incrementCorrectAttempts();
            return true;
        }
        else {
            stats.incrementWrongAttempts();
            return false;
        }

    }

    // resets the stats
    public void resetTrainerStatistics() {
        stats.resetStatistics();
    }

    // Save session method
    public void saveSession() {
        // Create a file chooser for the user to select where to save the file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Session");

        // Show the save dialog
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Check the file extension to decide which saver to use
            SaveLoad saver;
            if (filePath.endsWith(".json")) {
                saver = new SaveLoadJSON();  // Use JSON saver
            } else if (filePath.endsWith(".txt")) {
                saver = new SaveLoadTxt();   // Use Text saver
            } else {
                // If the user didn't specify a file extension, we assume text file as default
                filePath += ".txt";
                saver = new SaveLoadTxt();
            }

            // Attempt to save the session
            try {
                saver.save(this, filePath);  // Use the appropriate save method
                System.out.println("Session saved to " + filePath);
            } catch (IOException e) {
                System.err.println("Error saving session: " + e.getMessage());
            }
        }
    }

    // Load session method
    public void loadSession() {
        // Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a session file to load");

        // Show the file chooser dialog and capture the response
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            String filePath = fileToLoad.getAbsolutePath();  // Get the file path

            // Check the file extension to decide which loader to use
            SaveLoad loader;
            if (filePath.endsWith(".json")) {
                loader = new SaveLoadJSON();  // Use JSON loader
            } else if (filePath.endsWith(".txt")) {
                loader = new SaveLoadTxt();   // Use Text loader
            } else {
                System.err.println("Unsupported file format.");
                return;
            }

            // Attempt to load the session
            try {
                FlashcardDeck loadedDeck = loader.load(filePath);
                this.cards = loadedDeck.getFlashcards();
                this.stats = loadedDeck.getStatistics();
                this.deckName = loadedDeck.getDeckName();
                this.language = loadedDeck.getLanguage();
                System.out.println("Session loaded from " + filePath);
            } catch (IOException e) {
                System.err.println("Error loading session: " + e.getMessage());
            }
        }
    }

    // Getter and setter for statistics
    public Statistics getStatistics() {
        return stats;
    }

    public Flashcard getCurrentCard() {
        return currentCard;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getDeckName() {
        return this.deckName;
    }


    public void addFlashcard(Flashcard f) {
        this.cards.add(f);
    }


    public void setStatistics(Statistics statistics) {
        this.stats = statistics;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
