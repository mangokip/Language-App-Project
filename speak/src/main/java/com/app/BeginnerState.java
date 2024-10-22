package com.app;

import java.util.ArrayList;

/**
 * Beginner State
 * @author David Dinh
 */
class BeginnerState implements State {

    @Override
    public void increaseLevel() {
        System.out.println("Level increased! You're now at the Intermediate level.");
        // Logic to transition to IntermediateState can be implemented here.
    }

    @Override
    public void decreaseLevel() {
        System.out.println("You're already at the Beginner level.");
    }

    @Override
    public ArrayList<Question> getQuestionPool() {
        ArrayList<Question> questions = new ArrayList<>();
        // Adding beginner-level questions Example
        questions.add(new Question("Translate 'apple' to Spanish.", 1));
        questions.add(new Question("Translate 'hello' to Spanish.", 1));

        return questions;
    }

    @Override
    public void loadContent(Language language) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadContent'");
    }

   // @Override
   // public void loadContent(Language language);
}
