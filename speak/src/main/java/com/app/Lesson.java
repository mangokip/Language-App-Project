package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a lesson in the language learning application.
 */
public class Lesson {

    private ArrayList<Question> questions;
    private Language language;
    private String topic;
    private int numQuestionsCorrect;
    private static final int diff = 1;

    public Lesson(String topic, Language language) {
        this.topic = topic;
        this.questions = new ArrayList<>();
        this.language = language;
        this.numQuestionsCorrect = 0;

        WordList wordList = WordList.getInstance();
        PhraseList phraseList = PhraseList.getInstance();

        Word randomWord1 = wordList.getRandomWord(language);
        Word randomWord2 = wordList.getRandomWord(language);
        Word randomWord3 = wordList.getRandomWord(language);

        if (randomWord1 != null && randomWord2 != null && randomWord3 != null) {
            // Add multiple-choice questions
            questions.add(new MultipleChoice(diff, randomWord1, language));
            questions.add(new MultipleChoice(diff, randomWord2, language));

            // Add True/False question with random correctness
            Word randomWord = wordList.getRandomWord(language);
            boolean isCorrect = new Random().nextBoolean();  // Random correctness

            String correctTranslation = randomWord.getForeign();
            String incorrectTranslation = "Dog";  // Arbitrary incorrect answer
            String displayedTranslation = isCorrect ? correctTranslation : incorrectTranslation;

            questions.add(new TrueFalse(randomWord, displayedTranslation, incorrectTranslation, isCorrect, diff));

            // Add VocabularyMatching question (counts as one question)
            questions.add(new VocabularyMatching(randomWord3, language, diff));

            // Add FillBlank question if a suitable phrase is found
            List<Word> wordListForPhrase = wordList.getLanguageWords(language.getLanguageCode());
            if (wordListForPhrase != null && !wordListForPhrase.isEmpty()) {
                Word selectedWord = wordListForPhrase.get(new Random().nextInt(wordListForPhrase.size()));
                Phrase phraseWithBlank = generatePhraseWithWord(selectedWord, phraseList.getPhrases());

                if (phraseWithBlank != null) {
                    questions.add(new FillBlank(diff, selectedWord, language, phraseWithBlank));
                }
            } else {
                System.out.println("Error: No words available for phrases.");
            }
        } else {
            System.out.println("Error: Unable to generate questions due to missing words.");
        }
    }

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

    public int playLesson() {
        System.out.println("Starting lesson: " + topic);
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            boolean isCorrect = question.askQuestion(scanner);
            if (isCorrect) {
                numQuestionsCorrect++;
            }
        }

        
        int score = (numQuestionsCorrect * 100) / questions.size();
        System.out.println("Lesson completed. Score: " + score + "%");

        if (score >= 80) {
            System.out.println("You passed! Proceeding to the next module.");
        } else {
            System.out.println("You did not pass. Please try this lesson again.");
        }

        return score; 
    }
<<<<<<< HEAD

    /**
     * Returns the title of the lesson.
     * 
     * @return The title of the lesson.
     */
    public String getTitle() {
        return topic;
    }

    /**
     * Returns the list of questions in the lesson.
     * 
     * @return An ArrayList of questions in the lesson.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    
    private void createTrueFalseQuestions() {
        // Loop through all words for the given language
        List<Word> words = WordList.getInstance().getWords(language);  // Fetch words for the language
    
        for (Word word : words) {
            boolean isCorrect = Math.random() > 0.5;
            String incorrectTranslation = null;
    
            if (!isCorrect) {
                // Get words of the same genre
                List<Word> sameGenreWords = WordList.getInstance().getWordsByGenre(language, word.getGenre());
    
                // Ensure we get an incorrect word for the same genre
                Word incorrectWord;
                do {
                    incorrectWord = sameGenreWords.get((int) (Math.random() * sameGenreWords.size()));
                } while (incorrectWord.getForeign().equals(word.getForeign()));  // Ensure it's not the same word
    
                incorrectTranslation = incorrectWord.getForeign();
            }
    
            // Add True/False question to the lesson
            questions.add(new TrueFalse(
                word, incorrectTranslation, isCorrect, word.getDifficulty()
            ));
        }
    }
=======
>>>>>>> f76a5091e1cbe1cfb8998e4e728117936cdfab9e
}
