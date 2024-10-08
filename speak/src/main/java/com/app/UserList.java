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

    // Add a user to the system
    public void addUser(User user) {
        users.add(user);
    }

   public boolean isMatch(String email) {
        for(User user : users) {
            if(user.getEmail)
        }
   }
}