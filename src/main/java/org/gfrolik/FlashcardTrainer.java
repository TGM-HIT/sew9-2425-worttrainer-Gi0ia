package org.gfrolik;

import javax.swing.JOptionPane;

/**
 * CONTROLLER LAYER
 * Flashcard trainer
 * @author gioia
 * @version 2024-09-30
 */
public class FlashcardTrainer {
    private FlashcardDeck deck;
    private int correctCount;
    private int wrongCount;

    // start the training
    public void study() {

    }

    // show the image - request user input
    public void showFlashcard() {

    }

    // processes the user input - show feedback (true / false)
    public boolean processUserInput(String input) {
        return card.getBackAnswer().equals(response);
    }

    // save state
    public void saveTrainerState() {


    }

    // show statistics
    public void showStats() {
        JOptionPane.showMessageDialog(null, correctCount*100/(correctCount+wrongCount) + "% correct (" + correctCount + "/" + (correctCount+wrongCount)+")");
    }

}
