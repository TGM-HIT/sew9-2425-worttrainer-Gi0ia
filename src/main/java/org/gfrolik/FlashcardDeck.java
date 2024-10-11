package org.gfrolik;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.logging.*;


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



    public FlashcardDeck(String deckName, String language, List<Flashcard> cards, Statistics stats) {
        this.deckName = deckName;
        this.language = language;
        this.cards = cards;
        this.stats = stats;
    }

    public FlashcardDeck() {
        List<Flashcard> flashcards = new ArrayList<>();
        try {
            flashcards.add(new Flashcard(new URL("https://i.pinimg.com/originals/a4/4b/af/a44baf314bd7fda48430259e7365bf7f.jpg"), "spaghetti"));
            flashcards.add(new Flashcard(new URL("https://www.radsport-wagner.at/wp-content/uploads/2023/06/516caf81670d0566f088d5f4fed58b30-e1704898281552.png"), "bicicletta"));
            flashcards.add(new Flashcard(new URL("https://perfectdailygrind.com/es/wp-content/uploads/sites/2/2019/11/espresso.jpg"), "espresso"));
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Error while creating a URL for a flashcard", e);
        }

        this.deckName = "ITALIANO DECK 1";
        this.language = "italian";
        this.cards = flashcards;
        this.stats = new Statistics();
    }

    // set current card
    public void setCurrentCard(Flashcard currentCard) {
        this.currentCard = currentCard;
    }

    // select random card from deck
    public Flashcard selectRandomCard() {
        Random random = new Random();
        Flashcard f = cards.get(random.nextInt(cards.size()));
        setCurrentCard(f);
        return f;
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

    // Getter and setter for statistics
    public Statistics getStatistics() {
        return stats;
    }

    public Flashcard getCurrentCard() {
        return currentCard;
    }

    public void setStatistics(Statistics statistics) {
        this.stats = statistics;
    }
    // resets the stats
    public void resetTrainerStatistics() {
        stats.resetStatistics();
    }

    // Save session method
    public void saveSession(SaveLoad saver, String filePath) {
        try {
            saver.save(this, filePath);
            System.out.println("Session saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving session: " + e.getMessage());
        }
    }

    // Load session method
    public void loadSession(SaveLoad loader, String filePath) {
        try {
            FlashcardDeck loadDeck = loader.load(filePath);
            this.cards = loadDeck.getFlashcards();
            this.stats = loadDeck.getStatistics();
            System.out.println("Session loaded from " + filePath);
        } catch (IOException e) {
            System.err.println("Error loading session: " + e.getMessage());
        }
    }


    public void addFlashcard(Flashcard f) {
        this.cards.add(f);
    }
}
