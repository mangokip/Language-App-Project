package com.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Array;

import java.util.Scanner;


public class TrueFalseTest {
    WordList wordList = WordList.getInstance("/words.json");
    Language language = new Language("Spanish");
    Word word = wordList.getRandomWord(language);



    //this one just runs infinitely so i commented it out

    // @Test
    // public void testTrueFalse() {
    //     TrueFalse trueFalse = new TrueFalse(word,   true, 1);
    //     assertEquals(trueFalse.askQuestion(new Scanner(System.in)), true);
    // }

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

    @Test
    public void testImproperInput() {
        TrueFalse trueFalse = new TrueFalse(word,   true, 1);
        assertEquals(trueFalse.validateAnswer("w"), false);
    }

    @Test
    public void testEmptyInput() {
        TrueFalse trueFalse = new TrueFalse(word,   true, 1);
        assertEquals(trueFalse.validateAnswer(""), false);
    }

    @Test
    public void testNumberOutOfRange() {
        TrueFalse trueFalse = new TrueFalse(word,   true, 1);
        assertEquals(trueFalse.validateAnswer("10"), false);
    }


    //ignore these i forgot how the question worked for a minute lol

//     @Test
//     public void testCapitalLetterInput(){
//         TrueFalse trueFalse = new TrueFalse(word,   true, 1);
//         assertEquals(trueFalse.validateAnswer(word.getForeign().toUpperCase()), false);
//     }

//     @Test
//     public void testEmptyInput(){
//         TrueFalse trueFalse = new TrueFalse(word,   true, 1);
//         assertEquals(trueFalse.validateAnswer(""), false);
//     }

//     @Test
//     public void testValidAnswerWithSpaces(){
//         TrueFalse trueFalse = new TrueFalse(word,   true, 1);
//         assertEquals(trueFalse.validateAnswer(word.getForeign() + " "), true);
//     }


//     //also infinitely runs because its not simulating the user input, but the method itself doesnt really
//     //need to be tested i dont think because it just prints and reads user input and passes it to the validateAnswer method.

//     // @Test
//     // public void testAskQuestion() {
//     //     TrueFalse trueFalse = new TrueFalse(word,   true, 1);
//     //     assertDoesNotThrow(() -> trueFalse.askQuestion(new Scanner(System.in)), "Asking question should not throw exception");
//     // }
    
}
