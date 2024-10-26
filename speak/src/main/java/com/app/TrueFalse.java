<<<<<<< HEAD
  package com.app;
=======
package com.app;

<<<<<<< HEAD
public class TrueFalse extends Question {
    private String word;
    private String translation;
    private boolean isCorrectTranslation;
>>>>>>> d076aa40294398392203c9019a0a9b95fbb19936

  public class TrueFalse extends Question {
    private Word word;  // The Word object (contains English and Spanish translations)
    private String translation;  // The correct or incorrect translation
    private boolean isCorrectTranslation;  // Whether the translation shown is correct

    // Constructor to initialize TrueFalse question with a Word object
    public TrueFalse(Word word, String incorrectTranslation, boolean isCorrect, int difficulty) {
        super("Is this translation correct? " + word.getText() + " -> " + (isCorrect ? word.getForeign() : incorrectTranslation), difficulty);
        this.word = word;
        this.translation = isCorrect ? word.getForeign() : incorrectTranslation;
        this.isCorrectTranslation = isCorrect;
=======
import java.util.Scanner;

public class TrueFalse extends Question {

    private boolean correctTranslation; 

    public TrueFalse(Word word, String correctAnswer, String incorrectAnswer, boolean correctTranslation, int difficulty) {
        super("Is this translation correct? " + word.getText() + " -> " + correctAnswer, difficulty);
        this.correctTranslation = correctTranslation; 
>>>>>>> f76a5091e1cbe1cfb8998e4e728117936cdfab9e
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