package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.Language;
import com.app.MultipleChoice;
import com.app.Word;
import com.app.WordList;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Array;

import java.util.Scanner;

public class MultipleChoiceTest {
    private MultipleChoice multipleChoice;
    private Word correctAnswer;
    private Language language;
    private WordList wordList = WordList.getInstance("/words.json");

    @BeforeEach
    void setUp() {
        language = new Language("Spanish");
        correctAnswer = wordList.getRandomWord(language);
        multipleChoice = new MultipleChoice(1, correctAnswer, language);
    }

    @Test
    void testValidateCorrectAnswer() {
        int correctIndex = multipleChoice.getAnswerOptions().indexOf(correctAnswer);
        assertTrue(multipleChoice.validateAnswer(String.valueOf(correctIndex + 1)));
    }

    @Test
    void testValidateIncorrectAnswer() {
        int incorrectIndex = (multipleChoice.getAnswerOptions().indexOf(correctAnswer) + 1) % 4;
        assertFalse(multipleChoice.validateAnswer(String.valueOf(incorrectIndex + 1)));
    }

    @Test
    void testValidateEmptyAnswer() {
        assertFalse(multipleChoice.validateAnswer(" "));
    }

    @Test
    void testInvalidInput() {
        assertFalse(multipleChoice.validateAnswer("q"));
    }

    @Test
    void testInputWithSpaces() {
        assertTrue(multipleChoice.validateAnswer(" 1 "));
    }

    @Test void testNumberOutOfRange() {
        assertFalse(multipleChoice.validateAnswer("10"));
    }

    @Test
    public void testPopulateAnswerOptions() {
        assertEquals(4, multipleChoice.getAnswerOptions().size());
    }



    
}
