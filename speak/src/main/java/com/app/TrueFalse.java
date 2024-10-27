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

/*
 * Class for a true-false question.
 * @param word - The word to be used for the true-false question
 * @param correctTranslation - Whether the translation is correct or not
 * @param difficulty - The difficulty level of the true-false question
 */
public class TrueFalse extends Question {

    private boolean correctTranslation;
    private Word word;
    private String displayedAnswer;

    /*
     * Constructor for a true-false question.
     * @param word - The word to be used for the true-false question
     * @param correctTranslation - Whether the translation is correct or not
     * @param difficulty - The difficulty level of the true-false question
     */
    public TrueFalse(Word word, boolean correctTranslation, int difficulty) {
        super("Is this translation correct? " + word.getText() + " -> " + 
              (correctTranslation ? word.getForeign() : getRandomIncorrectTranslation(word)), 
              difficulty);

        this.word = word;
        this.correctTranslation = correctTranslation;
    }

    /*
     * method for asking the question used in the lesson class
     * 
     */
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

    //method for validating the answer used in the askQuestion method
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

    //method for getting a random incorrect translation if the answer is false and the translation is incorrect
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