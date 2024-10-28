package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordList {

    private Map<String, List<Word>> languageWords;
    private static WordList instance;

    /**
     * Singleton constructor for WordList
     */
    private WordList() {
        languageWords = new HashMap<>();
        loadWords();
    }

    /**
     * Singleton pattern - getInstance method
     * @return  The instance of the WordList
     */
    public static WordList getInstance() {
        if (instance == null) {
            instance = new WordList();
        }
        return instance;
    }

    /**
     * Loads words from the DataLoader
     */
    private void loadWords() {

        Map<String, List<Word>> loadedWords = DataLoader.loadWords();
        if (loadedWords != null && !loadedWords.isEmpty()) {
            languageWords.putAll(loadedWords);
        } else {
            System.err.println("DataLoader returned no words.");
        }
    }

    /**
     * Gets a random word from the list of words for the given language
     * @param language  The language to get the random word from
     * @return  A random word from the list of words for the given language
     */
    public Word getRandomWord(Language language) {
        return getRandomWord(language.getLanguageCode());
    }

    /**
     * Gets a random word from the list of words for the given language code
     * @param languageCode  The language code to get the random word from
     * @return A random word from the list of words for the given language code
     */
    public Word getRandomWord(String languageCode) {
        List<Word> words = languageWords.getOrDefault(languageCode, new ArrayList<>());
        if (words.isEmpty()) {
            System.err.println("No words available for language: " + languageCode);
            return null;
        }
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    /**
     * Gets the list of words for the given language code
     * @param languageCode - language
     */
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
