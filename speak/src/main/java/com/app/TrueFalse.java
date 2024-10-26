package com.app;

import java.util.Scanner;

public class TrueFalse extends Question {

    private boolean correctTranslation; 

    public TrueFalse(Word word, String correctAnswer, String incorrectAnswer, boolean correctTranslation, int difficulty) {
        super("Is this translation correct? " + word.getText() + " -> " + correctAnswer, difficulty);
        this.correctTranslation = correctTranslation; 
    }

    @Override
    public boolean askQuestion(Scanner scanner) {
        System.out.println(getPrompt());
        System.out.println("1. True");
        System.out.println("2. False");

        System.out.print("Enter your answer (1 or 2): ");
        String userAnswer = scanner.nextLine().trim();

        boolean userSelectedTrue = userAnswer.equals("1"); 

 
        boolean isCorrectAnswer = (userSelectedTrue == correctTranslation);  

        if (isCorrectAnswer) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect.");
            return false;
        }
    }

    @Override
    public boolean validateAnswer(String userAnswer) {
        boolean userSelectedTrue = userAnswer.equals("1");  
        boolean isCorrectAnswer = (userSelectedTrue == correctTranslation);  

        if (isCorrectAnswer) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect.");
        }
        return isCorrectAnswer;
    }
}
