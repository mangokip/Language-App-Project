package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String password;
    private String email;
    private HashMap<Language, ProgressTracker> progressTrackers;
    private String currentModule;
    private Language currentLanguage;

    public User(String username, String password, String email) {
        this.id = UUID.randomUUID();
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.progressTrackers = new HashMap<>();
        this.currentModule = "Module 1";
    }

    public User(UUID uuid, String username, String password, String email) {
        this.id = uuid;
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.progressTrackers = new HashMap<>();
    }

    public UUID getUUID() {
        return this.id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * User's language of choice to be selected
     *
     * @param language - language to be chosen
     */
    public void selectedLanguage(Language language) {

    }

    public void setCurrentModule(String module) {
        this.currentModule = module;
    }

    public String getCurrentModule() {
        return currentModule;
    }

    /**
     * Grabs a ProgressTracker object to be linked to a user for each language
     * they are learning
     *
     * @return -
     * HashMap<Language (key of what the progressTracker will be assigned to), ProgressTracker (progress tracker object to be linked to language)>
     */
    public HashMap<Language, ProgressTracker> getProgressTracker() {
        return this.progressTrackers;

    }

    public ProgressTracker getLanguageProgressTracker(Language language) {
        ProgressTracker tracker = progressTrackers.get(language);
        if (tracker == null) {
            System.out.println("Progress tracker not found for language: " + language.getLanguageCode());
        }
        return tracker;
    }
    

    public void createLanguageProgress(Language language) {
        if (!progressTrackers.containsKey(language)) {
            ProgressTracker tracker = new ProgressTracker(0, 0, 0, 0, 0, 10, 0, new BeginnerState());
            progressTrackers.put(language, tracker);  // Add tracker to map
            System.out.println("Language " + language.getLanguageCode() + " initialized for " + username);
        } else {
            System.out.println("Language " + language.getLanguageCode() + " already initialized for " + username);
        }
    }
    

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
     * Switch the state (difficulty) of the ProgressTracker for a given
     * language.
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

    // public void setCurrentLanguage(Language language){
    //     this.currentLanguage = language;
    // }
    // public Language getCurrentLanguage(){
    //     return this.currentLanguage;
    // }
}
