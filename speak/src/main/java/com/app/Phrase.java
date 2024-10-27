package com.app;

public class Phrase {
    private String text; // The original phrase text
    private String translation; // The translated text
    private String pronunciation; // The pronunciation guide

    // Constructor to initialize the phrase with text, translation, and pronunciation
    public Phrase(String text, String translation, String pronunciation) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
    }

    // Getter for the original text of the phrase
    public String getText() {
        return text;
    }

    // Getter for the translation of the phrase
    public String getTranslation() {
        return translation;
    }

    // Getter for the pronunciation of the phrase
    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * Replaces the correct word in the phrase text with a blank.
     * Assumes that the word's **foreign text** exists in the phrase.
     */
    public String withBlank(Word correctWord) {
        if (text.contains(correctWord.getForeign())) {
            return text.replace(correctWord.getForeign(), "______");
        } else {
            return text;  // If the word is not found, return the original text.
        }
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "text='" + text + '\'' +
                ", translation='" + translation + '\'' +
                ", pronunciation='" + pronunciation + '\'' +
                '}';
    }
}
