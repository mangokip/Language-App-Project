package com.app;

import java.util.ArrayList;

/**
 * Expert state
 *
 * @author David Dinh
 */
class ExpertState implements State {

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseLevel() {
        System.out.println("You're already at the highest level: Expert.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseLevel() {
        System.out.println("Level decreased! You're now at the Intermediate level.");
        // Logic to transition to IntermediateState can be implemented here.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Question> getQuestionPool() {
        ArrayList<Question> questions = new ArrayList<>();
        return questions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadContent(Language language) {
        /* 
        System.out.println("Loading expert content for language: " + language.getCode());
        language.addVocabulary(new Word("to be (subjunctive)", "sea", "seh-ah", Genre.VERB, 3, false));
        language.addVocabulary(new Word("philosophy", "filosofía", "fee-loh-soh-fee-ah", Genre.NOUN, 3, false));
        language.addGrammarRule("Use 'sea' for subjunctive form of 'to be' in Spanish.");
         */
    }

    @Override
    public void evaluatePerformance(int correctAnswers) {
        if (correctAnswers >= 7) {
            System.out.println("Congratulations! You've mastered the Expert level!g");
        } else {
            System.out.println("Keep practicing to master the Expert level!");
        }
    }

    @Override
    public String toString() {
        return "EXPERT";
    }
}
