package com.app;

import java.util.List;
import java.util.Scanner;

public class CockySpeak {

    private Language currentLanguage;
    private UserList userList;
    private WordList wordList;
    private User user;
    private ProgressTracker currentProgressTracker;
    private int difficulty;
    private DataLoader loader = new DataLoader();
    private DataWriter writer = new DataWriter();
    private List<Flashcard> flashcards;

    public CockySpeak() {
        userList = UserList.getInstance();
        wordList = WordList.getInstance();

        for (User loadedUser : loader.loadUsers()) {
            userList.addUser(loadedUser.getUserName(), loadedUser.getPassword(), loadedUser.getEmail());
        }
    }

    /**
     * Sets the active language for the CockySpeak system.
     *
     * @param language the code for the language to switch to
     */
    public void setLanguage(Language language) {
        if (language == null) {
            System.out.println("Invalid language. Defaulting to Spanish.");
            language = new Language("Spanish");
        }
        this.currentLanguage = language;
        user.createLanguageProgress(language);
        writer.saveUsers(userList.getUsers());
        System.out.println("Language set to: " + language.getLanguageCode());
        this.currentProgressTracker = user.getLanguageProgressTracker(currentLanguage);
        if(currentProgressTracker.getState().toString().equals("INTERMEDIATE")){
            this.difficulty = 2;
        }else if(currentProgressTracker.getState().toString().equals("EXPERT")){
            this.difficulty = 3;
        }else{
            this.difficulty = 1;
        }
    }

    /**
     * Prompts the user to select a language.
     *
     * @return
     */
    public Language promptLanguageSelection() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Available languages: Spanish");
        System.out.print("Please select a language: ");
        String selectedLanguageCode = keyboard.nextLine().trim();

        // Ensure a valid Language object is returned
        if (selectedLanguageCode.isEmpty()) {
            System.out.println("Invalid input. Defaulting to Spanish.");
            return new Language("Spanish");  // Default to Spanish if input is invalid
        }
        return new Language(selectedLanguageCode);
    }

    public void promptDifficultySelection(Language language) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Select difficulty level: BEGINNER, INTERMEDIATE, EXPERT");
        String selectedDifficulty = keyboard.nextLine().toUpperCase();

        switch (selectedDifficulty) {
            case "BEGINNER":
                user.switchDifficulty(language, new BeginnerState());
                break;
            case "INTERMEDIATE":
                user.switchDifficulty(language, new IntermediateState());
                break;
            case "EXPERT":
                user.switchDifficulty(language, new ExpertState());
                break;
            default:
                System.out.println("Invalid input. Defaulting to BEGINNER.");
                user.switchDifficulty(language, new BeginnerState());
                break;
        }
    }

    /**
     * Attempts to log in a user.
     *
     * @param username The username to log in with.
     * @param password The password to log in with.
     */
    public void login(String username, String password) {
        if (userList.hasUser(username)) {
            User thisUser = userList.getUser(username);
            if (password.equals(thisUser.getPassword())) {
                this.user = thisUser;
                System.out.println("Welcome " + username);
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("Username not found");
        }
    }

    /**
     * Registers a new user and saves to storage.
     */
    public boolean register(String username, String password, String email) {
        if (userList.hasUser(username)) {
            System.out.println("Username already exists.");
            return false;
        }
        userList.addUser(username, password, email);
        System.out.println("User registered successfully!");

        DataWriter writer = new DataWriter();
        writer.saveUsers(userList.getUsers());
        return true;
    }

    public void logout() {
        writer.saveUsers(userList.getUsers());
        System.out.println("Progress saved... Logging out");
        System.exit(0);
    }

    public void changeUsername(String newUsername) {
        user.setUserName(newUsername);
        writer.saveUsers(userList.getUsers());
        System.out.println("Username changed to: " + newUsername);
    }

    public void changePassword(String newPassword) {
        user.setPassword(newPassword);
        writer.saveUsers(userList.getUsers());
        System.out.println("Password changed successfully");
    }

    public void loadFlashcards() {
        // Generate flashcards by loading words and phrases from DataLoader
        flashcards = Flashcard.generateFlashcards();
    
        System.out.println("Flashcards loaded: " + flashcards.size());
    
        // Print all flashcards to verify loading
        for (Flashcard card : flashcards) {
            card.display();  // Call the display() method for each card
            System.out.println();  // Add a blank line between flashcards
        }
    }
    

    /**
     * Gets the currently logged-in user.
     *
     * @return The currently logged-in user, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return user;
    }

    public Language getCurrentLanguage() {
        return currentLanguage;
    }
    public void playFillBlank(int diff, Word correctAnswer, Phrase sentence, Language language){
        FillBlank fillBlank = new FillBlank(diff, correctAnswer, sentence, language);
        Scanner k = new Scanner(System.in);
        fillBlank.toString();
    }

    public void playVocabularyMatching(Word word){
        VocabularyMatching vocabularyMatching = new VocabularyMatching(currentLanguage, difficulty, word);


    }

    public void playPhraseQuesiton(){
        
    }

}