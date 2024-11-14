package com.app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The DataWriter class is responsible for saving data related to users in the
 * CockySpeak application. It provides methods to save users to a data source.
 */
public class DataWriter extends DataConstants {

    /**
     * Saves the list of users to the data source.
     *
     * @param users the ArrayList of User objects to be saved
     */
    public void saveUsers(ArrayList<User> users) {
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < users.size(); i++) {
            jsonUsers.add(getUserDetails(users.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers(ArrayList<User> users, String filePath) {
        JSONArray jsonUsers = new JSONArray();
        
        // Add user details if any exist in the list
        if (users != null) {
            for (User user : users) {
                jsonUsers.add(getUserDetails(user));
            }
        }
    
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonUsers.toJSONString()); // Writes [] if jsonUsers is empty
            file.flush();
        } catch (Exception e) {
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
    protected void updateUserDetails(JSONObject existingUser, User user) {
        
        JSONObject updatedUserDetails = getUserDetails(user);

        
        for (Object key : updatedUserDetails.keySet()) {
            existingUser.put(key, updatedUserDetails.get(key));
        }
    }

    /**
     * Converts a User object to a JSONObject.
     *
     * @param user User object to be converted
     * @return JSONObject representing the user
     */
    protected static JSONObject getUserDetails(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_ID, user.getUUID().toString());
        userDetails.put(USER_NAME, user.getUserName());
        userDetails.put(PASSWORD, user.getPassword());
        userDetails.put(EMAIL, user.getEmail());

        JSONObject languageProgress = new JSONObject();
        for (Map.Entry<Language, ProgressTracker> entry : user.getProgressTracker().entrySet()) {
            Language language = entry.getKey();
            ProgressTracker progress = entry.getValue();

            JSONObject progressDetails = new JSONObject();
            progressDetails.put(QUESTIONS_COMPLETED, progress.getQuestionsCompleted());
            progressDetails.put(LESSONS_COMPLETED, progress.getLessonsCompleted());
            progressDetails.put(XP, progress.getXP());
            progressDetails.put(STREAK, progress.getStreak());
            progressDetails.put(COMPLETED_LESSONS, progress.getCompletedLessons());
            progressDetails.put(TOTAL_LESSONS, progress.getTotalLessons());
            progressDetails.put(PROGRESS_PERCENTAGE, progress.getProgressPercentage());
            progressDetails.put(CURRENT_STATE, progress.getState() == null ? null : progress.getState().toString());

            languageProgress.put(language.getLanguageCode(), progressDetails);
        }

        userDetails.put(PROGRESS_TRACKERS, languageProgress); // match the JSON structure

        return userDetails;
    }

    public static void clearUserFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    try (FileWriter newFile = new FileWriter(filePath)) {
        newFile.write("[]"); // Write an empty JSON array
        newFile.flush();
        System.out.println("File recreated and cleared successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    
}
