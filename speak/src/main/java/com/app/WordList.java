package com.app;

import java.util.ArrayList;
import java.util.random.*;

public class WordList {
    private static WordList wordList;
    private ArrayList<Word> words;


    public static WordList getInstance() {
        if (wordList == null) {
            wordList = new WordList();
        }
        return wordList;
    }

    public ArrayList<Word> getWordsGenre(Genre genre){
        ArrayList<Word> wordsWithGenre = new ArrayList<>();
        for(Word word: words){
            if(word.getGenre() == genre){
                wordsWithGenre.add(word);
            }
        }
        return wordsWithGenre;
    }
}
