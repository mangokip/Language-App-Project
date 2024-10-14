package com.app;

public class CockySpeakUI {
    private CockySpeak cockySpeak = new CockySpeak(); // Initialize CockySpeak

    public static void main(String[] args) {
        CockySpeakUI uI = new CockySpeakUI();


        
        if (uI.login("JohnDoe", "password123")) {
            System.out.println("Logged in successfully!");
            
        } else {
            System.out.println("Login failed.");
        }
    }

    /**
     * Attempts to log in a user.
     *
     * @param userName The username to log in with.
     * @param password The password to log in with.
     * @return true if login is successful, false otherwise.
     */
    private boolean login(String userName, String password) {
        cockySpeak.login(userName, password);
        return cockySpeak.getCurrentUser() != null;
    }
}
