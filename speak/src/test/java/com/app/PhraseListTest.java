package com.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;

class PhraseListTest {

    private PhraseList phraseList;

    @BeforeEach
    void setUp() throws Exception {
        phraseList = new PhraseList();
        phraseList.loadPhrases("./json/phrases.json");
    }

    @AfterEach
    void tearDown() {
        phraseList = null;
    }

    @Test
    void testPhraseListNotEmpty() {
        assertFalse(phraseList.getPhrases().isEmpty(), "Phrase list should not be empty");
    }

    @Test
    void testTranslationExists() {
        phraseList.getPhrases().forEach(phrase -> {
            assertNotNull(phrase.getTranslation(), "Each phrase should have a translation");
        });
    }

    @Test
    void testPronunciationExists() {
        phraseList.getPhrases().forEach(phrase -> {
            assertNotNull(phrase.getPronunciation(), "Each phrase should have a pronunciation");
        });
    }

    @Test
    void testNoDuplicatePhrases() {
        List<String> texts = phraseList.getPhrases().stream()
                .map(Phrase::getText)
                .collect(Collectors.toList());
        assertEquals(texts.size(), new HashSet<>(texts).size(), "There should be no duplicate phrases");
    }
}
