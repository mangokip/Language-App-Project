package com.app;

import java.util.ArrayList;

public class StateFactory {
    
    public static State valueOf(String stateName){
        if (stateName == null){
            throw new NullPointerException("State name cannot be null");
        }

        String upperStateName = stateName.toUpperCase();
        switch (upperStateName){
            case"BEGINNER":
                return new BeginnerState();
            case"INTERMEDIATE":
                return new IntermediateState();
            case"EXPERT":
                return new ExpertState();
            default:
                throw new IllegalArgumentException("Invalid state name: " + stateName);

        }
    }
    static class BeginnerState implements State {
        @Override
        public void increaseLevel() {
            // TODO Auto-generated method stub
        }
    
        @Override
        public void decreaseLevel() {
            // TODO Auto-generated method stub
        }
    
        @Override
        public ArrayList<Question> getQuestionPool() {
            // TODO Auto-generated method stub
            return new ArrayList<>();
        }
    
        @Override
        public void loadContent(Language language) {
            // TODO Auto-generated method stub
        }

        @Override
        public String toString() {
            return "BEGINNER";
        }
    }
}
