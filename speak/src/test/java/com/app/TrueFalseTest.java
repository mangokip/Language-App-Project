package com.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Array;

import java.util.Scanner;


public class TrueFalseTest {
    WordList wordList = WordList.getInstance("/resources/words.json");
    Language language = new Language("Spanish");
    Word word = wordList.getRandomWord(language);



    @Test
    public void testTrueFalse() {
        TrueFalse trueFalse = new TrueFalse(word,   true, 1);
        assertEquals(trueFalse.askQuestion(new Scanner(System.in)), true);
    }

    @Test
    public void testTrueAnswer() {
        TrueFalse trueFalse = new TrueFalse(word,   true, 1);
        assertEquals(trueFalse.validateAnswer("1"), true);
    }
    @Test
    public void testFalseAnswer() {
        TrueFalse trueFalse = new TrueFalse(word,   false, 1);
        assertEquals(trueFalse.validateAnswer("1"), false);
    }
    
}
