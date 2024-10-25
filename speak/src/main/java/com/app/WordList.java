package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.internal.ArrayComparisonFailure;

public class WordList {
    private DataLoader dataLoader = new DataLoader();
    private Map<Language, List<Word>> languageWords;
    private Map<Word, Integer> wordDifficulty;
    // private List<Word> words;
    private static WordList wordList;

    private WordList() {
        languageWords = new HashMap<>();
        LanguageList languageList = LanguageList.getInstance();
        Language thisLanguage = languageList.getLanguage("Spanish");
        languageWords.put(thisLanguage, dataLoader.loadWordsToList());
        wordDifficulty = new HashMap<>();
        // words = dataLoader.loadWordsToList();
    }

    public static WordList getInstance() {
        if (wordList == null) {
            wordList = new WordList();
        }
        return wordList;
    }

    public Word addWord(Language language, String text, String translation, String pronounce, Genre genre, int difficulty) {
        Word word = new Word(text, translation, pronounce, genre, difficulty);
        languageWords.computeIfAbsent(language, k -> new ArrayList<>()).add(word);
        wordDifficulty.put(word, difficulty);
        return word;
    }

    public Word getRandomWord(Language language) {
        List<Word> words = languageWords.get(language);
        if (words == null || words.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    public List<Word> getWordsByGenre(Language language, Genre genre) {
        List<Word> words = languageWords.get(language);
        if (words == null) {
            return new ArrayList<>();
        }
        return words.stream().filter(word -> word.getGenre() == genre).toList();
    }

    public int getDifficulty(Word word) {
        return wordDifficulty.getOrDefault(word, 0);
    }

    public Map<Language, List<Word>> getLanguageWords() {
        return languageWords;
    }
}