package com.app;

import java.util.ArrayList;

public class UserList {

    // Attributes
    private static UserList userList;
    private ArrayList<User> users;

    // Constructor (private to enforce singleton pattern)
    private UserList() {
        users = new ArrayList<>();
    }

    // Singleton Pattern - getInstance method
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The username to search for.
     * @return The User object if found, null otherwise.
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Checks if a user exists with the given username.
     *
     * @param username The username to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean hasUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a user to the list if the username is not already taken.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param email    The email for the new user.
     * @return true if the user was added successfully, false otherwise.
     */
    public boolean addUser(String username, String password, String email) {
        if (hasUser(username)) {
            System.out.println("User already exists with username: " + username);
            return false;
        }
        users.add(new User(username, password, email));
        return true;
    }

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email The email to check.
     * @return true if a user with the email exists, false otherwise.
     */
    public boolean isMatch(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        System.out.println("Invalid email. Try again.");
        return false;
    }

    /**
     * Returns the list of users.
     *
     * @return ArrayList of users.
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
