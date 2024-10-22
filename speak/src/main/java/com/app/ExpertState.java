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

    @Override
    public void loadContent(Language language) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadContent'");
    }

    
}