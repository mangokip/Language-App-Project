package com.app;
import java.util.HashMap;
import java.util.UUID;

/*
 * The User class holds all the info that will be registered and manipulated as a user profile
 * for those who will be using the CockySpeak application.
 */
public class User {

    private String uniqueID;
    private String username;
    private String password;
    private String email;
    private HashMap<Language, ProgressTracker> progressTracker;

    /*
     * Basic constructor
     */
    public User() {
        username = null;
        password = null;
        email = null;
        uniqueID = null;
    }

    /**
     * Parametized Constructor - ASK IF WE NEED TO ADD UUID TOO
     *
     * @param username - user's username of choice
     * @param password - user's password of choice
     * @param email - user's registered email
     */
    public User(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.uniqueID = UUID.randomUUID().toString();
    }

    /**
     * Method to register user given attributes
     *
     * @param user - user to be registered into list
     */
    public void register(User user) {
        
    }

    /**
     * Independent check of match for email to be integrated with resetPassword
     *
     * @param email - email to be checked
     * @return - return boolean, true if match false if not
     */
    public boolean isMatch(String email) {
        
    }

    /**
     * Basic check on if user passed is a match to an existing user
     *
     * @param username - username to be checked
     * @param password - password to be checked
     * @param email - email to be checked
     * @return - return boolean, true if match false if not
     */
    public boolean isMatch(String username, String password, String email) {
        
    }

    /**
     * Resets user's password based on passed email if it exists with a user
     *
     * @param email
     */
    public void resetPassword(String email) {

    }

    /**
     * Grabs user object
     *
     * @return - User object
     */
    public User getUser() {

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
