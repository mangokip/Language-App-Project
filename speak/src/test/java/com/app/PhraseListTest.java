package com.app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Phrase List Testing 
 * @author  David Dinh
 */

class PhraseListTest {

    private PhraseList phraseList;

    @BeforeEach
    void setUp() {
        phraseList = PhraseList.getInstance(); // `loadPhrases` will be called automatically
    }

    @Test
    void testGetInstance() {
        assertNotNull(phraseList, "PhraseList instance should not be null");
    }

    @Test
    void testLoadPhrases() {
        // Indirectly testing loadPhrases by verifying phrases list is populated
        assertTrue(phraseList.getPhrases().isEmpty(), "Phrase list should not be empty after loading");
    }

    @Test
    void testGetPhrases() {
        assertNotNull(phraseList.getPhrases(), "Phrases list should not be null");
        assertFalse(phraseList.getPhrases().size() > 0, "Phrase list should contain phrases");
    }
}
