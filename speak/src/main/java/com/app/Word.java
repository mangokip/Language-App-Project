package com.app;
/*
 * LaMorra Strong
 */
public class Word {
    private String text;

    /**
     * Constructs a Word object with the specified text.
     * 
     * @param text the word as a String
     */
    public Word(String text) {
        this.text = text;
    }

    /**
     * Gets the word text.
     * 
     * @return the word as a String
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the word text.
     * 
     * @param text the new word text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Analyzes the word to extract linguistic information such as part of speech.
     * 
     * TODO: Implement the logic for word analysis.
     */
    public void analyze() {
        // TODO: Implement logic to analyze the word (e.g., part of speech, meaning)
        System.out.println("Analyzing word: " + text);
    }
}
