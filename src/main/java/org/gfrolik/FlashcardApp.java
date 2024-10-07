package org.gfrolik;

import javax.swing.JOptionPane;

/**
 * CONTROLLER LAYER
 * Flashcard trainer
 * @author gioia
 * @version 2024-09-30
 */
public class FlashcardApp {
    private FlashcardDeck deck;
    private GUI frontend;
    private int correctCount;
    private int wrongCount;

    public FlashcardApp(FlashcardDeck deck, GUI frontend) {
        this.deck = deck;
        this.frontend = frontend;
    }

    // start the training
    public void study() {

    }

}
