package com.app;

import java.util.ArrayList;
import java.util.List;

/**
 * The Language class represents a language with a list of vocabulary words,
 * phrases, and grammar rules.
 */
public class Language {

    private String code;
    private WordList vocabularyList; // Shared WordList instance
    private ArrayList<String> grammarRules;

    public Language(String code) {
        this.code = code;
        this.vocabularyList = WordList.getInstance(); // Singleton to avoid duplicate instances
        this.grammarRules = new ArrayList<>();
    }

    public void addGrammarRule(String rule) {
        grammarRules.add(rule);
    }

    public void displayContent() {
        System.out.println("Language: " + code);
        System.out.println("Vocabulary:");

        List<Word> words = vocabularyList.getLanguageWords(code);
        if (words != null && !words.isEmpty()) {
            for (Word word : words) {
                System.out.println(word.getText() + " - " + word.getForeign()
                        + " (" + word.getPronounce() + ") - Difficulty: " + word.getDifficulty());
            }
        } else {
            System.out.println("No vocabulary available.");
        }

        System.out.println("Grammar Rules:");
        if (grammarRules.isEmpty()) {
            System.out.println("No grammar rules added.");
        } else {
            for (String rule : grammarRules) {
                System.out.println(rule);
            }
        }
    }

    // Getters and Setters for the language code
    public String getLanguageCode() {
        return code;
    }

    public void setLanguageCode(String code) {
        this.code = code;
    }

    // Get the list of grammar rules
    public ArrayList<String> getGrammarRules() {
        return grammarRules;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Language language = (Language) obj;
        return code.equalsIgnoreCase(language.code);
    }

    @Override
    public int hashCode() {
        return code.toLowerCase().hashCode();
    }

}
