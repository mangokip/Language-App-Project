
import java.util.HashMap;


/*
 * The User class holds all the info that will be registered and manipulated as a user profile
 * for those who will be using the CockySpeak application.
 */
public class User {

    private String username;
    private String password;
    private String email;
    private HashMap<Language, ProgressTracker> progressTracker;

    /*
     * Basic constructor
     */
    public User() {

    }

    /**
     * Parametized Constructor - ASK IF WE NEED TO ADD UUID TOO
     *
     * @param username - user's username of choice
     * @param password - user's password of choice
     * @param email - user's registered email
     */
    public User(String username, String password, String email) {

    }

    /**
     * Method to register user given attributes
     *
     * @param username - user's username to be passed
     * @param password - user's password to be passed
     * @param email - user's email to be passed
     */
    public void register(String username, String password, String email) {

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
