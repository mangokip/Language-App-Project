package com.app;

import java.util.ArrayList;

/**
 * The State interface defines the contract for all concrete state classes
 * in the CockySpeak application's state pattern implementation.
 */
public interface State {

    /**
     * Increases the difficulty level of the current state.
     */
    void increaseLevel();

    /**
     * Decreases the difficulty level of the current state.
     */
    void decreaseLevel();

    /**
     * Retrieves a pool of questions appropriate for the current state.
     *
     * @return An ArrayList of Question objects suitable for the current
     * difficulty level.
     */
    ArrayList<Question> getQuestionPool();

    /**
     * Loads content specific to the current state and given language.
     *
     * @param language The Language object representing the language for which
     * to load content.
     */
    void loadContent(Language language);
}

/**
 * Represents the beginner level state in the CockySpeak application.
 */
