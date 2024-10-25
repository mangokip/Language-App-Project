package com.app;

import java.util.ArrayList;
import java.util.List;

public class Flashcard {
    private String text;
    private String translation;
    private String pronunciation;

    public Flashcard(String text, String translation, String pronunciation) {
        this.text = text;
        this.translation = translation;
        this.pronunciation = pronunciation;
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

    @Override
    public String toString() {
        return text + " (" + pronunciation + "): " + translation;
    }

    public static List<Flashcard> generateFlashcards() {
        List<Flashcard> flashcards = new ArrayList<>();
        
        // Add 10 words
        flashcards.add(new Flashcard("perro", "dog", "peh-roh"));
        flashcards.add(new Flashcard("gato", "cat", "gah-toh"));
        flashcards.add(new Flashcard("mujer", "woman", "moo-hehr"));
        flashcards.add(new Flashcard("hombre", "man", "ohm-breh"));
        flashcards.add(new Flashcard("feliz", "happy", "feh-lees"));
        flashcards.add(new Flashcard("rojo", "red", "roh-hoh"));
        flashcards.add(new Flashcard("azul", "blue", "ah-sool"));
        flashcards.add(new Flashcard("amigo", "friend", "ah-mee-goh"));
        flashcards.add(new Flashcard("libro", "book", "lee-broh"));
        flashcards.add(new Flashcard("casa", "house", "kah-sah"));

        // Add 10 phrases
        flashcards.add(new Flashcard("El perro corre en el parque.", "The dog runs in the park.", ""));
        flashcards.add(new Flashcard("La casa es roja.", "The house is red.", ""));
        flashcards.add(new Flashcard("Tengo un libro nuevo.", "I have a new book.", ""));
        flashcards.add(new Flashcard("El amigo de Juan está feliz.", "Juan's friend is happy.", ""));
        flashcards.add(new Flashcard("Ella es una mujer amable.", "She is a kind woman.", ""));
        flashcards.add(new Flashcard("Me gusta el color azul.", "I like the color blue.", ""));
        flashcards.add(new Flashcard("El hombre lee un libro.", "The man reads a book.", ""));
        flashcards.add(new Flashcard("El gato duerme en la casa.", "The cat sleeps in the house.", ""));
        flashcards.add(new Flashcard("¿Es azul tu coche?", "Is your car blue?", ""));
        flashcards.add(new Flashcard("Mis amigos están muy felices hoy.", "My friends are very happy today.", ""));

        return flashcards;
    }
}
