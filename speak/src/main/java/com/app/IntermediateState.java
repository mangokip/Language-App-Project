package com.app;

import java.util.ArrayList;

/**
 * intermediate State
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
        // Adding intermediate-level questions
        questions.add(new Question("Conjugate the verb 'to eat' in the past tense in Spanish.", 2));
        questions.add(new Question("What is the plural form of 'child' in Spanish?", 2));
        return questions;
    }

    @Override
    public void loadContent(Language language) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadContent'");
    }

    
}