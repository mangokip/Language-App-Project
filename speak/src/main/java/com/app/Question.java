package com.app;

import java.util.Scanner;

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
     * Abstract method to ask the question.
     * Must be implemented by all subclasses.
     */
    public abstract boolean askQuestion(Scanner scanner);  // Changed to boolean

    /**
     * Abstract method to validate the answer.
     * Must be implemented by all subclasses.
     */
    public abstract boolean validateAnswer(String userAnswer);  // Ensure consistency

    @Override
    public String toString() {
        return prompt;
    }
}
