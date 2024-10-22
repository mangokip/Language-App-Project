package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Language class represents a language with a list of vocabulary words,
 * phrases, and grammar rules.
 */
public class Language {
    private String code;
    private WordList vocabularyList;
    private ArrayList<String> grammarRules;

    public Language(String name) {
        this.code = name;
        this.vocabularyList = WordList.getInstance();
        this.grammarRules = new ArrayList<>();
    }

    public void addVocabulary(String text, String translation, String pronounce, Genre genre, int difficulty) {
        vocabularyList.addWord(this, text, translation, pronounce, genre, difficulty);
    }

    public void addPhrase(String phrase) {
        vocabularyList.addWord(this, phrase, "", "", null, 1); // Default difficulty for phrases
    }

    public void addGrammarRule(String rule) {
        grammarRules.add(rule);
    }

    public void displayContent() {
        System.out.println("Language: " + code);
        System.out.println("Vocabulary:");
        for (Map.Entry<Language, List<Word>> entry : vocabularyList.getLanguageWords().entrySet()) {
            if (entry.getKey().equals(this)) {
                for (Word word : entry.getValue()) {
                    int difficulty = vocabularyList.getDifficulty(word);
                    System.out.println(word.getText() + " - " + word.getForeign() + " (" + word.getPronounce() + ") - Difficulty: " + difficulty);
                }
            }
        }
        System.out.println("Grammar Rules:");
        for (String rule : grammarRules) {
            System.out.println(rule);
        }
    }

    public String getType() {
        return code;
    }

    public void setType(String type) {
        this.code = type;
    }

    public String getLanguageCode() {
        return this.code;
    }

    public List<Word> getVocabularyList() {
        return vocabularyList.getLanguageWords().get(this);
    }

    public ArrayList<String> getGrammarRules() {
        return grammarRules;
    }
}