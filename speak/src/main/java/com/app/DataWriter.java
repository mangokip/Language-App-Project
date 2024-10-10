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
 * The DataWriter class is responsible for saving data related to users and lessons
 * in the CockySpeak application. It provides methods to save users and lessons to a data source.
 * @author David Dinh
 */
public class DataWriter {
    private static final String USER_FILE = "user.json";
    private static final String LESSON_FILE = "lesson.json";

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
                if (existingUser.get("UUID").equals(user.getUUID()) ||
                    existingUser.get("username").equals(user.getUsername()) ||
                    existingUser.get("email").equals(user.getEmail())) {
                    // Update existing user data
                    existingUser.put("username", user.getUsername());
                    existingUser.put("password", user.getPassword());
                    existingUser.put("email", user.getEmail());

                    JSONObject languageProgress = new JSONObject();
                    user.getLanguageProgress().forEach((language, progress) -> {
                        JSONObject progressDetails = new JSONObject();
                        progressDetails.put("completedLessons", progress.getCompletedLessons());
                        progressDetails.put("totalLessons", progress.getTotalLessons());
                        progressDetails.put("progressPercentage", progress.getProgressPercentage());
                        languageProgress.put(language, progressDetails);
                    });
                    existingUser.put("languageProgress", languageProgress);

                    updated = true;
                    break;
                }
            }
            if (!updated) {
                // Add new user
                JSONObject userDetails = new JSONObject();
                userDetails.put("UUID", user.getUUID());
                userDetails.put("username", user.getUsername());
                userDetails.put("password", user.getPassword());
                userDetails.put("email", user.getEmail());

                JSONObject languageProgress = new JSONObject();
                user.getLanguageProgress().forEach((language, progress) -> {
                    JSONObject progressDetails = new JSONObject();
                    progressDetails.put("completedLessons", progress.getCompletedLessons());
                    progressDetails.put("totalLessons", progress.getTotalLessons());
                    progressDetails.put("progressPercentage", progress.getProgressPercentage());
                    languageProgress.put(language, progressDetails);
                });
                userDetails.put("languageProgress", languageProgress);

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
     * @author David Dinh
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
     * Checks if a user already exists in the list.
     *
     * @author David Dinh
     * @param userList JSONArray of existing users
     * @param user User object to check
     * @return true if the user exists, false otherwise
     */
    private boolean userExists(JSONArray userList, User user) {
        for (Object obj : userList) {
            JSONObject existingUser = (JSONObject) obj;
            if (existingUser.get("UUID").equals(user.getUUID()) ||
                existingUser.get("username").equals(user.getUsername()) ||
                existingUser.get("email").equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the list of lessons to the data source.
     *
     * @param lessons the ArrayList of Lesson objects to be saved
     */
    @SuppressWarnings("unchecked")
    public void saveLessons(ArrayList<Lesson> lessons) {
        JSONArray lessonList = new JSONArray();

        for (Lesson lesson : lessons) {
            JSONObject lessonDetails = new JSONObject();
            lessonDetails.put("topic", lesson.getTopic());

            JSONArray questionsArray = new JSONArray();
            for (Question question : lesson.getQuestions()) {
                JSONObject questionDetails = new JSONObject();
                questionDetails.put("question", question.getQuestionText());
                questionDetails.put("answer", question.getAnswer());
                questionDetails.put("answerOptions", question.getAnswerOptions());
                questionDetails.put("difficulty", question.getDifficulty());
                questionDetails.put("category", question.getCategory());
                questionDetails.put("counter", question.getCounter());
                questionsArray.add(questionDetails);
            }
            lessonDetails.put("questions", questionsArray);
            lessonDetails.put("lessonStatus", lesson.isLessonStatus());

            lessonList.add(lessonDetails);
        }

        try (FileWriter file = new FileWriter(LESSON_FILE)) {
            file.write(lessonList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}