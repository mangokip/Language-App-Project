package com.app;

public class CockySpeak {
    private Language currentLanguage;
    private UserList userList;
    private User user;

    public CockySpeak() {
        userList = UserList.getInstance(); 
        DataLoader loader = new DataLoader();

    
        for (User loadedUser : loader.loadUsers()) {
            userList.addUser(loadedUser.getUserName(), loadedUser.getPassword(), loadedUser.getEmail());
        }
    }

    /**
     * Initializes the CockySpeak system with a default language.
     *
     * @param languageCode the code for the default language (e.g., "en" for English)
     */
    public CockySpeak(String languageCode) {
        this(); // Call the default constructor to load users
        this.currentLanguage = new Language(languageCode);
        currentLanguage.loadLanguage();
    }

    /**
     * Sets the active language for the CockySpeak system.
     *
     * @param languageCode the code for the language to switch to
     */
    public void setLanguage(String languageCode) {
        this.currentLanguage.setLanguageCode(languageCode);
        currentLanguage.loadLanguage();
    }

    /**
     * Attempts to log in a user.
     *
     * @param username The username to log in with.
     * @param password The password to log in with.
     */
    public void login(String username, String password) {
        if (userList.hasUser(username)) {
            User thisUser = userList.getUser(username);
            if (password.equals(thisUser.getPassword())) {
                this.user = thisUser;
                System.out.println("Welcome " + username);
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("Username not found");
        }
    }

    public boolean register(String username, String password, String email) {
        if (userList.hasUser(username)) {
            System.out.println("Username already exists.");
            return false; 
        }
        userList.addUser(username, password, email); 
        DataWriter writer = new DataWriter();
        writer.saveUsers(userList.getUsers());
        return true; 
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return The currently logged-in user, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return user;
    }
}
