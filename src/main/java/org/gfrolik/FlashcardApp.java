package org.gfrolik;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * CONTROLLER LAYER
 * Flashcard trainer
 *
 * @author gioia
 * @version 2024-10-11
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

    // Start the training
    public void study() {
        boolean continueTraining = true;

        // Ask if the user wants to load an existing session or start a new one
        int response = JOptionPane.showOptionDialog(null,
                "Would you like to start a new session or load an existing one?",
                "Session Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"New Session", "Load Session"},
                "New Session");

        if (response == 1) {  // If the user chooses "Load Session"
            deck.loadSession();
        } else {  // If the user chooses "New Session"
            deck.createNewSession();
        }

        while (continueTraining) {
            // Select a random word-image pair
            deck.selectRandomCard();

            // Display the image instead of the URL
            ImageIcon imageIcon = new ImageIcon(deck.getCurrentCard().getURL());
            String statistics = deck.getStatistics().getStatisticsAsString();

            // Show the image and prompt the user for input
            String userInput = frontend.showImageWithPrompt(imageIcon, statistics);

            // Check the user input and display feedback
            if (userInput != null && !userInput.isEmpty()) {
                boolean correct = deck.checkUserInput(userInput);
                if (correct) {
                    frontend.showMessage("Correct!");
                } else {
                    frontend.showMessage("Incorrect! The correct word is: " + deck.getCurrentCard().getWord());
                }
            } else {
                // Stop if the input is empty
                continueTraining = false;
            }

            // Optionally, save the session after each input
            deck.saveSession();
        }

        // End the training and show statistics
        frontend.showMessage("Training finished. Statistics:\n" + deck.getStatistics());
        deck.saveSession();  // Save final session state
    }
}
