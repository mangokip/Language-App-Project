package com.app;

import java.util.ArrayList;
import java.util.List;

public class Flashcard {

    private String text;
    private String translation;
    private String pronunciation;
    private String genre;        // Only for words
    private Integer difficulty;  // Only for words

    // Constructor for words (with genre and difficulty)
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
        int boxWidth = 50; // Set the fixed width for the box
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
            // Print the first part of the text that fits in the box
            System.out.printf("║ %-"+(width-2)+"s ║\n", text.substring(0, width-2));
            // Move the remaining text to the next line
            text = text.substring(width-2);
        }
        // Print any remaining text
        System.out.printf("║ %-"+(width-2)+"s ║\n", text);
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

    // Generate flashcards from DataLoader
    public static List<Flashcard> generateFlashcards() {
        List<Flashcard> flashcards = new ArrayList<>();
        flashcards.addAll(DataLoader.loadWords());
        flashcards.addAll(DataLoader.loadPhrases());
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
