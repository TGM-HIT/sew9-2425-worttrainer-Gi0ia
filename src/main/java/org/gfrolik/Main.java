package org.gfrolik;

public class Main {
    public static void main(String[] args) {
        // create Model (SpellingTrainer)
        FlashcardDeck deck = new FlashcardDeck();

        // create View (TrainerView)
        GUI frontend = new GUI();

        // create Controller und starte das Training
        FlashcardApp duo = new FlashcardApp(deck, frontend);
        duo.study();
    }
}
