package com.app;

import java.util.ArrayList;
import java.util.Random;

public class WordList {
    private ArrayList<Word> words;
    private static WordList wordList;

    private WordList() {
        words = new ArrayList<>();
    }

    public static WordList getInstance() {
        if(wordList == null) {
            wordList = new WordList();
        }
        return wordList;
    }

    public Word addWord(String text, String translation, String pronounce, Genre genre) {
        Word word = new Word(text, translation, pronounce, genre);
        words.add(word);
        return word;
    }   

    public Word getRandomWord() {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    public ArrayList<Word> getWordsByGenre(Genre genre) {
        return (ArrayList<Word>) words.stream().filter(word -> word.getGenre() == genre).toList();
    }
    
}
