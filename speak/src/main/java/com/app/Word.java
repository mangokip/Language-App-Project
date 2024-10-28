package com.app;

/**
 * The Word class represents a word with its attributes, including its
 * text, foreign translation, pronunciation, genre, and difficulty level.
 */
public class Word {

    /** The word in its original language. */
    private String text;
    /** The word in a foreign language. */
    private String foreign;
    /** The pronunciation guide for the word. */
    private String pronounce;
    /** The genre of the word (e.g., noun, verb, etc.). */
    private Genre genre;
    /** The difficulty level of the word, represented as an integer. */
    private int difficulty;

    /**
     * Constructs a Word object with the specified attributes.
     *
     * @param text the word as a String
     * @param foreign the foreign translation of the word
     * @param pronounce the pronunciation guide
     * @param genre the genre of the word
     * @param difficulty the difficulty level of the word
     */
    public Word(String text, String foreign, String pronounce, Genre genre, int difficulty) {
        this.text = text;
        this.foreign = foreign;
        this.pronounce = pronounce;
        this.genre = genre;
        this.difficulty = difficulty;

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
     * Gets the foreign translation of the word.
     *
     * @return the foreign translation
     */
    public String getForeign() {
        return foreign;
    }
    /**
     * Gets the pronunciation guide for the word.
     *
     * @return the pronunciation of the word
     */
    public String getPronounce() {
        return pronounce;
    }
    
    /**
     * Gets the genre of the word.
     *
     * @return the genre of the word
     */
    public Genre getGenre() {
        return genre;
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
     * Sets the foreign translation of the word.
     *
     * @param foreign the new foreign translation
     */
    public void setForeign(String foreign) {
        this.foreign = foreign;
    }

    /**
     * Sets the pronunciation guide for the word.
     *
     * @param pronounce the new pronunciation guide
     */
    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    /**
     * Sets the genre of the word.
     *
     * @param genre the new genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Gets the difficulty level of the word.
     *
     * @return the difficulty level as an integer
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    @Override
    public String toString() {
        return "Word{"
                + "text='" + text + '\''
                + ", foreign='" + foreign + '\''
                + ", pronounce='" + pronounce + '\''
                + ", genre=" + genre
                + '}';
    }
}
