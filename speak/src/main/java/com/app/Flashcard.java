package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Flashcard {

    private String text;
    private String translation;
    private String pronunciation;
    private String genre;        // Only for words
    private Integer difficulty;  // Only for words

    public Flashcard() {
        this.text = "";
        this.translation = "";
        this.pronunciation = "";
        this.genre = "";
        this.difficulty = 0;
    }

    public Flashcard(String text, String translation, String pronunciation, String genre, int difficulty) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.genre = genre;
        this.difficulty = difficulty;
    }

    // Constructor for phrases (without genre and difficulty)
    public Flashcard(String text, String translation, String pronunciation) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.genre = "PHRASE";
        this.difficulty = 1;  // Default difficulty for phrases
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

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

    private void printWrappedLine(String text, int width) {
        while (text.length() > width) {

            System.out.printf("║ %-" + (width - 2) + "s ║\n", text.substring(0, width - 2));

            text = text.substring(width - 2);
        }

        System.out.printf("║ %-" + (width - 2) + "s ║\n", text);
    }

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
