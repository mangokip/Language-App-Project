package com.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MultipleChoice extends Question {

    private List<Word> answerOptions;
    private Word correctAnswer;

    public MultipleChoice(int difficulty, Word correctAnswer, Language language) {
        super("Select the foreign word that matches this English word: " + correctAnswer.getText(), difficulty);
        this.correctAnswer = correctAnswer;
        this.answerOptions = new ArrayList<>();
        populateAnswerOptions(language);
    }

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

    @Override
    public boolean askQuestion(Scanner scanner) {
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
}
