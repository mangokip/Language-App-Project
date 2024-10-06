//carson Sessoms

import java.util.ArrayList;

public class FillBlank {
    private Question baseGame;
    private ArrayList<String> sentences;
    private ArrayList<String> correctAnswers;

    public FillBlank(Question baseGame, ArrayList<String> sentences, ArrayList<String> correctAnswers) {
        this.baseGame = baseGame;
        this.sentences = sentences;
        this.correctAnswers = correctAnswers;
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
