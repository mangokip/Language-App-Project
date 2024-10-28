package com.app;

import java.util.Scanner;

import com.narration.Narriator; // Import Narriator class

/*
 * default question class that all question types extend from
 * @param prompt - the prompt for the question
 * @param difficulty - the difficulty level of the question
 * 
 */
public abstract class Question {
    private String prompt;
    private int difficulty;

    // constructor for the question class
    public Question(String prompt, int difficulty) {
        this.prompt = prompt;
        this.difficulty = difficulty;
    }

    // getter for the prompt
    public String getPrompt() {
        return this.prompt;
    }

    // getter for the difficulty
    public int getDifficulty() {
        return this.difficulty;
    }

    // setter for the difficulty
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Pronounces the question using AWS Polly via the Narriator.
     */
    public void pronouncePrompt() {
        try {
            Narriator.playSoundEnglish(prompt);  // Use Narriator to pronounce the prompt
        } catch (Exception e) {
            System.err.println("Error pronouncing the prompt: " + e.getMessage());
        }
    }

    // abstract method for asking the question that all child classes implement
    public abstract boolean askQuestion(Scanner scanner);
    // abstract method for validating the user's answer that all child classes implement
    public abstract boolean validateAnswer(String userAnswer);

    // toString method that returns the prompt
    @Override
    public String toString() {
        return prompt;
    }
}
