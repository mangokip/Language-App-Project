package com.app;

import java.util.ArrayList;
/**
 * State class
 * @author David Dinh
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

    /**
     * Evaluates the user's performance and determines if they should advance to the next level.
     *
     * @param correctAnswers The number of correct answers given by the user.
     */
    void evaluatePerformance(int correctAnswers);
}