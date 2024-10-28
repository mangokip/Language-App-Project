package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Flashcard class represents a single flashcard containing a word or phrase with
 * its translation, pronunciation, genre, and difficulty level. Flashcards can be displayed
 * with a formatted border for visual appeal and can be generated in bulk using the
 * DataLoader.
 */
public class Flashcard {

    private String text;           // The word or phrase on the flashcard
    private String translation;     // Translation of the word or phrase
    private String pronunciation;   // Pronunciation of the word or phrase
    private String genre;           // Genre, only for words
    private Integer difficulty;     // Difficulty level, only for words

    /**
     * Default constructor initializing the flashcard with empty fields.
     */
    public Flashcard() {
        this.text = "";
        this.translation = "";
        this.pronunciation = "";
        this.genre = "";
        this.difficulty = 0;
    }

    /**
     * Constructor to create a flashcard for words, including genre and difficulty.
     * 
     * @param text          The word text for the flashcard.
     * @param translation   The translation of the word.
     * @param pronunciation The pronunciation of the word.
     * @param genre         The genre of the word (e.g., noun, verb).
     * @param difficulty    The difficulty level of the word.
     */
    public Flashcard(String text, String translation, String pronunciation, String genre, int difficulty) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.genre = genre;
        this.difficulty = difficulty;
    }

    /**
     * Constructor to create a flashcard for phrases, excluding genre and difficulty.
     * 
     * @param text          The phrase text for the flashcard.
     * @param translation   The translation of the phrase.
     * @param pronunciation The pronunciation of the phrase.
     */
    public Flashcard(String text, String translation, String pronunciation) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.genre = "PHRASE";
        this.difficulty = 1;  // Default difficulty for phrases
    }

    /**
     * @return The text of the flashcard (word or phrase).
     */
    public String getText() {
        return text;
    }

    /**
     * @return The translation of the flashcard text.
     */
    public String getTranslation() {
        return translation;
    }

    /**
     * @return The pronunciation of the flashcard text.
     */
    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * @return The genre of the word on the flashcard (or "PHRASE" if it is a phrase).
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return The difficulty level of the word on the flashcard.
     */
    public Integer getDifficulty() {
        return difficulty;
    }

    /**
     * Displays the flashcard details within a formatted box for visual appeal.
     */
    public void display() {
        int boxWidth = 50;
        String topBorder = "╔" + "═".repeat(boxWidth) + "╗";
        String bottomBorder = "╚" + "═".repeat(boxWidth) + "╝";

        System.out.println(topBorder);
        printWrappedLine("Word/Phrase: " + text, boxWidth);
        printWrappedLine("Pronunciation: " + pronunciation, boxWidth);
        printWrappedLine("Translation: " + translation, boxWidth);

        if (!genre.equals("PHRASE")) {
            printWrappedLine(String.format("Genre: %s | Difficulty: %d", genre, difficulty), boxWidth);
        }

        System.out.println(bottomBorder);
    }

    /**
     * Helper method to print text wrapped within a specified box width.
     * 
     * @param text  The text to be wrapped.
     * @param width The width of the box.
     */
    private void printWrappedLine(String text, int width) {
        while (text.length() > width) {
            System.out.printf("║ %-" + (width - 2) + "s ║\n", text.substring(0, width - 2));
            text = text.substring(width - 2);
        }
        System.out.printf("║ %-" + (width - 2) + "s ║\n", text);
    }

    /**
     * Generates a string representation of the flashcard formatted within a box.
     * 
     * @return A formatted string displaying the flashcard's contents.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("╔").append("═".repeat(50)).append("╗\n");
        builder.append(String.format("║ %-48s ║\n", "Word/Phrase: " + text));
        builder.append(String.format("║ %-48s ║\n", "Pronunciation: " + pronunciation));
        builder.append(String.format("║ %-48s ║\n", "Translation: " + translation));

        if (!genre.equals("PHRASE")) {
            builder.append(String.format("║ %-48s ║\n", "Genre: " + genre + " | Difficulty: " + difficulty));
        }

        builder.append("╚").append("═".repeat(50)).append("╝");
        return builder.toString();
    }

    /**
     * Generates a list of flashcards by loading words and phrases from data sources.
     * 
     * @return A list of flashcards loaded from available data.
     */
    public static List<Flashcard> generateFlashcards() {
        List<Flashcard> flashcards = new ArrayList<>();

        Map<String, List<Word>> wordsMap = DataLoader.loadWords();
        if (wordsMap != null) {
            for (List<Word> words : wordsMap.values()) {
                for (Word word : words) {
                    flashcards.add(new Flashcard(
                            word.getText(),
                            word.getForeign(),
                            word.getPronounce(),
                            word.getGenre().toString(),
                            word.getDifficulty()
                    ));
                }
            }
        }

        List<Flashcard> phraseFlashcards = DataLoader.loadPhraseCards();  // Ensure loadPhrases() returns List<Flashcard>
        flashcards.addAll(phraseFlashcards);

        return flashcards;
    }

    /**
     * Main method for testing flashcard generation and display.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Generate and display flashcards
        List<Flashcard> flashcards = generateFlashcards();
        System.out.println("Loaded Flashcards:");
        for (Flashcard card : flashcards) {
            card.display();  // Use the display method for visual appeal
            System.out.println();  // Add a blank line between cards
        }
    }
}
