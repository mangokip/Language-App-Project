import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class User {

    private UUID id;
    private String userName;
    private String password;
    private String email; //what does type do 
    private HashMap<String, ProgressTracker> progressTrackers;

    
    

    public User(String userName, String password, String email, String type) {
        this.id = UUID.randomUUID();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.progressTrackers = new HashMap<>();
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setProgressTrackers(HashMap<String, ProgressTracker> progressTrackers) {
        this.progressTrackers = progressTrackers;
    }

    public boolean  facadeRegister(String name, String userName, String password, String email, String type) {
        UserList userList = UserList.getInstance();
        if(userList.findUser(userName, password)== null){
            User newUser = new User(userName, password, email, type);
            userList.getListOfUsers().add(newUser);
            userList.saveUsers();
            return true;
        }
        return false;
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


    public void selectLanguage(Language language) {
        if (!progressTrackers.containsKey(language)) {
            progressTrackers.put(language, new ProgressTracker());
        }
        // Additional logic for language selection
    }

    public void saveUser() throws IOException{
        objectMapper.writeValue(new File(Language-App-Project/json), this);
    }

    public static User loadUser() // does the user class need to read anything like for instance, saving a user and loading a user. 

    
}
