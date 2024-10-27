package com.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a vocabulary matching question.
 */
public class VocabularyMatching extends Question {

    private Map<String, String> wordPairs;
    private Map<String, String> userPairs;

    public VocabularyMatching(Word randomWord, Language language, int difficulty) {
        super("Match the words in their English form to their foreign form:", difficulty);
        this.wordPairs = new HashMap<>();
        this.userPairs = new HashMap<>();

        populateWordPairs(randomWord, language);
    }

    private void populateWordPairs(Word randomWord, Language language) {
        WordList wordList = WordList.getInstance();
        List<Word> genreWords = wordList.getWordsByGenre(language, randomWord.getGenre());

        wordPairs.put(randomWord.getText().toLowerCase(), randomWord.getForeign().toLowerCase());

        Random rand = new Random();
        while (wordPairs.size() < 3) {
            Word randomGenreWord = genreWords.get(rand.nextInt(genreWords.size()));
            wordPairs.putIfAbsent(randomGenreWord.getText().toLowerCase(), randomGenreWord.getForeign().toLowerCase());
        }
    }

    @Override
    public boolean askQuestion(Scanner scanner) {
        ArrayList<String> foreignWords = new ArrayList<>(wordPairs.keySet());
        ArrayList<String> englishWords = new ArrayList<>(wordPairs.values());
        Collections.shuffle(foreignWords);
        Collections.shuffle(englishWords);
        System.out.println(getPrompt() + "\n");
        for (int i = 0; i < foreignWords.size(); i++) {
            System.out.println(foreignWords.get(i) + "     " + englishWords.get(i) + "\n");
        }
        for (Map.Entry<String, String> entry : wordPairs.entrySet()) {
            String englishWord = entry.getKey();
            System.out.print("Enter the matching word for '" + englishWord + "': ");

            String userInput = scanner.nextLine().trim().toLowerCase();
            userPairs.put(englishWord, userInput);
        }

        return validateAnswer("");
    }

    @Override
    public boolean validateAnswer(String ignored) {
        boolean allCorrect = true;

        for (Map.Entry<String, String> entry : wordPairs.entrySet()) {
            String englishWord = entry.getKey().toLowerCase();
            String correctTranslation = entry.getValue().toLowerCase();
            String userTranslation = userPairs.getOrDefault(englishWord, "");

            if (!userTranslation.equals(correctTranslation)) {
                System.out.println("Incorrect. The correct answer for '" + englishWord + "' is: " + correctTranslation);
                allCorrect = false;
            }
        }

        if (allCorrect) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect.");
        }
        return allCorrect;
    }
}
