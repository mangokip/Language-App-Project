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

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean hasUser(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Add a user to the system
    public void addUser(User user) {
        users.add(user);
    }

    public boolean isMatch(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        System.out.println("Invalid email. Try again.");
        return false;

    }
}
