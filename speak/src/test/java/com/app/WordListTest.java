package com.app;

import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordListTest {

    private WordList wordList;

    @BeforeEach
    public void setUp() {
        // Print statement to verify file path
        System.out.println("Attempting to access: " + getClass().getResource("/words.json"));
    
        // Create WordList instance to load data
        wordList = WordList.getInstance("/words.json");
    }

    @Test
    public void testFileExists() {
        InputStream fileStream = getClass().getResourceAsStream("/words.json");
        assertNotNull(fileStream, "words.json file is not found in resources.");
        System.out.println("words.json file loaded successfully.");
    }


    @Test
    void testGetInstance() {
        assertNotNull(wordList, "WordList instance should not be null");
    }

    @Test
    void testLanguageWordsAfterLoad() {
        // Assuming "Spanish" is a valid language code in words.json
        List<Word> spanishWords = wordList.getLanguageWords("Spanish");
        assertNotNull(spanishWords, "Language words should not be null");
        assertFalse(spanishWords.isEmpty(), "Language words should not be empty after loading");
    }

    @Test
    void testGetRandomWordByLanguage() {
        Word randomWord = wordList.getRandomWord("Spanish");
        assertNotNull(randomWord, "Random word should not be null for the given language");
    }

    @Test
    void testGetWordsByGenre() {
        Language spanishLanguage = new Language("Spanish");
        List<Word> nouns = wordList.getWordsByGenre(spanishLanguage, Genre.NOUN);
        assertNotNull(nouns, "Genre-specific word list should not be null");
        assertFalse(nouns.isEmpty(), "Genre-specific word list should contain words");
    }
}
