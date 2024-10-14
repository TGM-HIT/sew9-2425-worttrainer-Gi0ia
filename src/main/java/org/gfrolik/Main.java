package org.gfrolik;

/**
 * main
 * @author gioia
 * @version 2024-10-11
 */
public class Main {
    public static void main(String[] args) {
        // create Model (Flashcard Deck)
        FlashcardDeck deck = new FlashcardDeck();

        // create View (frontend / GUI)
        GUI frontend = new GUI();

        // create Controller und starte das Training
        FlashcardApp duo = new FlashcardApp(deck, frontend);
        duo.study();
    }
}
