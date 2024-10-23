package com.app;
//Carson Sessoms

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents a phrase used in a phrase-based question.
 * 
 * @author Lamorra Strong
 */
public class PhraseQuestion {
    private Question baseGame;
    private ArrayList<String> phrase;
    private ArrayList<String> correctAnswers;
/**
     * Constructs a PhraseQuestion object with the given base game, phrase, and correct answers.
     *
     * @param baseGame The base Question object that this game builds upon.
     * @param phrase The list of phrases used in the game.
     * @param correctAnswers The list of correct answers for the phrases.
     */
    public PhraseQuestion(Question baseGame, ArrayList<String> phrase, ArrayList<String> correctAnswers) {
        this.baseGame = baseGame;
        this.phrase = phrase;
        this.correctAnswers = correctAnswers;
    }
    /**
     * Sets up a blank version of the phrase game. 
     * This method currently does not implement any logic but serves as a placeholder.
     *
     * @param baseGame The base Question object for the phrase game.
     */
    public void PhraseBlank(Question baseGame) {

    }
    /**
     * Starts the phrase question game. 
     * Handles the flow of the game and checks answers.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Phrase Game!");

        for (int i = 0; i < phrase.size(); i++) {
            System.out.println("Complete the phrase: " + phrase.get(i));
            String userAnswer = scanner.nextLine();
            
            // Check if the user's answer is correct
            if (userAnswer.equalsIgnoreCase(correctAnswers.get(i))) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + correctAnswers.get(i));
            }
        }
        
        System.out.println("Game Over! Thanks for playing.");
        scanner.close();
    }
    /**
     * Ends the phrase question game. Currently, the method is not implemented.
     */

    public void endGame() {
        // Implementation to clean up game state can go here
        System.out.println("Thank you for playing the Phrase Game!");
    }

    /**
     * Displays the instructions for the phrase question game.
     */
    public void displayInstructions() {
        System.out.println("Instructions:");
        System.out.println("1. You will be given phrases with missing words.");
        System.out.println("2. Type in your answer for each phrase.");
        System.out.println("3. You will receive feedback on whether your answer is correct or not.");
        System.out.println("4. Enjoy the game!");
    }

    /**
     * Gets the base Question object associated with this game.
     *
     * @return The base Question object.
     */
    public Question getBaseGame() {
        return baseGame;
    }
    /**
     * Sets the base Question object for this game.
     *
     * @param baseGame The base Question to set.
     */
    public void setBaseGame(Question baseGame) {
        this.baseGame = baseGame;
    }

    /**
     * Gets the list of phrases used in this game.
     *
     * @return An ArrayList of phrases.
     */
    public ArrayList<String> getPhrase() {
        return phrase;
    }
    /**
     * Sets the list of phrases for this game.
     *
     * @param phrase The list of phrases to set.
     */
    public void setPhrase(ArrayList<String> phrase) {
        this.phrase = phrase;
    }

    /**
     * Gets the list of correct answers for the phrases.
     *
     * @return An ArrayList of correct answers.
     */

    public ArrayList<String> getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Sets the list of correct answers for the phrases.
     *
     * @param correctAnswers The list of correct answers to set.
     */
    public void setCorrectAnswers(ArrayList<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}