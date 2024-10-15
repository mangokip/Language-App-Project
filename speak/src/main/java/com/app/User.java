package com.app;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private UUID id;
    private String username;
    private String password;
    private String email; //what does type do 
    private HashMap<String, ProgressTracker> progressTrackers;

    
    

    public User(String username, String password, String email) {
        setUserName(username);
        setPassword(password);
        setEmail(email);
    }
    public User(UUID uuid, String username, String password, String email) {
        this.id = UUID.randomUUID();
        setUserName(username);
        setPassword(password);
        setEmail(email);
    }
    public UUID getUUID() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
    
    public HashMap<String, ProgressTracker> getProgressTrackers() {
        return progressTrackers;
    }

    /**
     * User's language of choice to be selected
     *
     * @param language - language to be chosen
     */
    public void selectedLanguage(Language language) {

    }

    /**
     * Grabs a ProgressTracker object to be linked to a user for each language
     * they are learning
     *
     * @return -
     * HashMap<Language (key of what the progressTracker will be assigned to), ProgressTracker (progress tracker object to be linked to language)>
     */
    public HashMap<Language, ProgressTracker> getProgressTracker() {

    }

   

}


    