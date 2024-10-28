package com.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for a vocabulary matching question.
 * @param wordPairs - Map of English words to their foreign translations
 * @param userPairs - Map of English words to the user's translations
 * 
 */
public class VocabularyMatching extends Question {

    private Map<String, String> wordPairs;
    private Map<String, String> userPairs;

    /*
     * Constructor for a vocabulary matching question.
     * @param randomWord - The random word to be used for the vocabulary matching question
     * @param language - The language to be used for the vocabulary matching question
     * @param difficulty - The difficulty level of the vocabulary matching question
     */

    public VocabularyMatching(Word randomWord, Language language, int difficulty) {
        super("Match the words in their English form to their foreign form:", difficulty);
        this.wordPairs = new HashMap<>();
        this.userPairs = new HashMap<>();
        // populates the word pairs map with random words from the passed language
        populateWordPairs(randomWord, language);
    }

    /*
     * Populates the word pairs map with random words from the passed language. using the wordList
     * @param randomWord - The random word to be used for the vocabulary matching question
     * @param language - The language to be used for the vocabulary matching question
     */
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

    /*
     * prints all the lines necessary for the vocabulary matching question
     * and takes and validates the user's input
     */

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

    //function to validate the user's input based on the word pairs map and the user pairs map
    // making sure that the maps have the same keys and values
    // returns true if the values are the same and false if they are not
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
