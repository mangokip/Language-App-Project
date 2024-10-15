package com.app;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The DataWriter class is responsible for saving data related to users
 * in the CockySpeak application. It provides methods to save users to a data source.
 * @author David Dinh
 */
public class DataWriter extends DataConstants {
    

    /**
     * Saves the list of users to the data source.
     *
     * @param users the ArrayList of User objects to be saved
     */
    public void saveUsers(ArrayList<User> users) {
        JSONArray userList = loadExistingUsers();

        for (User user : users) {
            boolean updated = false;
            for (int i = 0; i < userList.size(); i++) {
                JSONObject existingUser = (JSONObject) userList.get(i);
                if (existingUser.get("UUID").equals(user.getUUID())) {
                    // Update existing user data
                    updateUserDetails(existingUser, user);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                // Add new user
                JSONObject userDetails = getUserDetails(user);
                userList.add(userDetails);
            }
        }

        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(userList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads existing users from the JSON file.
     *
     * @return JSONArray of existing users
     */
    private JSONArray loadExistingUsers() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(USER_FILE)) {
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }

    /**
     * Updates the details of an existing user.
     *
     * @param existingUser JSONObject of the existing user
     * @param user User object with updated details
     */
    private void updateUserDetails(JSONObject existingUser, User user) {
        existingUser.put("username", user.getUserName());
        existingUser.put("password", user.getPassword());
        existingUser.put("email", user.getEmail());

        JSONObject languageProgress = new JSONObject();
        for (LanguageProgress progress : user.getLanguageProgress()) {
            JSONObject progressDetails = new JSONObject();
            progressDetails.put("completedLessons", progress.getCompletedLessons());
            progressDetails.put("totalLessons", progress.getTotalLessons());
            progressDetails.put("progressPercentage", progress.getProgressPercentage());
            languageProgress.put(progress.getLanguage(), progressDetails);
        }
        existingUser.put("languageProgress", languageProgress);
    }

    /**
     * Converts a User object to a JSONObject.
     *
     * @param user User object to be converted
     * @return JSONObject representing the user
     */
    private JSONObject getUserDetails(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put("UUID", user.getUUID());
        userDetails.put("username", user.getUserName());
        userDetails.put("password", user.getPassword());
        userDetails.put("email", user.getEmail());

        JSONObject languageProgress = new JSONObject();
        for (LanguageProgress progress : user.getLanguageProgress()) {
            JSONObject progressDetails = new JSONObject();
            progressDetails.put("completedLessons", progress.getCompletedLessons());
            progressDetails.put("totalLessons", progress.getTotalLessons());
            progressDetails.put("progressPercentage", progress.getProgressPercentage());
            languageProgress.put(progress.getLanguage(), progressDetails);
        }
        userDetails.put("languageProgress", languageProgress);

        return userDetails;
    }
}

class LanguageProgress {
    private String language;
    private int completedLessons;
    private int totalLessons;
    private double progressPercentage;

    public String getLanguage() {
        return language;
    }

    public int getCompletedLessons() {
        return completedLessons;
    }

    public int getTotalLessons() {
        return totalLessons;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }
}

/* class User {
    private String UUID;
    private String username;
    private String password;
    private String email;
    private List<LanguageProgress> languageProgress;

    public String getUUID() {
        return UUID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<LanguageProgress> getLanguageProgress() {
        return languageProgress;
    } 
}  */
