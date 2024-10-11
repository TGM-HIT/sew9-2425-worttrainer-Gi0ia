package org.gfrolik;

import java.io.IOException;

/**
 * Flashcard deck
 * @author gioia
 * @version 2024-09-30
 */
public interface SaveLoad {
    void save(FlashcardDeck trainer, String filePath) throws IOException;
    FlashcardDeck load(String filePath) throws IOException;
}
