package com.app;
import java.util.ArrayList;
import java.util.List;

public class Flashcard {
    private String text;
    private String translation;
    private String pronunciation;
    private String genre;        
    private Integer difficulty;  

    
    public Flashcard(String text, String translation, String pronunciation, String genre, int difficulty) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.genre = genre;
        this.difficulty = difficulty;
    }

    
    public Flashcard(String text, String translation, String pronunciation) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.genre = "PHRASE";
        this.difficulty = 1; 
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

    @Override
    public String toString() {
        if (genre.equals("PHRASE")) {
            return String.format("%s: %s [%s]", text, translation, pronunciation);
        }
        return String.format("%s (%s): %s [%s, Difficulty: %d]", text, pronunciation, translation, genre, difficulty);
    }

    /**
     * Generates a combined list of Flashcards from both words and phrases.
     *
     * @return List of Flashcards
     */
    public static List<Flashcard> generateFlashcards() {
        List<Flashcard> flashcards = new ArrayList<>();

        
        List<Flashcard> words = DataLoader.loadWords();
        List<Flashcard> phrases = DataLoader.loadPhrases();

        flashcards.addAll(words);
        flashcards.addAll(phrases);

        return flashcards;
    }

    public static void main(String[] args) {
        List<Flashcard> flashcards = generateFlashcards();

        System.out.println("Loaded Flashcards:");
        for (Flashcard card : flashcards) {
            System.out.println(card);
        }
    }
}
