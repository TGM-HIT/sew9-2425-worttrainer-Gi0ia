package org.gfrolik;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class SaveLoadJSON implements SaveLoad {

    @Override
    public void save(FlashcardDeck deck, String filePath) throws IOException {
        // Create a JSON object to hold the session data
        JSONObject jsonTrainer = new JSONObject();
        jsonTrainer.put("totalAttempts", deck.getStatistics().getAttempts());
        jsonTrainer.put("correctAttempts", deck.getStatistics().getCorrectCount());
        jsonTrainer.put("wrongAttempts", deck.getStatistics().getWrongCount());

        // Convert word-image pairs to JSON array
        JSONArray jsonWordImagePairs = new JSONArray();
        for (Flashcard card : deck.getFlashcards()) {
            JSONObject jsonPair = new JSONObject();
            jsonPair.put("word", card.getWord());
            jsonPair.put("imageUrl", card.getURL().toString());
            jsonWordImagePairs.put(jsonPair);
        }
        jsonTrainer.put("wordImagePairs", jsonWordImagePairs);

        // Write JSON object to file
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonTrainer.toString(4)); // Indented output
        }
    }

    @Override
    public FlashcardDeck load(String filePath) throws IOException {
        // Read JSON file and parse it into a SpellingTrainer object
        FileReader reader = new FileReader(filePath);
        StringBuilder sb = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            sb.append((char) i);
        }
        reader.close();

        JSONObject jsonTrainer = new JSONObject(sb.toString());
        FlashcardDeck deck = new FlashcardDeck();

        // Load statistics
        Statistics stats = new Statistics();
        stats.setAttempts(jsonTrainer.getInt("totalAttempts"));
        stats.setCorrectCount(jsonTrainer.getInt("correctAttempts"));
        stats.setWrongCount(jsonTrainer.getInt("wrongAttempts"));
        deck.setStatistics(stats);

        // Load word-image pairs
        JSONArray jsonWordImagePairs = jsonTrainer.getJSONArray("wordImagePairs");
        for (int j = 0; j < jsonWordImagePairs.length(); j++) {
            JSONObject jsonPair = jsonWordImagePairs.getJSONObject(j);
            Flashcard pair = new Flashcard(
                    jsonPair.getString("word"),
                    new URL(jsonPair.getString("imageUrl"))
            );
            deck.addWordImagePair(pair);
        }

        return deck;
    }
}
