<<<<<<< HEAD
  package com.app;
=======
package com.app;

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
    }

    // Method to check the user's answer (True/False)
    public boolean checkAnswer(String answer) {
        try {
            boolean userAnswer = Boolean.parseBoolean(answer.toLowerCase());
            return userAnswer == isCorrectTranslation;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter 'True' or 'False'.");
            return false;  // Invalid input should return false for incorrect answer
        }
    }

    @Override
    public String toString() {
        return this.getPrompt();
    }
}