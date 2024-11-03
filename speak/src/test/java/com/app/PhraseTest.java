package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhraseTest {

    private Phrase phrase;
    private Word word;

    @BeforeEach
    public void setUp() {
        // Initialize a phrase and word object for testing
        phrase = new Phrase("El perro corre rápido.", "The dog runs fast.", "el peh-roh koh-reh rah-pee-doh");
        word = new Word("perro", "dog", "peh-roh", "noun");
    }

    @Test
    public void testGetText() {
        assertEquals("El perro corre rápido.", phrase.getText(), "The original text should match.");
    }

    @Test
    public void testGetTranslation() {
        assertEquals("The dog runs fast.", phrase.getTranslation(), "The translation should match.");
    }

    @Test
    public void testGetPronunciation() {
        assertEquals("el peh-roh koh-reh rah-pee-doh", phrase.getPronunciation(), "The pronunciation should match.");
    }

    @Test
    public void testWithBlankReplacesWord() {
        String result = phrase.withBlank(word);
        assertEquals("El ______ corre rápido.", result, "The word 'perro' should be replaced with a blank.");
    }

    @Test
    public void testWithBlankWordNotInPhrase() {
        Word unrelatedWord = new Word("gato", "cat", "gah-toh", "noun");
        String result = phrase.withBlank(unrelatedWord);
        assertEquals("El perro corre rápido.", result, "The phrase should remain unchanged if the word is not present.");
    }

    @Test
    public void testWithBlankHandlesNullWord() {
        assertThrows(IllegalArgumentException.class, () -> phrase.withBlank(null), "Should throw IllegalArgumentException if the word is null.");
    }

    @Test
    public void testWithBlankHandlesNullForeignText() {
        Word nullForeignWord = new Word(null, "house", "pronunciation", "noun");
        assertThrows(IllegalArgumentException.class, () -> phrase.withBlank(nullForeignWord), "Should throw IllegalArgumentException if the foreign text is null.");
    }

    @Test
    public void testToString() {
        String expected = "Phrase{text='El perro corre rápido.', translation='The dog runs fast.', pronunciation='el peh-roh koh-reh rah-pee-doh'}";
        assertEquals(expected, phrase.toString(), "The toString method should return the correct string representation.");
    }
}
