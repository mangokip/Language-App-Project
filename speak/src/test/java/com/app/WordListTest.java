package com.app;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach; // Ensure this import is present
import org.junit.jupiter.api.Test;

class WordListTest {

    private WordList wordList;

    @BeforeEach
    void setUp() {
        wordList = WordList.getInstance(); // Indirectly calls loadWords()
    }

    @Test
    void testGetInstance() {
        assertNotNull(wordList, "WordList instance should not be null");
    }

    @Test
    void testLanguageWordsAfterLoad() {
        // Assuming "Spanish" is a valid language code in words.json
        assertNotNull(wordList.getLanguageWords("es"), "Language words should not be null");
        assertFalse(wordList.getLanguageWords("es").isEmpty(), "Language words should not be empty after loading");
    }

    @Test
    void testGetRandomWordByLanguage() {
        Word randomWord = wordList.getRandomWord("es");
        assertNotNull(randomWord, "Random word should not be null for given language");
    }

    @Test
    void testGetWordsByGenre() {
        Language spanishLanguage = new Language("es");
        List<Word> nouns = wordList.getWordsByGenre(spanishLanguage, Genre.NOUN);
        assertNotNull(nouns, "Genre-specific word list should not be null");
        assertFalse(nouns.isEmpty(), "Genre-specific word list should contain words");
    }
}
