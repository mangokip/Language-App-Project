package com.app;

import java.util.ArrayList;

public class BeginnerState implements State {
    private ArrayList<Question> questionPool;
    private Language currentLanguage;

    /**
     * Constructs a new BeginnerState object.
     */
    public BeginnerState() {
        this.questionPool = new ArrayList<>();
        this.currentLanguage = null;
        // Constructor implementation
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseLevel() {
        // Implementation for increasing level
        questionPool.clear();
        System.out.println("Progressing to Intermediate level");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLevel() {
        // Implementation for decreasing level
        System.out.println("Already at Beginner level - cannot decrease further");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Question> getQuestionPool() {
        // Implementation to get question pool for beginner level
        if (questionPool.isEmpty() && currentLanguage != null) {
            loadContent(currentLanguage);
        }
        return new ArrayList<>(questionPool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadContent(Language language) {
        if (language == null){
            throw new IllegalArgumentException("Language cannot be null");
        }
        // Implementation to load beginner content for the given language

        this.currentLanguage = language;
        questionPool.clear();

        Question sampleQuestion = new Question();// I need a parameters for creating a beginner question
        questionPool.add(sampleQuestion);

        System.out.println("Loaded beginner content for: " + language);
    }

    @Override
    public String toString() {
        return "BEGINNER";
    }
}

/**
 * Represents the intermediate level state in the CockySpeak application.
 */
