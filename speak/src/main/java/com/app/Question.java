package com.app;

import java.util.Scanner;

import com.narration.Narriator; // Import Narriator class

public abstract class Question {
    private String prompt;
    private int difficulty;

    public Question(String prompt, int difficulty) {
        this.prompt = prompt;
        this.difficulty = difficulty;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

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

    public abstract boolean askQuestion(Scanner scanner);
    public abstract boolean validateAnswer(String userAnswer);

    @Override
    public String toString() {
        return prompt;
    }
}
