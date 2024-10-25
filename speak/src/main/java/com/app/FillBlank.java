package com.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Represents a fill-in-the-blank question in the language learning application.
 */
public class FillBlank extends Question {
    private Phrase sentence;
    private ArrayList<Word> answers;
    private Word correctAnswer;
    private static final String sharedPrompt = "Select the answer choice that best completes the sentence: ";

    /**
     * Constructs a FillBlank question.
     * 
     * @param diff The difficulty level.
     * @param correctAnswer The correct answer for the blank.
     * @param sentence The phrase containing the blank.
     * @param language The language object used for selecting other possible answers.
     */
    public FillBlank(int diff, Word correctAnswer, Phrase sentence, Language language) {
        super(sharedPrompt, diff);
        this.sentence = sentence;
        this.correctAnswer = correctAnswer;
        this.answers = new ArrayList<>();
        Random rand = new Random();

        // Ensure correct answer is added to the list of possible answers
        answers.add(correctAnswer);

        // Get words from the word list with the same genre as the correct answer
        Genre wordGenre = correctAnswer.getGenre();
        WordList wordList = WordList.getInstance();
        // word list
        List<Word> genreWords = wordList.getWordsByGenre(language, wordGenre);

        // Add other unique answers to the list until we have 4 options in total
        Set<Word> uniqueAnswers = new HashSet<>();
        uniqueAnswers.add(correctAnswer);

        // Keep selecting random words until we have 4 unique answers
        while (uniqueAnswers.size() < 4) {
            Word tempWord = genreWords.get(rand.nextInt(genreWords.size()));
            uniqueAnswers.add(tempWord);
        }

        // Clear the answers list and add all unique answers from the set
        answers.clear();
        answers.addAll(uniqueAnswers);
    }

    /**
     * Checks whether the given answer is correct.
     *
     * @param userAnswer The user's answer.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean checkAnswer(Word userAnswer) {
        // Compare the user's answer to the correct answer
        return correctAnswer.equals(userAnswer);
    }

    /**
     * Returns the prompt for the fill-in-the-blank question, along with answer choices.
     *
     * @return The question prompt with answer choices.
     */
    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append(sharedPrompt).append("\n");
        ArrayList<String> foreignWords = sentence.getForeignPhrase();

        // Construct the sentence with the blank space for the correct answer
        for (String word : foreignWords) {
            if (word.equals(correctAnswer.getForeign())) {
                sB.append(" ________ ");
            } else {
                sB.append(word).append(" ");
            }
        }
        sB.append("\n");

        // Append answer choices to the prompt
        for (int i = 0; i < answers.size(); ++i) {
            sB.append((i + 1)).append(". ").append(answers.get(i).getForeign()).append("\n");
        }

        return sB.toString();
    }
}
