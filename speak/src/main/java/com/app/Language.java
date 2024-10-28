package com.app;

import java.util.ArrayList;
import java.util.List;

/**
 * The Language class represents a language with a list of vocabulary words,
 * phrases, and grammar rules.
 */
public class Language {

    private String code; /** The language code (e.g., "en" for English, "es" for Spanish). */
    private WordList vocabularyList; // Shared WordList instance
    private ArrayList<String> grammarRules;/** A list of grammar rules associated with this language. */
    /**
     * Constructs a Language instance with the specified language code.
     *
     * @param code the language code
     */
    public Language(String code) {
        this.code = code;
        this.vocabularyList = WordList.getInstance(); // Singleton to avoid duplicate instances
        this.grammarRules = new ArrayList<>();
    }

     /**
     * Adds a grammar rule to the list of grammar rules.
     *
     * @param rule the grammar rule to add
     * @throws IllegalArgumentException if the rule is null or empty
     */
    public void addGrammarRule(String rule) {
        grammarRules.add(rule);
    }

    /**
     * Displays the content of the language, including vocabulary and grammar rules.
     */
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

    /**
     * Returns the language code.
     *
     * @return the language code
     */
    public String getLanguageCode() {
        return code;
    }

    /**
     * Sets the language code.
     *
     * @param code the new language code
     */
    public void setLanguageCode(String code) {
        this.code = code;
    }

    /**
     * Returns a list of grammar rules associated with this language.
     *
     * @return a list of grammar rules
     */
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
