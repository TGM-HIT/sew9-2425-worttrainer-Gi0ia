package org.gfrolik;

import javax.swing.*;
import java.net.URL;

/**
 * VIEW LAYER
 * @author gioia
 * @version 2024-10-11
 */
public class GUI {

    // Shows the image (as an ImageIcon) and prompts the user for input
    public String showImageWithPrompt(ImageIcon image, String statistics) {
        return (String) JOptionPane.showInputDialog(
                null,
                statistics + "\nPlease enter the correct word:",
                "Flashcard Trainer",
                JOptionPane.QUESTION_MESSAGE,
                image,
                null,
                null
        );
    }

    // Shows a message (e.g., "Correct!" or "Incorrect!")
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

