package com.app;
import java.util.HashMap;
import java.util.UUID;
import java.util.Scanner;


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
    Scanner keyboard = new Scanner(System.in);

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
        this.uniqueID = UUID.randomUUID().toString(); //should I keep this here or just change the parameters to include UUID and when someone makes a user, they enter user-pass-email but not UUID and it's just randomly generated and automatically applied for them?
    }

    /**
     * Method to register user given attributes
     *
     * @param user - user to be registered into list
     */
    public void register(User user) {
        // is this method even needed if using userlist?
    }

    /**
     * Independent check of match for email to be integrated with resetPassword
     *
     * @param email - email to be checked
     * @return - return boolean, true if match false if not
     */
    public boolean isMatch(String email) {
        return (email == null ? this.email == null : email.equals(this.email));
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
        return ((username == null ? this.username == null : username.equals(this.username)) &&
                (password == null ? this.password == null : password.equals(this.password)) &&
                (email == null ? this.email == null : email.equals(this.email)));
    }

    /**
     * Resets user's password based on passed email if it exists with a user
     *
     * @param email
     */
    public void resetPassword(String email) {
        if(email.equals(this.email)) {
            String newPassword = null;
            System.out.println("Please enter your new password.");
            newPassword = keyboard.nextLine();
            this.password = newPassword;
        }
    }

    
    public String getEmail() {
        return this.email;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
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
