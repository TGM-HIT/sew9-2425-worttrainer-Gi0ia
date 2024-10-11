package org.gfrolik;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * save & load JSON
 * @author gioia
 * @version 2024-10-11
 */
public class SaveLoadJSON implements SaveLoad {

    @Override
    public void save(FlashcardDeck deck, String filePath) throws IOException {
        // Create a JSONObject to store the session data
        JSONObject jsonDeck = new JSONObject();
        jsonDeck.put("deckName", deck.getDeckName());
        jsonDeck.put("language", deck.getLanguage());

        // Add statistics to the JSON object
        JSONObject jsonStats = new JSONObject();
        Statistics stats = deck.getStatistics();
        jsonStats.put("totalAttempts", stats.getAttempts());
        jsonStats.put("correctAttempts", stats.getCorrectCount());
        jsonStats.put("wrongAttempts", stats.getWrongCount());
        jsonDeck.put("statistics", jsonStats);

        // Add flashcards to the JSON object
        JSONArray jsonFlashcards = new JSONArray();
        for (Flashcard card : deck.getFlashcards()) {
            JSONObject jsonCard = new JSONObject();
            jsonCard.put("word", card.getWord());
            jsonCard.put("imageUrl", card.getURL().toString());
            jsonFlashcards.put(jsonCard);
        }
        jsonDeck.put("flashcards", jsonFlashcards);

        // Write JSON object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonDeck.toString(4));  // Pretty-print with 4 space indentation
        }
    }

    @Override
    public FlashcardDeck load(String filePath) throws IOException {
        // Read the JSON file and parse it into a JSONObject
        FileReader reader = new FileReader(filePath);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            sb.append((char) i);
        }
        reader.close();

        JSONObject jsonDeck = new JSONObject(sb.toString());

        // Extract deck details
        String deckName = jsonDeck.getString("deckName");
        String language = jsonDeck.getString("language");

        // Extract statistics
        JSONObject jsonStats = jsonDeck.getJSONObject("statistics");
        int totalAttempts = jsonStats.getInt("totalAttempts");
        int correctAttempts = jsonStats.getInt("correctAttempts");
        int wrongAttempts = jsonStats.getInt("wrongAttempts");

        // Create FlashcardDeck and Statistics
        FlashcardDeck deck = new FlashcardDeck();
        deck.setDeckName(deckName);
        deck.setLanguage(language);
        Statistics stats = new Statistics(totalAttempts, correctAttempts, wrongAttempts);
        deck.setStatistics(stats);

        // Extract flashcards
        JSONArray jsonFlashcards = jsonDeck.getJSONArray("flashcards");
        for (int j = 0; j < jsonFlashcards.length(); j++) {
            JSONObject jsonCard = jsonFlashcards.getJSONObject(j);
            String word = jsonCard.getString("word");
            URL imageUrl = new URL(jsonCard.getString("imageUrl"));
            Flashcard card = new Flashcard(imageUrl, word);
            deck.addFlashcard(card);
        }

        return deck;
    }
}
