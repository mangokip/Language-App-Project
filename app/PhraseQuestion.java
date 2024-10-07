//Carson Sessoms

import java.util.ArrayList;

public class PhraseQuestion {
    private Question baseGame;
    private ArrayList<String> phrase;
    private ArrayList<String> correctAnswers;

    public PhraseQuestion(Question baseGame, ArrayList<String> phrase, ArrayList<String> correctAnswers) {
        this.baseGame = baseGame;
        this.phrase = phrase;
        this.correctAnswers = correctAnswers;
    }

    public void PhraseBlank(Question baseGame) {
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

    public ArrayList<String> getPhrase() {
        return phrase;
    }

    public void setPhrase(ArrayList<String> phrase) {
        this.phrase = phrase;
    }

    public ArrayList<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(ArrayList<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
