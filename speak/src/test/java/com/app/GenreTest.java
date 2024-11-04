package com.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Genre Testing
 * @author David Dinh
 */

class GenreTest {

    @Test
    void testNounGenre() {
        assertEquals("NOUN", Genre.NOUN.toString(), "Genre should be NOUN");
    }

    @Test
    void testVerbGenre() {
        assertEquals("VERB", Genre.VERB.toString(), "Genre should be VERB");
    }

    @Test
    void testAdjectiveGenre() {
        assertEquals("ADJECTIVE", Genre.ADJECTIVE.toString(), "Genre should be ADJECTIVE");
    }

    @Test
    void testAdverbGenre() {
        assertEquals("ADVERB", Genre.ADVERB.toString(), "Genre should be ADVERB");
    }

    @Test
    void testPrepositionGenre() {
        assertEquals("PREPOSITION", Genre.PREPOSITION.toString(), "Genre should be PREPOSITION");
    }

    @Test
    void testConjunctionGenre() {
        assertEquals("CONJUNCTION", Genre.CONJUNCTION.toString(), "Genre should be CONJUNCTION");
    }

    @Test
    void testInterjectionGenre() {
        assertEquals("INTERJECTION", Genre.INTERJECTION.toString(), "Genre should be INTERJECTION");
    }
}
