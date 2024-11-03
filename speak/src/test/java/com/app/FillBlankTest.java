package com.app;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FillBlankTest {

    private FillBlank fillBlank;

    @BeforeEach
    void setUp() {
        Word correctAnswer = new Word("run", "correr", "verb", Genre.VERB, 1);
        Phrase phrase = new Phrase("El perro ____ rápido.", "The dog ____ fast.", "el perro rápido");
        Language spanishLanguage = new Language("es"); // Language code for Spanish

        fillBlank = new FillBlank(1, correctAnswer, spanishLanguage, phrase);
    }

    @Test
    void testAskQuestion() {
        // Indirectly testing answer options by checking askQuestion's behavior
        assertDoesNotThrow(() -> fillBlank.askQuestion(new Scanner(System.in)), "Asking question should not throw exception");
    }

    @Test
    void testValidateAnswerCorrect() {
        assertTrue(fillBlank.validateAnswer("1"), "Correct answer should return true");
    }

    @Test
    void testValidateAnswerIncorrect() {
        assertFalse(fillBlank.validateAnswer("q"), "Incorrect answer should return false");
    }
}
