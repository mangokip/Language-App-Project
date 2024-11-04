package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


class WordTest {

    private Word word;

    @BeforeEach
    void setUp() {
        word = new Word("dog", "perro", "peh-roh", Genre.NOUN, 1);
    }

    @Test
    void testConstructor() {
        assertNotNull(word);
        assertEquals("dog", word.getText());
        assertEquals("perro", word.getForeign());
        assertEquals("peh-roh", word.getPronounce());
        assertEquals(Genre.NOUN, word.getGenre());
        assertEquals(1, word.getDifficulty());
    }

    @Test
    void testSetText() {
        word.setText("cat");
        assertEquals("cat", word.getText());
    }

    @Test
    void testSetForeign() {
        word.setForeign("gato");
        assertEquals("gato", word.getForeign());
    }

    @Test
    void testSetPronounce() {
        word.setPronounce("gah-toh");
        assertEquals("gah-toh", word.getPronounce());
    }

    @Test
    void testSetGenre() {
        word.setGenre(Genre.ADJECTIVE);
        assertEquals(Genre.ADJECTIVE, word.getGenre());
    }

   

    @Test
    void testToString() {
        String expected = "Word{text='dog', foreign='perro', pronounce='peh-roh', genre=NOUN}";
        assertEquals(expected, word.toString());
    }
}
