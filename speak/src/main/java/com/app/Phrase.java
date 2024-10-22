package com.app;
/*
 * LaMorra Strong
 */
import java.util.ArrayList;

/**
 * The Phrase class represents a phrase consisting of multiple words and its meaning.
 */
public class Phrase {

    // A list of words that make up the phrase.
    private ArrayList<Word> words;

    // The meaning of the phrase.
    private String meaning;

    /**
     * Constructor to create a new Phrase object.
     * 
     * @param words   The words that make up the phrase.
     * @param meaning The meaning of the phrase.
     */
    public Phrase(ArrayList<Word> words, String meaning) {
        this.words = words;
        this.meaning = meaning;
    }

    public ArrayList<String> getEnglishPhrase(){
        ArrayList<String> englishWords = new ArrayList<String>();
        for(Word word : words){
            englishWords.add(word.getText());
        }
        return englishWords;
    }
    public ArrayList<String> getForeignPhrase(){
        ArrayList<String> foreignWords = new ArrayList<String>();
        for(Word word: words){
            foreignWords.add(word.getForeign());
        }
        return foreignWords;
    }

    /**
     * Gets the words in the phrase.
     * 
     * @return An ArrayList of Word objects representing the words in the phrase.
     */
    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     * Gets the meaning of the phrase.
     * 
     * @return The meaning of the phrase as a String.
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * Displays the phrase details, including the words and their meaning.
     */
    public void displayPhrase() {
        System.out.print("Phrase: ");
        for (Word word : words) {
            System.out.print(word.getText() + " ");
        }
        System.out.println("\nMeaning: " + meaning);
    }
}
