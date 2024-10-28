package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * Class for a fill-in-the-blank question.
 * @param phrase - The phrase to be used for the fill-in-the-blank question
 * @param correctAnswer - The correct answer for the fill-in-the-blank question
 * @param answerOptions - List of Word objects that represent the possible answers
 */
public class FillBlank extends Question {

    private Phrase phrase;
    private Word correctAnswer;
    private List<Word> answerOptions;

    /*
     * Constructor for a fill-in-the-blank question.
     * @param difficulty - The difficulty level
     * @param correctAnswer - The correct answer
     * @param language - The language to bee used
     * @param phrase - The phrase to be used
     */
    public FillBlank(int difficulty, Word correctAnswer, Language language, Phrase phrase) {
        super("Select the answer choice that best completes the sentence:", difficulty);
        this.correctAnswer = correctAnswer;
        this.phrase = phrase;
        this.answerOptions = new ArrayList<>();
        populateAnswerOptions(language);
    }

    //Populates the answer options list with random words from the passed language with the same genre. then shuffles th the list
    //@param language - The language to be used for the fill-in-the-blank question
    private void populateAnswerOptions(Language language) {
        WordList wordList = WordList.getInstance();
        List<Word> genreWords = wordList.getWordsByGenre(language, correctAnswer.getGenre());

        answerOptions.add(correctAnswer);
        Random rand = new Random();
        while (answerOptions.size() < 4) {
            Word randomWord = genreWords.get(rand.nextInt(genreWords.size()));
            if (!answerOptions.contains(randomWord)) {
                answerOptions.add(randomWord);
            }
        }
        Collections.shuffle(answerOptions);
    }

    //function to ask the question and validate the user's answer
    @Override
    public boolean askQuestion(Scanner scanner) {
        pronouncePrompt();  // Pronounce the phrase before displaying

        if (phrase != null) {
            System.out.println(phrase.getText());

            for (int i = 0; i < answerOptions.size(); ++i) {
                System.out.println((i + 1) + ". " + answerOptions.get(i).getForeign());
            }

            System.out.print("Enter your answer (1-4): ");
            String userAnswer = scanner.nextLine().trim();

            return validateAnswer(userAnswer);
        } else {
            System.out.println("No suitable phrase found.");
            return false;
        }
    }

    //function to validate the user's answer based on the answer options list and the correct answer
    // returns true if the user's answer is correct and false if it is not
    @Override
    public boolean validateAnswer(String userAnswer) {
        try {
            int answerIndex = Integer.parseInt(userAnswer) - 1;
            boolean isCorrect = answerOptions.get(answerIndex).equals(correctAnswer);

            if (isCorrect) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + correctAnswer.getForeign());
            }
            return isCorrect;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            return false;
        }
    }
}
