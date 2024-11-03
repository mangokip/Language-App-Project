package com.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class LanguageTest {

    private Language language;

    @BeforeEach
    public void setUp() {
        // Set up a Language instance with the code "es" for Spanish
        language = new Language("es");
    }

    @AfterEach
    public void tearDown() {
        // Reset the Language instance to ensure no state leaks between tests
        language = null;
    }

    @Test
    public void testGetLanguageCode() {
        assertEquals("es", language.getLanguageCode(), "Language code should be 'es'");
    }

    @Test
    public void testSetLanguageCode() {
        language.setLanguageCode("en");
        assertEquals("en", language.getLanguageCode(), "Language code should be 'en' after setting");
    }

    @Test
    public void testAddGrammarRule() {
        language.addGrammarRule("Subject-Verb-Object order");
        List<String> rules = language.getGrammarRules();
        assertEquals(1, rules.size(), "There should be one grammar rule");
        assertEquals("Subject-Verb-Object order", rules.get(0), "The grammar rule should match what was added");
    }

    @Test
    public void testAddGrammarRuleThrowsExceptionForNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> language.addGrammarRule(null), "Should throw exception for null rule");
        assertThrows(IllegalArgumentException.class, () -> language.addGrammarRule(""), "Should throw exception for empty rule");
    }

    @Test
     public void testDisplayContent() {
        // Capture output for validation if needed, or just ensure no exceptions are thrown
        assertDoesNotThrow(() -> language.displayContent(), "Display content should execute without throwing exceptions");
    }

    @Test
    public void testEquality() {
        Language sameLanguage = new Language("es");
        Language differentLanguage = new Language("en");
        
        assertEquals(language, sameLanguage, "Languages with the same code should be equal");
        assertNotEquals(language, differentLanguage, "Languages with different codes should not be equal");
    }

    @Test
    public void testHashCode() {
        Language sameLanguage = new Language("es");
        
        assertEquals(language.hashCode(), sameLanguage.hashCode(), "Hash codes should match for languages with the same code");
    }
    
}
