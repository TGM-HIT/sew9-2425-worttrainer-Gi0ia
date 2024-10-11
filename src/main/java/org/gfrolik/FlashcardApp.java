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
        boolean continueTraining = true;

        // Lade den Trainerzustand oder setze einen neuen Zustand
        deck.loadSession();

        while (continueTraining) {
            // Zufällig ein Wort-Bild-Paar auswählen
            deck.selectRandomCard();

            // Zeigt das Bild an und fordert den Benutzer zur Eingabe auf
            String userInput = frontend.showImageWithPrompt(deck.getCurrentCard().getURL(), deck.getStatistics().getStatisticsAsString());

            // Überprüft die Benutzereingabe und zeigt die Rückmeldung
            if (userInput != null && !userInput.isEmpty()) {
                boolean correct = deck.checkUserInput(userInput);
                if (correct) {
                    frontend.showMessage("Richtig!");
                } else {
                    frontend.showMessage("Falsch! Das richtige Wort ist: " + deck.getCurrentCard().getWord());
                }
            } else {
                // Abbrechen, wenn die Eingabe leer ist
                continueTraining = false;
            }

            // Optional: Nach jeder Eingabe den Zustand speichern
            deck.saveSession();
        }

        // Training beenden und Statistiken zeigen
        frontend.showMessage("Training beendet. Statistiken:\n" + deck.getStatistics());
        deck.saveSession(); // Endzustand speichern
    }

}
