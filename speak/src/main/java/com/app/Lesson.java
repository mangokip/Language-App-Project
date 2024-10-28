package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a lesson in the language learning application.
 * @param questions - ArrayList of Question with different types of questions
 * @param language - the langauge of the lesson
 * @param topic - the topic of the lesson
 * @param numQuestionsCorrect - the number of questions the user has answered correctly
 * @param diff - the difficulty level of the lesson
 */
public class Lesson {

    private ArrayList<Question> questions;
    private Language language;
    private String topic;
    private int numQuestionsCorrect;
    private static final int diff = 1;

    // constructor for the lesson class
    public Lesson(String topic, Language language) {
        this.topic = topic;
        this.questions = new ArrayList<>();
        this.language = language;
        this.numQuestionsCorrect = 0;

        WordList wordList = WordList.getInstance();
        PhraseList phraseList = PhraseList.getInstance();

        // Add exactly 5 questions
        while (questions.size() < 5) {
            Word randomWord = wordList.getRandomWord(language);

            if (randomWord == null) {
                System.out.println("Error: Unable to generate question due to missing words.");
                continue; // Skip this iteration if no word is found
            }

            //adds different question types based on the size of the questions arraylist
            switch (questions.size()) {
                case 0:
                case 1:
                    // Add multiple-choice questions
                    questions.add(new MultipleChoice(diff, randomWord, language));
                    break;

                case 2:
                    // Add a True/False question
                    boolean isCorrect = new Random().nextBoolean();
                    String correctTranslation = randomWord.getForeign();
                    String incorrectTranslation = "Dog"; // Arbitrary incorrect answer
                    questions.add(new TrueFalse(randomWord, isCorrect, diff));

                    break;

                case 3:
                    // Add a VocabularyMatching question
                    questions.add(new VocabularyMatching(randomWord, language, diff));
                    break;

                case 4:
                    // Add a FillBlank question
                    List<Word> wordListForPhrase = wordList.getLanguageWords(language.getLanguageCode());
                    if (wordListForPhrase != null && !wordListForPhrase.isEmpty()) {
                        Word selectedWord = wordListForPhrase.get(new Random().nextInt(wordListForPhrase.size()));
                        Phrase phraseWithBlank = generatePhraseWithWord(selectedWord, phraseList.getPhrases());

                        if (phraseWithBlank != null) {
                            questions.add(new FillBlank(diff, selectedWord, language, phraseWithBlank));
                        } else {
                            // If no phrase found, fallback to multiple-choice
                            questions.add(new MultipleChoice(diff, randomWord, language));
                        }
                    } else {
                        // Fallback to multiple-choice if no words for phrases
                        questions.add(new MultipleChoice(diff, randomWord, language));
                    }
                    break;
            }
        }
    }

    // function to generate a phrase with a word that is passed in the function
    // @param word - the word to be used to generate the phrase
    // @param phrases - the list of phrases to be used to generate the phrase
    // returns a phrase with a blank space for the word passed in
    private Phrase generatePhraseWithWord(Word word, List<Phrase> phrases) {
        for (Phrase phrase : phrases) {
            if (phrase.getText().toLowerCase().contains(word.getForeign().toLowerCase())) {
                return new Phrase(
                        phrase.withBlank(word),
                        phrase.getTranslation(),
                        phrase.getPronunciation()
                );
            }
        }
        return null;
    }

    // function to play the lesson
    public int playLesson() {
        System.out.println("Starting lesson: " + topic);
        Scanner scanner = new Scanner(System.in);
        /*
         * loops throught the questions arraylist and asks the questions
         * if the question is correct, it increments the numQuestionsCorrect variable
         */
        for (Question question : questions) {
            boolean isCorrect = question.askQuestion(scanner);
            if (isCorrect) {
                numQuestionsCorrect++;
            }
        }
        //calculates the score of the lesson and prints it
        int score = (numQuestionsCorrect * 100) / questions.size();
        System.out.println("Lesson completed. Score: " + score + "%");

        //if the score is greater than or equal to 80, the user passes the lesson and moves on to the next module
        //if the score is less than 80, the user fails the lesson
        if (score >= 80) {
            System.out.println("You passed! Proceeding to the next module.");
        } else {
            System.out.println("You did not pass. Please try this lesson again.");
        }

        return score;
    }
}
