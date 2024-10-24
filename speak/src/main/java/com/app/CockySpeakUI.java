package com.app;

import java.util.Scanner;
import java.util.ArrayList;

public class CockySpeakUI {

    private static CockySpeak cockySpeak = new CockySpeak(); // Initialize CockySpeak

    public static void main(String[] args) {
        scenario2();
    }

    public static void scenario1() {
        System.out.println("User to Login: JohnDoe");
        cockySpeak.login("JohnDoe", "password123");
        //todo - going through intermediate lesson
    }

    public static void scenario2() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Username:");
        String username = keyboard.nextLine();
        System.out.println("Enter Password:");
        String password = keyboard.nextLine();
        System.out.println("Enter Email:");
        String email = keyboard.nextLine();
        cockySpeak.register(username, password, email);
        cockySpeak.login(username, password);

        Language selectedLanguage = cockySpeak.promptLanguageSelection();
        cockySpeak.setLanguage(selectedLanguage);
        cockySpeak.promptDifficultySelection(selectedLanguage);

        WordList wordList = WordList.getInstance();
        Word correctWord = wordList.getRandomWord(selectedLanguage);
        ArrayList<Word> wordsForPhrase = new ArrayList<Word>();
        wordsForPhrase.add(correctWord);
        for (int i = 0; i < 3; i++) {
            wordsForPhrase.add(wordList.getRandomWord(selectedLanguage));
        }
        Phrase phrase = new Phrase(wordsForPhrase, "nothing");
        cockySpeak.playFillBlank(1, correctWord, phrase, selectedLanguage);

        //todo - rest of scenario
    }
}


    