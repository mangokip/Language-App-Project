package com.app;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.narration.Narriator;

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
    private Flashcard selectedWord;

    public CockySpeak() {
        userList = UserList.getInstance();
        wordList = WordList.getInstance();

        for (User loadedUser : loader.loadUsers()) {
            userList.addUser(loadedUser.getUserName(), loadedUser.getPassword(), loadedUser.getEmail());
        }
        flashcards = Flashcard.generateFlashcards();
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
        user.createLanguageProgress(language);  // Ensure progress is initialized
        writer.saveUsers(userList.getUsers());
        System.out.println("Language set to: " + language.getLanguageCode());
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

       
        if (selectedLanguageCode.isEmpty()) {
            System.out.println("Invalid input. Defaulting to Spanish.");
            return new Language("Spanish");  
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

    public void login(String username, String password) {
        if (userList.hasUser(username)) {
            User thisUser = userList.getUser(username);
            if (password.equals(thisUser.getPassword())) {
                this.user = thisUser;
                System.out.println("Welcome " + username);

                
                Language spanish = new Language("Spanish");
                if (user.getLanguageProgressTracker(spanish) == null) {
                    user.createLanguageProgress(spanish);
                }

               
                writer.saveUsers(userList.getUsers());

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

        flashcards = Flashcard.generateFlashcards();

        System.out.println("Flashcards loaded: " + flashcards.size());

        for (Flashcard card : flashcards) {
            card.display();
            System.out.println();
        }
    }

    /**
     * Allows the user to input a word or phrase and searches for it in the
     * flashcards.
     */
    public void searchAndStorePhraseOrWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word or phrase: ");
        String input = scanner.nextLine().trim().toLowerCase();

        Optional<Flashcard> foundItem = flashcards.stream()
                .filter(card -> card.getText().equalsIgnoreCase(input))
                .findFirst();

        if (foundItem.isPresent()) {
            selectedWord = foundItem.get();
            System.out.println("Found: " + selectedWord.getText());

            pronounceTextWithPolly(selectedWord.getText());
        } else {
            System.out.println("Not found.");
        }
    }

    public void startModule(String moduleName) {
        if (currentLanguage == null) {
            System.out.println("No language set. Defaulting to Spanish.");
            setLanguage(new Language("Spanish"));
        }

        System.out.println("\nStarting " + moduleName + "...");
        Lesson lesson = new Lesson(moduleName, currentLanguage);  // Use the current language
        int score = lesson.playLesson();

        ProgressTracker tracker = user.getLanguageProgressTracker(currentLanguage);
        if (tracker == null) {
            System.out.println("Error: No progress tracker found for " + currentLanguage.getLanguageCode());
            return;  // Exit if no tracker is found
        }

        if (score >= 80) {
            System.out.println("You passed " + moduleName + "!");
            tracker.completeLesson();  // Mark the lesson as completed
        } else {
            System.out.println("You did not pass " + moduleName + ". Please try again.");
        }

        writer.saveUsers(userList.getUsers());  // Save progress
    }

    public void resumeFromProgress(String languageCode) {
        ProgressTracker tracker = user.getLanguageProgressTracker(new Language(languageCode));
        int completedLessons = tracker.getCompletedLessons();

        if (completedLessons < 1) {
            startModule("Module 1");
        } else {
            startModule("Module 2");
        }
    }

    /**
     * Uses AWS Polly to pronounce the given text (word or phrase).
     */
    private void pronounceTextWithPolly(String text) {
        try {
            System.out.println("Pronouncing: " + text);

            Narriator.playSound(text);

        } catch (Exception e) {
            System.err.println("Error pronouncing the text: " + e.getMessage());
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

}
