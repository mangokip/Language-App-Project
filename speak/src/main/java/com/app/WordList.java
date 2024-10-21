package com.app;

import java.util.List;
import java.util.Random;

public class WordList {
    private List<Word> words;

    public WordList(String filePath) {
        this.words = DataLoader.loadWords(filePath);
    }

    public Word getRandomWord() {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    public List<Word> getWordsByGenre(Genre genre) {
        return words.stream().filter(word -> word.getGenre() == genre).toList();
    }
}
