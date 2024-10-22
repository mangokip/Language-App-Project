package com.app;
/*
 * LaMorra Strong
 */
public class Word {
    private String text;
    private String foreign;
    private String pronounce;
    private Genre genre;
    private int difficulty;
    private boolean understood;

    /**
     * Constructs a Word object with the specified text.
     * 
     * @param text the word as a String
     */
    public Word(String text, String foregin, String pronounce, Genre genre, int difficulty, boolean understood) {
        this.text = text;
        this.foreign = foreign;
        this.pronounce = pronounce;
        this.genre = genre;
        this.difficulty = difficulty;
        this.understood = understood;
        

    }

    /**
     * Gets the word text.
     * 
     * @return the word as a String
     */
    public String getText() {
        return text;
    }

    public String getForeign()
    {
        return foreign;
    }

    public String getPronounce(){
        return pronounce;
    }

    public Genre getGenre()
    {
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

    public void setForeign(String foreign){
        this.foreign = foreign;
    }

    public void setPronounce(String pronounce){
        this.pronounce = pronounce;
    }

    public  void setGenre(Genre genre){
        this.genre = genre;
    }

    public int getDifficulty(){
        return this.difficulty;
    }
    public boolean getUnderstood(){
        return this.understood;
    }
    public void setUnderstood(boolean understood){
        this.understood = understood;
    }
    @Override
    public String toString() {
        return "Word{" +
               "text='" + text + '\'' +
               ", foreign='" + foreign + '\'' +
               ", pronounce='" + pronounce + '\'' +
               ", genre=" + genre +
               '}';
    }
}

