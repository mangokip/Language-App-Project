package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordList {
    private Map<String, List<Word>> languageWords; 
    private static WordList instance;

    
    private WordList() {
        languageWords = new HashMap<>();  
        loadWords();  
    }

  
    public static WordList getInstance() {
        if (instance == null) {
            instance = new WordList();
        }
        return instance;
    }

    private void loadWords() {
        
        Map<String, List<Word>> loadedWords = DataLoader.loadWords();
        if (loadedWords != null && !loadedWords.isEmpty()) {
            languageWords.putAll(loadedWords);
        } else {
            System.err.println("DataLoader returned no words.");
        }
    }

   
    public Word getRandomWord(Language language) {
        return getRandomWord(language.getLanguageCode());
    }

    public Word getRandomWord(String languageCode) {
        List<Word> words = languageWords.getOrDefault(languageCode, new ArrayList<>());
        if (words.isEmpty()) {
            System.err.println("No words available for language: " + languageCode);
            return null;
        }
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    
    public List<Word> getLanguageWords(String languageCode) {
        return languageWords.getOrDefault(languageCode, new ArrayList<>());
    }

    public List<Word> getWordsByGenre(Language language, Genre genre) {
        List<Word> genreWords = new ArrayList<>();
        for (Word word : getLanguageWords(language.getLanguageCode())) {
            if (word.getGenre() == genre) {
                genreWords.add(word);
            }
        }
        return genreWords;
    }
    
    
}

