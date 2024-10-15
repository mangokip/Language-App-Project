package com.app;
//carson Sessoms

import java.util.ArrayList;

public class FillBlank extends Question {
    private Phrase sentence;
    private Word correctAnswer;

    public FillBlank(int diff, String prompt, Word correctAnswer, Phrase sentence) {
        this.setDifficulty(diff);
    }

    public void FillBlank(Question baseGame) {
    }

    public void startGame() {
    }

    public void endGame() {
    }

    public void displayInstructions() {
    }

    public Question getBaseGame() {
        return baseGame;
    }

    public void setBaseGame(Question baseGame) {
        this.baseGame = baseGame;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<String> sentences) {
        this.sentences = sentences;
    }

    public ArrayList<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(ArrayList<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
