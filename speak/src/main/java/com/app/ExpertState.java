package com.app;

import java.util.ArrayList;

public class ExpertState implements State {
    private ArrayList<Question> questionPool;
    private Language currentLanguage;
    /**
     * Constructs a new ExpertState object.
     */
    public ExpertState() {
        // Constructor implementation
        this.questionPool = new ArrayList<>();
        this.currentLanguage = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseLevel() {

        System.out.println("Already at Expert level - cannot increase further");
        // Implementation for increasing level
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLevel() {
        // Implementation for decreasing level
        questionPool.clear();
        System.out.println("Returning to Intermediate level");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Question> getQuestionPool() {
        // Implementation to get question pool for expert level
        if (questionPool.isEmpty() && currentLanguage != null) {
            loadContent(currentLanguage);
        }
        return new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadContent(Language language) {
        if (language == null){
            throw new IllegalArgumentException("Language cannot be null");
        }
        this.currentLanguage = language;
        questionPool.clear();
        System.out.println("Loading expert content for " + language);
        // Implementation to load expert content for the given language
    }

    @Override
    public String toString() {
        return "EXPERT";
    }
}