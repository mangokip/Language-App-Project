package com.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PhraseListTest {

    private PhraseList phraseList;

    @BeforeEach
    void setUp() {
        phraseList = PhraseList.getInstance(); // This should internally call loadPhrases
    }

    @Test
    void testGetInstance() {
        assertNotNull(phraseList, "PhraseList instance should not be null");
    }

    @Test
    void testGetPhrasesAfterLoad() {
        assertNotNull(phraseList.getPhrases(), "Phrases list should not be null");
        assertFalse(phraseList.getPhrases().isEmpty(), "Phrases list should not be empty after loading");
    }
}
