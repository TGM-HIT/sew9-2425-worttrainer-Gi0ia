package org.gfrolik;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * VIEW LAYER
 * @author gioia
 * @version 2024-10-11
 */
public class GUI {

    // Shows the image (as an ImageIcon) and prompts the user for input
    public String showImageWithPrompt(ImageIcon image, String statistics) {
        Image scaledImage = image.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Scale the image
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Create a new ImageIcon from the scaled image

        return (String) JOptionPane.showInputDialog(
                null,
                statistics + "\nPlease enter the correct word:",
                "Flashcard Trainer",
                JOptionPane.QUESTION_MESSAGE,
                scaledIcon,
                null,
                null
        );
    }

    // Shows a message (e.g., "Correct!" or "Incorrect!")
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

