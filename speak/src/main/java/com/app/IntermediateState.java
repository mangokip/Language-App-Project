package com.app;

import java.util.ArrayList;

/**
 * intermediate State
 *
 * @author David Dinh
 */
class IntermediateState implements State {

    @Override
    public void increaseLevel() {
        System.out.println("Level increased! You're now at the Expert level.");
        // Logic to transition to ExpertState can be implemented here.
    }

    @Override
    public void decreaseLevel() {
        System.out.println("Level decreased! You're now at the Beginner level.");
        // Logic to transition to BeginnerState can be implemented here.
    }

    @Override
    public ArrayList<Question> getQuestionPool() {
        ArrayList<Question> questions = new ArrayList<>();
        return questions;
    }

    //TODO Fix Load content
    @Override
    public void loadContent(Language language) {
        /* System.out.println("Loading intermediate content for language: " + language.getCode());
        language.addVocabulary(new Word("ate", "comí", "koh-mee", Genre.VERB, 2, false));
        language.addVocabulary(new Word("children", "niños", "nee-nyos", Genre.NOUN, 2, false));
        language.addGrammarRule("Use 'comí' for 'I ate' in past tense.");
         */
    }

    @Override
    public void evaluatePerformance(int correctAnswers) {
        if (correctAnswers >= 7) {
            increaseLevel();
        } else {
            System.out.println("Keep practicing to advance to the next level!");
        }
    }

    @Override
    public String toString() {
        return "INTERMEDIATE";
    }
}
