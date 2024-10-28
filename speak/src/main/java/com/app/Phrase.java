package com.app;

/**
 * The Phrase class represents a phrase with its original text, 
 * translation, and pronunciation guide.
 */
public class Phrase {
    private String text; // The original phrase text
    private String translation; // The translated text
    private String pronunciation; // The pronunciation guide

    /**
     * Constructs a Phrase instance with the specified text, translation, and pronunciation.
     *
     * @param text the original phrase text
     * @param translation the translated text of the phrase
     * @param pronunciation the pronunciation guide
     */
    public Phrase(String text, String translation, String pronunciation) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
    }

    /**
     * Returns the original text of the phrase.
     *
     * @return the original phrase text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the translation of the phrase.
     *
     * @return the translated text of the phrase
     */
    public String getTranslation() {
        return translation;
    }

    /**
     * Returns the pronunciation guide for the phrase.
     *
     * @return the pronunciation of the phrase
     */
    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * Replaces the correct word in the phrase text with a blank.
     * Assumes that the word's **foreign text** exists in the phrase.
     * 
     * @param correctWord the word to replace with a blank
     * @return the phrase text with the specified word replaced by a blank
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
