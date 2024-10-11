package org.gfrolik;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * CONTROLLER LAYER Flashcard trainer
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
        int questionCounter = 0; // Counter for the number of questions

        // Ask if the user wants to load an existing session or start a new one
        int response = JOptionPane.showOptionDialog(null, "Would you like to start a new session or load an existing one?",
                "Session Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[] { "New Session", "Load Session" }, "New Session");

        if (response == 1) { // If the user chooses "Load Session"
            deck.loadSession();
        } else { // If the user chooses "New Session"
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
                questionCounter++; // Increment question counter
            } else {
                // Stop if the input is empty
                continueTraining = false;
            }

            // After every 3 questions, ask the user if they want to continue or stop
            if (questionCounter >= 3) {
                int continueResponse = JOptionPane.showConfirmDialog(null,
                        "Would you like to continue playing?", "Continue?", JOptionPane.YES_NO_OPTION);

                if (continueResponse == JOptionPane.NO_OPTION) {
                    continueTraining = false;  // End the training session
                    frontend.showMessage("Training session has ended.");

                }
                questionCounter = 0; // Reset counter after asking the user
            }
        }

        // Show final statistics when the session ends
        frontend.showMessage("Training finished. Statistics:\n" + deck.getStatistics().getStatisticsAsString());
        deck.saveSession();  // Save final session state
    }
}
