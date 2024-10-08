import java.util.UUID;
import java.util.HashMap;

public class User {
    private UUID id;
    private String userName;
    private String password;
    private String email;
    private HashMap<Language, ProgressTracker> progressTrackers;

    public User() {
        this.id = UUID.randomUUID();
        this.progressTrackers = new HashMap<>();
    }

    public User(String name, String password, String email) {
        this();
        this.userName = name;
        this.password = password;
        this.email = email;
    }

    public void register() {
        // Implementation for user registration
    }

    public boolean isMatch(String name, String password, String email) {
        return this.userName.equals(name) && this.password.equals(password) && this.email.equals(email);
    }

    public boolean isMatch(String email) {
        return this.email.equals(email);
    }

    public void resetPassword(String email) {
        // Implementation for password reset
    }

    public void displayProfile() {
        System.out.println("User Profile for " + userName);
        System.out.println("Email: " + email);
        // Add more profile information as needed
    }

    public User getUser() {
        return this;
    }

    public void selectLanguage(Language language) {
        if (!progressTrackers.containsKey(language)) {
            progressTrackers.put(language, new ProgressTracker());
        }
        // Additional logic for language selection
    }

    public HashMap<Language, ProgressTracker> getProgressTracker() {
        return progressTrackers;
    }

    public UUID getId() {
        return id;
    }
}
