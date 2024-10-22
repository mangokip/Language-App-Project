package com.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The DataLoader class is responsible for reading lesson data from a JSON file.
 * This version is a simple test that reads the file's content and prints it,
 * but it does not yet parse the JSON into objects.
 */
public class DataLoader extends DataConstants {

    /**
     * Loads lessons from a JSON file and returns them as a list of Lesson
     * objects. This version simply reads the file and returns an empty list,
     * without parsing the content.
     *
     * @return a List of Lesson objects (currently empty)
     */
    public List<Lesson> loadLessons() {
        List<Lesson> lessons = new ArrayList<>();
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(LESSON_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            System.out.println("JSON file content:");
            System.out.println(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lessons;
    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            JSONArray usersJSON = (JSONArray) new JSONParser().parse(content.toString());

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);

                String username = (String) userJSON.get(USER_NAME);
                String password = (String) userJSON.get(PASSWORD);
                String email = (String) userJSON.get(EMAIL);

                if (username == null || password == null || email == null) {
                    System.err.println("User data missing or invalid at index " + i);
                    continue;
                }

                User user = new User(username, password, email);
                users.add(user);
                System.out.println("Successfully loaded user: " + user.getUserName());
            }

            System.out.println("Total users loaded: " + users.size());
            return users;
        } catch (IOException | ParseException e) {
            System.err.println("Error loading users: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Loads words from a JSON file and returns them as a list of Word objects.
     *
     * @return a List of Word objects
     */
    public static ArrayList<Word> loadWords() {
        ArrayList<Word> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(WORD_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            JSONArray wordsJSON = (JSONArray) new JSONParser().parse(content.toString());

            for (int i = 0; i < wordsJSON.size(); i++) {
                JSONObject wordJSON = (JSONObject) wordsJSON.get(i);

                String text = (String) wordJSON.get(TEXT);
                String foreign = (String) wordJSON.get(FOREIGN);
                String pronounce = (String) wordJSON.get(PRONOUNCE);
                String genreString = (String) wordJSON.get(GENRE);

                if (text == null || foreign == null || pronounce == null || genreString == null) {
                    System.err.println("Word data missing or invalid at index " + i);
                    continue;
                }

                Genre genre;
                try {
                    genre = Genre.valueOf(genreString.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid genre at index " + i + ": " + genreString);
                    continue;
                }

                Word word = new Word(text, foreign, pronounce, genre);
                words.add(word);
                System.out.println("Successfully loaded word: " + word.getText());
            }

            System.out.println("Total words loaded: " + words.size());
            return words;
        } catch (IOException | ParseException e) {
            System.err.println("Error loading words: " + e.getMessage());
            e.printStackTrace();
        }

        return words;
    }

    /**
     * Main method to test the DataLoader class. Loads lessons from the JSON
     * file and prints the file's content to the console. If no lessons are
     * loaded or the file is not found, it prints an appropriate message.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadLessons();
        loadUsers();
        loadWords();
    }
}