package org.gfrolik;

import java.util.ArrayList;
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
    private Statistics stats;



    public FlashcardDeck(String deckName, String language, List<Flashcard> cards, Statistics stats) {
        this.deckName = deckName;
        this.language = language;
        this.cards = cards;
        this.stats = stats;
    }

    public FlashcardDeck() {
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("https://i.pinimg.com/originals/a4/4b/af/a44baf314bd7fda48430259e7365bf7f.jpg", "spaghetti"));
        flashcards.add(new Flashcard("https://www.radsport-wagner.at/wp-content/uploads/2023/06/516caf81670d0566f088d5f4fed58b30-e1704898281552.png", "bicicletta"));
        flashcards.add(new Flashcard("https://perfectdailygrind.com/es/wp-content/uploads/sites/2/2019/11/espresso.jpg", "espresso"));

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
        return cards.get(random.nextInt(cards.size()));

    }


    // returns true if user input is correct & increments the count
    public boolean checkUserInput(String input) {

        if (this.currentCard.getBackAnswer().equalsIgnoreCase(input)) {
            stats.incrementCorrectAttempts();
            return true;
        }
        else {
            stats.incrementWrongAttempts();
            return false;
        }

    }

    // returns stats as String
    public String getStatistics() {
        return stats.getStatistics();
    }

    // resets the stats
    public void resetTrainerStatistics() {
        stats.resetStatistics();
    }

    public void saveSession() {

    }

    public void loadSession() {

    }


}
