package com.app;

import java.util.ArrayList;

/**
 * Beginner State
 *
 * @author David Dinh
 */
class BeginnerState implements State {

    @Override
    public void increaseLevel() {
        System.out.println("Level increased! You're now at the Intermediate level.");
    }

    @Override
    public void decreaseLevel() {
        System.out.println("You're already at the Beginner level.");
    }

    @Override
    public ArrayList<Question> getQuestionPool() {
        ArrayList<Question> questions = new ArrayList<>();
        return questions;
    }

    //TODO fix this, I have no clue what to do here
    @Override
    public void loadContent(Language language) {
        /*System.out.println("Loading beginner content for language: " + language.getCode());
        language.addVocabulary(new Word("apple", "manzana", "mah-nzah-nah", Genre.NOUN, 1, false));
        language.addVocabulary(new Word("hello", "hola", "oh-lah", Genre.INTERJECTION, 1, false));
         */
    }

    @Override
    public void evaluatePerformance(int correctAnswers) {
        if (correctAnswers >= 7) {
            increaseLevel();
        } else {
            decreaseLevel();
            System.out.println("Keep practicing to advance to the next level!");
        }
    }

    @Override
    public String toString() {
        return "BEGINNER";
    }
}
