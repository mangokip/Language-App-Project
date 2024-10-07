
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

    /*
     * Parametized Constructor
     */
    public User(String username, String password, String email) {

    }

    /*
     * This method will register a user with only a username and password.
     */
    public void register(String username, String password) {

    }

    /*
     * Overloaded method for if the user wants to register an email as well
     */
    public void register(String username, String password, String email) {

    }

   /*
    * 
    */
    public boolean isMatch(String username, String password) {

    }

    /*
     * Resets password based on user's email.
     */
    public void resetPassword(String email) {

    }

    /*
     * Get method for users
     * @return User object
     */
    public User getUser() {

    }

    public void selectedLanguage(Language language) {

    }

   
    public HashMap<Language, ProgressTracker> getProgressTracker() {

    }
}
