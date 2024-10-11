package org.gfrolik;

import java.io.*;
import java.net.URL;


/**
 * save & load JSON
 * @author gioia
 * @version 2024-10-11
 */
public class SaveLoadTxt implements SaveLoad {

    @Override
    public void save(FlashcardDeck deck, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Save deck name and language
            writer.write(deck.getDeckName() + "\n");
            writer.write(deck.getLanguage() + "\n\n");

            // Save statistics
            Statistics stats = deck.getStatistics();
            writer.write(stats.getAttempts() + " (total attempts)\n");
            writer.write(stats.getCorrectCount() + " (correct attempts)\n");
            writer.write(stats.getWrongCount() + " (wrong attempts)\n\n");

            // Save flashcards (word and image URL)
            for (Flashcard card : deck.getFlashcards()) {
                writer.write(card.getWord() + ", " + card.getURL().toString() + "\n");
            }
        }
    }

    @Override
    public FlashcardDeck load(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read deck name and language
            String deckName = reader.readLine();
            String language = reader.readLine();
            reader.readLine();  // Skip empty line

            // Read statistics
            int totalAttempts = Integer.parseInt(reader.readLine().split(" ")[0]);
            int correctAttempts = Integer.parseInt(reader.readLine().split(" ")[0]);
            int wrongAttempts = Integer.parseInt(reader.readLine().split(" ")[0]);
            reader.readLine();  // Skip empty line

            // Create new FlashcardDeck and Statistics
            FlashcardDeck deck = new FlashcardDeck();
            deck.setDeckName(deckName);
            deck.setLanguage(language);
            Statistics stats = new Statistics(totalAttempts, correctAttempts, wrongAttempts);
            deck.setStatistics(stats);

            // Read flashcards
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String word = parts[0];
                URL imageUrl = new URL(parts[1]);
                Flashcard card = new Flashcard(imageUrl, word);
                deck.addFlashcard(card);
            }

            return deck;
        }
    }
}

