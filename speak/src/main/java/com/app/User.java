package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The User class represents an individual user within the app. Each user has a unique ID, username, 
 * password, email, and a set of progress trackers for different languages they are learning.
 * The class provides methods to manage user information, select languages, track progress, 
 * and switch difficulty levels for each language.
 */
public class User {

    private UUID id; // Unique identifier for the user
    private String username; // Username for the user
    private String password; // User's password
    private String email; // User's email address
    private HashMap<Language, ProgressTracker> progressTrackers; // Map to track progress for each language
    private String currentModule; // Current module the user is on
    private Language currentLanguage; // Current language being studied

    /**
     * Constructor to initialize a user with basic details.
     * 
     * @param username The user's username.
     * @param password The user's password.
     * @param email    The user's email.
     */
    public User(String username, String password, String email) {
        this.id = UUID.randomUUID();
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.progressTrackers = new HashMap<>();
        this.currentModule = "Module 1";
    }

    /**
     * Constructor with UUID to initialize user details, typically for loading existing users.
     * 
     * @param uuid     Unique identifier of the user.
     * @param username The user's username.
     * @param password The user's password.
     * @param email    The user's email.
     */
    public User(UUID uuid, String username, String password, String email) {
        this.id = uuid;
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.progressTrackers = new HashMap<>();
    }

    /**
     * @return The unique ID of the user.
     */
    public UUID getUUID() {
        return this.id;
    }

    /**
     * @return The username of the user.
     */
    public String getUserName() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username New username for the user.
     */
    public void setUserName(String username) {
        this.username = username;
    }

    /**
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password New password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * 
     * @param email New email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Selects a language for the user to learn.
     *
     * @param language Language chosen by the user.
     */
    public void selectedLanguage(Language language) {
        // Implementation to be added if necessary
    }

    /**
     * Sets the current module the user is working on.
     * 
     * @param module New module name.
     */
    public void setCurrentModule(String module) {
        this.currentModule = module;
    }

    /**
     * @return The current module the user is working on.
     */
    public String getCurrentModule() {
        return currentModule;
    }

    /**
     * Provides access to the progress trackers for each language the user is learning.
     *
     * @return HashMap mapping each language to its corresponding ProgressTracker.
     */
    public HashMap<Language, ProgressTracker> getProgressTracker() {
        return this.progressTrackers;
    }

    /**
     * Retrieves the progress tracker for a specific language.
     * 
     * @param language Language for which the progress tracker is requested.
     * @return The ProgressTracker for the given language, or null if none exists.
     */
    public ProgressTracker getLanguageProgressTracker(Language language) {
        ProgressTracker tracker = progressTrackers.get(language);
        if (tracker == null) {
            System.out.println("Progress tracker not found for language: " + language.getLanguageCode());
        }
        return tracker;
    }

    /**
     * Initializes a new ProgressTracker for a specified language if none exists.
     * 
     * @param language The language for which the progress tracker is created.
     */
    public void createLanguageProgress(Language language) {
        if (!progressTrackers.containsKey(language)) {
            ProgressTracker tracker = new ProgressTracker(0, 0, 0, 0, 0, 10, 0, new BeginnerState());
            progressTrackers.put(language, tracker);
            System.out.println("Language " + language.getLanguageCode() + " initialized for " + username);
        } else {
            System.out.println("Language " + language.getLanguageCode() + " already initialized for " + username);
        }
    }

    /**
     * Gets a list of progress summaries for each language the user is learning.
     * 
     * @return A list of strings, each containing the progress summary for a language.
     */
    public List<String> getLanguageProgress() {
        List<String> progressList = new ArrayList<>();
        for (Map.Entry<Language, ProgressTracker> entry : progressTrackers.entrySet()) {
            Language language = entry.getKey();
            ProgressTracker progressTracker = entry.getValue();
            progressList.add("Language: " + language.getLanguageCode() + ", Progress: " + progressTracker.displayProgress());
        }
        return progressList;
    }

    /**
     * Switches the difficulty level of the progress tracker for a specific language.
     * 
     * @param language The language for which the difficulty level is being changed.
     * @param newState The new state to be set.
     */
    public void switchDifficulty(Language language, State newState) {
        ProgressTracker tracker = progressTrackers.get(language);
        if (tracker != null) {
            tracker.setState(newState);
            System.out.println("Switched to " + newState.toString() + " difficulty for " + language.getLanguageCode());
        } else {
            System.out.println("No progress tracker found for language: " + language.getLanguageCode());
        }
    }
}
