package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhraseTest {

    private Phrase phrase;
    private Word correctWord;

    @BeforeEach
    public void setUp() {
        phrase = new Phrase("How are you?", "¿Cómo estás?", "hau ar yu");
        correctWord = new Word("estás", "you are", "eh-stahs", Genre.VERB, 2); // Example Word
    }

    @Test
    public void testGetText() {
        assertEquals("How are you?", phrase.getText());
    }

    @Test
    public void testGetTranslation() {
        assertEquals("¿Cómo estás?", phrase.getTranslation());
    }

    @Test
    public void testGetPronunciation() {
        assertEquals("hau ar yu", phrase.getPronunciation());
    }

    @Test
    public void testWithBlankReplacesCorrectWord() {
        String result = phrase.withBlank(correctWord);
        assertEquals("How are you?", result); // Assuming the foreign word is "estás"
    }

    @Test
    public void testWithBlankDoesNotReplaceNonExistentWord() {
        Word nonExistentWord = new Word("hello", "hola", "heh-lo", Genre.VERB, 1);
        String result = phrase.withBlank(nonExistentWord);
        assertEquals("How are you?", result);
    }
}
