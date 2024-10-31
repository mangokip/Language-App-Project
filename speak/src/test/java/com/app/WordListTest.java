package com.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;

class WordListTest {

    private WordList wordList;

    @BeforeEach
    void setUp() throws Exception {
        wordList = new WordList();
        wordList.loadWords("./json/words.json");
    }

    @AfterEach
    void tearDown() {
        wordList = null;
    }

    @Test
    void testWordListNotEmpty() {
        assertFalse(wordList.getWords().isEmpty(), "Word list should not be empty");
    }

    @Test
    void testGenreExists() {
        wordList.getWords().forEach(word -> {
            assertNotNull(word.getGenre(), "Each word should have a genre");
        });
    }

    @Test
    void testDifficultyLevel() {
        wordList.getWords().forEach(word -> {
            assertTrue(word.getDifficulty() > 0, "Difficulty should be a positive integer");
        });
    }

    @Test
    void testNoDuplicateWords() {
        List<String> texts = wordList.getWords().stream()
                .map(Word::getText)
                .collect(Collectors.toList());
        assertEquals(texts.size(), new HashSet<>(texts).size(), "There should be no duplicate words");
    }
}
