package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * Class for a multiple choice question.
 * @param answerOptions - List of Word objects that represent the possible answers
 * @param correctAnswer - Word object that represents the correct answer
 */
public class MultipleChoice extends Question {

    private List<Word> answerOptions;
    private Word correctAnswer;

    /*
     * Constructor for a multiple choice question.
     * @param difficulty - The difficulty level of the multiple choice question
     * @param correctAnswer - The correct answer for the multiple choice question
     * @param language - The language to be used for the multiple choice question
     */
    public MultipleChoice(int difficulty, Word correctAnswer, Language language) {
        super("Select the foreign word that matches this English word: " + correctAnswer.getText(), difficulty);
        this.correctAnswer = correctAnswer;
        this.answerOptions = new ArrayList<>();
        populateAnswerOptions(language);
    }

    /*
     * Populates the answer options list with random words from the passed language. then shuffles th the list
     * @param language - The language to be used for the multiple choice question
     */
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

    /*
     * Prints the question and the answer options and asks the user to select an answer
     * this method is used in the lesson class to ask each quesiton
     */
    @Override
    public boolean askQuestion(Scanner scanner) {
        pronouncePrompt();  // Pronounce the question before displaying

        System.out.println(getPrompt());

        for (int i = 0; i < answerOptions.size(); ++i) {
            System.out.println((i + 1) + ". " + answerOptions.get(i).getForeign());
        }

        System.out.print("Enter your answer (1-4): ");
        String userAnswer = scanner.nextLine().trim();

        boolean result = validateAnswer(userAnswer);
        if (result) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect. The correct answer is: " + correctAnswer.getForeign());
        }
        return result;
    }

    //function to validate the user's answer based on the answer options list and the correct answer
    // returns true if the user's answer is correct and false if it is not
    @Override
    public boolean validateAnswer(String userAnswer) {
        try {
            int answerIndex = Integer.parseInt(userAnswer) - 1;
            return answerOptions.get(answerIndex).equals(correctAnswer);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            return false;
        }
    }

    public List<Word> getAnswerOptions() {
        return answerOptions;
    }
}
