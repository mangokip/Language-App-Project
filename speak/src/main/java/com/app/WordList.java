package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WordList {
    private Map<Language, List<Word>> languageWords;
    private Map<Word, Integer> wordDifficulty;
    private static WordList wordList;

    private WordList() {
        languageWords = new HashMap<>();
        wordDifficulty = new HashMap<>();
    }

    public static WordList getInstance() {
        if (wordList == null) {
            wordList = new WordList();
        }
        return wordList;
    }

    public Word addWord(Language language, String text, String translation, String pronounce, Genre genre, int difficulty) {
        Word word = new Word(text, translation, pronounce, genre, difficulty, false);
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