package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhraseTest {

    private Phrase phrase1;
    private Phrase phrase2;
    private Phrase phrase3;

    @BeforeEach
    public void setUp() {
        phrase1 = new Phrase("El perro corre rápido.", "The dog runs fast.", "el peh-roh koh-reh rah-pee-doh");
        phrase2 = new Phrase("El gato duerme en la casa.", "The cat sleeps in the house.", "el gah-toh dwehr-meh en lah kah-sah");
        phrase3 = new Phrase("La mujer está feliz hoy.", "The woman is happy today.", "lah moo-hehr es-tah feh-lees oy");
    }

    @Test
    public void testGetText() {
        assertEquals("El perro corre rápido.", phrase1.getText());
        assertEquals("El gato duerme en la casa.", phrase2.getText());
        assertEquals("La mujer está feliz hoy.", phrase3.getText());
    }

    @Test
    public void testGetTranslation() {
        assertEquals("The dog runs fast.", phrase1.getTranslation());
        assertEquals("The cat sleeps in the house.", phrase2.getTranslation());
        assertEquals("The woman is happy today.", phrase3.getTranslation());
    }

    @Test
    public void testGetPronunciation() {
        assertEquals("el peh-roh koh-reh rah-pee-doh", phrase1.getPronunciation());
        assertEquals("el gah-toh dwehr-meh en lah kah-sah", phrase2.getPronunciation());
        assertEquals("lah moo-hehr es-tah feh-lees oy", phrase3.getPronunciation());
    }

    @Test
    public void testWithBlankReplacesCorrectWord() {
        Word correctWord = new Word("perro", "dog", "peh-roh", Genre.NOUN, 1); // Assuming this word matches with "perro"
        String result = phrase1.withBlank(correctWord);
        assertEquals("El ______ corre rápido.", result); // Check if "perro" was replaced with a blank
    }

    @Test
    public void testWithBlankDoesNotReplaceNonExistentWord() {
        Word nonExistentWord = new Word("gato", "cat", "gah-toh", Genre.NOUN, 1); // This should not affect phrase1
        String result = phrase1.withBlank(nonExistentWord);
        assertEquals("El perro corre rápido.", result); // Ensure original phrase remains unchanged
    }
}
