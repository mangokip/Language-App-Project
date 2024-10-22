package com.app;

import java.util.ArrayList;

/*
 * LaMorra Strong
 */
/**
 * The Language class represents a language with a list of vocabulary words,
 * phrases, and grammar rules.
 */
public class Language {

    // The type/name of the language (e.g., Spanish).
    private String code;

    // A list of vocabulary words in the language.
    private ArrayList<Word> vocabularyList;

    // A list of grammar rules for the language.
    private ArrayList<String> grammarRules;

    /**
     * Constructor to create a new Language object with a specified name.
     *
     * @param name The name/type of the language.
     */
    public Language(String name) {
        this.code = name;
        this.vocabularyList = new ArrayList<>();
        this.grammarRules = new ArrayList<>();
    }

    /**
     * Adds a new vocabulary word to the language's vocabulary list.
     *
     * @param word The word to add.
     */
    public void addVocabulary(Word word) {
        vocabularyList.add(word);
    }

    /**
     * Adds a new phrase to the language's vocabulary list.
     *
     * @param phrase The phrase to add as a Word object.
     */
    public void addPhrase(String phrase) {
        // Assuming that a phrase can be treated as a Word with empty pronunciation and genre.
        Word phraseWord = new Word(phrase, "", "", null); // Phrase has no foreign translation or genre
        vocabularyList.add(phraseWord);
    }

    /**
     * Adds a new grammar rule to the list of grammar rules.
     *
     * @param rule The grammar rule to add.
     */
    public void addGrammarRule(String rule) {
        grammarRules.add(rule);
    }

    /**
     * Displays the language's vocabulary and grammar rules.
     */
    public void displayContent() {
        System.out.println("Language: " + code);
        System.out.println("Vocabulary:");
        for (Word word : vocabularyList) {
            System.out.println(word.getText() + " - " + word.getForeign() + " (" + word.getPronounce() + ")");
        }
        System.out.println("Grammar Rules:");
        for (String rule : grammarRules) {
            System.out.println(rule);
        }
    }

    // Getter for language type
    public String getType() {
        return code;
    }

    // Setter for language type
    public void setType(String type) {
        this.code = type;
    }

    public String getLanguageCode() {
        return this.code;
    }

    // Getter for vocabulary list
    public ArrayList<Word> getVocabularyList() {
        return vocabularyList;
    }

    // Getter for grammar rules
    public ArrayList<String> getGrammarRules() {
        return grammarRules;
    }
}
