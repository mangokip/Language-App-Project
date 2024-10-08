package com.app;
//Carson Sessoms

import java.util.ArrayList;

public class Question {
    private String question;
    private String answer;
    private ArrayList<String> answerOptions;
    private State difficulty;
    private String category;
    private int counter;

    public Question(String question, String answer, ArrayList<String> options, State difficulty, String category) {
        this.question = question;
        this.answer = answer;
        this.answerOptions = options;
        this.difficulty = difficulty;
        this.category = category;
    }

    public void getRandomQ(Language language) {
    }

    public void displayQuestion() {
    }

    public boolean checkAnswer(String userAnswer) {
        return false;
    }

    public void adjustDifficulty(State newState) {
    }

    public void setCounter(int count) {
    }

    public int getCounter() {
        return 0;
    }
}
