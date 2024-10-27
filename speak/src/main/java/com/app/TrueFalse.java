package com.app;

import java.util.Scanner;

public class TrueFalse extends Question {

    private boolean correctTranslation;
    private Word word;
    private String displayedAnswer;

    public TrueFalse(Word word, boolean correctTranslation, int difficulty) {
        super("Is this translation correct? " + word.getText() + " -> " + 
              (correctTranslation ? word.getForeign() : getRandomIncorrectTranslation(word)), 
              difficulty);

        this.word = word;
        this.correctTranslation = correctTranslation;
    }

    @Override
    public boolean askQuestion(Scanner scanner) {
        pronouncePrompt();  // Pronounce the question before displaying it

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
            System.out.println("Incorrect. The correct translation is: " + word.getForeign());
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
            System.out.println("Incorrect. The correct translation is: " + word.getForeign());
        }
        return isCorrectAnswer;
    }

    private static String getRandomIncorrectTranslation(Word correctWord) {
        WordList wordList = WordList.getInstance();
        String incorrectTranslation;

        // Keep generating random words until the translation is different from the correct one
        do {
            Word randomWord = wordList.getRandomWord("Spanish");  // Adjust if needed to use specific language
            incorrectTranslation = randomWord.getForeign();
        } while (incorrectTranslation.equals(correctWord.getForeign()));

        return incorrectTranslation;
    }

}
