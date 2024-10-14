package org.gfrolik;

import static org.junit.jupiter.api.Assertions.*; // JUnit 5

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class FlashcardDeckRandomTest {

    private FlashcardDeck deck;

    @BeforeEach
    public void setUp() {
        deck = new FlashcardDeck();
    }

    @Test
    public void testSelectRandomCard() throws MalformedURLException {
        deck.addFlashcard(new Flashcard(new URL("https://example.com/image1.jpg"), "word1"));
        deck.addFlashcard(new Flashcard(new URL("https://example.com/image2.jpg"), "word2"));

        deck.selectRandomCard();
        assertNotNull(deck.getCurrentCard()); // Ensure that a card is selected
        assertTrue(deck.getFlashcards().contains(deck.getCurrentCard())); // The selected card should be from the deck
    }
}
