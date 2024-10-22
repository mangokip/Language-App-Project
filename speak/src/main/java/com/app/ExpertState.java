package com.app;

import java.util.ArrayList;

/**
 * Expert state
 * @author David Dinh
 */
class ExpertState implements State {

    @Override
    public void increaseLevel() {
        System.out.println("You're already at the highest level: Expert.");
    }

    @Override
    public void decreaseLevel() {
        System.out.println("Level decreased! You're now at the Intermediate level.");
        // Logic to transition to IntermediateState can be implemented here.
    }

    @Override
    public ArrayList<Question> getQuestionPool() {
        ArrayList<Question> questions = new ArrayList<>();
        // Adding expert-level questions
        questions.add(new Question("What is the subjunctive form of 'to be' in Spanish?", 3));
        questions.add(new Question("Translate 'philosophy' to Spanish.", 3));
        return questions;
    }

    //TODO fix Loadcontent
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
            System.out.println("Congratulations! You've mastered the Expert level!");
        } else {
            System.out.println("Keep practicing to master the Expert level!");
        }
    }
    @Override
    public String toString() {
        return "EXPERT";
    }
}