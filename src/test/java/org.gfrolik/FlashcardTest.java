package org.gfrolik;

import static org.junit.jupiter.api.Assertions.*; // JUnit 5
import org.gfrolik.Flashcard;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;

public class FlashcardTest {

    @Test
    public void testIsValid_ValidURLAndWord() throws MalformedURLException {
        Flashcard flashcard = new Flashcard(new URL("https://example.com/image.jpg"), "word");
        assertTrue(flashcard.isValid(new URL("https://example.com/image.jpg"), "word"));
    }

    @Test
    public void testIsValid_InvalidURL() throws MalformedURLException {
        Flashcard flashcard = new Flashcard(new URL("ht:/invalid-url"), "word");
        assertFalse(flashcard.isValid(new URL("ht:/invalid-url"), "word"));
    }

    @Test
    public void testIsValid_NullWord() throws MalformedURLException {
        Flashcard flashcard = new Flashcard(new URL("https://example.com/image.jpg"), null);
        assertFalse(flashcard.isValid(new URL("https://example.com/image.jpg"), null));
    }
}
