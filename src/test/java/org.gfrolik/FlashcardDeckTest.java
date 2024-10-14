package org.gfrolik;

import static org.junit.jupiter.api.Assertions.*; // JUnit 5
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
public class FlashcardDeckTest {

    private FlashcardDeck deck;

    @BeforeEach
    public void setUp() {
        deck = new FlashcardDeck();
    }

    @Test
    public void testAddFlashcard() throws MalformedURLException {
        Flashcard flashcard = new Flashcard(new URL("https://example.com/image1.jpg"), "word1");
        deck.addFlashcard(flashcard);
        assertEquals(1, deck.getFlashcards().size());
    }

}
