package com.app;
//Carson Sessoms



import java.util.Map;

public class VocabularyMatching {
    private Question baseQuestion;
    private Map<String, String> wordPairs;

    public VocabularyMatching(Question baseQuestion, Map<String, String> wordPairs) {
        this.baseQuestion = baseQuestion;
        this.wordPairs = wordPairs;
    }

    public void startGame() {
    }

    public void endGame() {
    }

    public void displayInstructions() {
    }

    public Question getBaseQuestion() {
        return baseQuestion;
    }

    public void setBaseQuestion(Question baseQuestion) {
        this.baseQuestion = baseQuestion;
    }

    public Map<String, String> getWordPairs() {
        return wordPairs;
    }

    public void setWordPairs(Map<String, String> wordPairs) {
        this.wordPairs = wordPairs;
    }
}
