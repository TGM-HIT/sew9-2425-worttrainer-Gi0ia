package org.gfrolik;

import java.util.List;
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

    private List<Flashcard> cards;
    private Flashcard currentCard;
    private int attempts;
    private int correctAttempts;
    private int wrongAttempts;


    public FlashcardDeck(String deckName, String language, List<Flashcard> cards) {
        this.deckName = deckName;
        this.language = language;
        this.cards = cards;
    }

    // set current card
    public void setCurrentCard(Flashcard currentCard) {
        this.currentCard = currentCard;
    }

    // select random card from deck
    public Flashcard selectRandomCard() {
        Random random = new Random();
        return cards.get(random.nextInt(cards.size()));

    }


    // returns true if user input is correct
    public boolean checkUserInput(String input) {
         return this.currentCard.getBackAnswer().equalsIgnoreCase(input);
    }

    // returns
    public String getStatistics() {

    }

    public void saveSession() {

    }

    public void loadSession() {

    }


}
