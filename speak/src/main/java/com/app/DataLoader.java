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

    public static List<Flashcard> loadWords() {
        List<Flashcard> words = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(WORD_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;
    
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
    
            JSONObject wordsJSON = (JSONObject) new JSONParser().parse(content.toString());
            JSONArray wordsArray = (JSONArray) wordsJSON.get("Spanish");
    
            for (Object obj : wordsArray) {
                JSONObject wordJSON = (JSONObject) obj;
    
                String text = (String) wordJSON.get("text");
                String translation = (String) wordJSON.get("foreign");
                String pronunciation = (String) wordJSON.get("pronounce");
                String genre = (String) wordJSON.get("genre");
                int difficulty = ((Long) wordJSON.get("difficulty")).intValue();

    
                words.add(new Flashcard(text, translation, pronunciation, genre, difficulty));
                System.out.println("Loaded word: " + text);
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error loading words: " + e.getMessage());
        }
    
        return words;
    }
    
    public static List<Flashcard> loadPhrases() {
        List<Flashcard> phrases = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(PHRASE_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;
    
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
    
            // Parse the JSON content correctly
            JSONObject phrasesJSON = (JSONObject) new JSONParser().parse(content.toString());
            JSONArray phrasesArray = (JSONArray) phrasesJSON.get("Spanish");
    
            // Iterate over the array of phrase objects
            for (Object obj : phrasesArray) {
                JSONObject phraseJSON = (JSONObject) obj;
    
                // Extract values from the JSON object
                String text = (String) phraseJSON.get("text");
                String translation = (String) phraseJSON.get("translation");
                String pronunciation = (String) phraseJSON.get("pronounce");
    
                // Create a Flashcard for each phrase and add it to the list
                phrases.add(new Flashcard(text, translation, pronunciation));
                System.out.println("Loaded phrase: " + text);
            }
    
        } catch (IOException | ParseException e) {
            System.err.println("Error loading phrases: " + e.getMessage());
            e.printStackTrace();
        }
    
        return phrases;
    }
    
    
    
        public static List<Flashcard> loadWords() {
            List<Flashcard> words = new ArrayList<>();
    
            try (BufferedReader reader = new BufferedReader(new FileReader(WORD_FILE))) {
                StringBuilder content = new StringBuilder();
                String line;
    
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
    
                JSONObject wordsJSON = (JSONObject) new JSONParser().parse(content.toString());
                JSONArray wordsArray = (JSONArray) wordsJSON.get("Spanish");
    
                for (Object obj : wordsArray) {
                    JSONObject wordJSON = (JSONObject) obj;
    
                    String text = (String) wordJSON.get("text");
                    String translation = (String) wordJSON.get("foreign");
                    String pronunciation = (String) wordJSON.get("pronounce");
                    String genre = (String) wordJSON.get("genre");
                    int difficulty = ((Long) wordJSON.get("difficulty")).intValue();
    
                    words.add(new Flashcard(text, translation, pronunciation, genre, difficulty));
                    System.out.println("Loaded word: " + text);
                }
            } catch (IOException | ParseException e) {
                System.err.println("Error loading words: " + e.getMessage());
            }
    
            return words;
        }
    
        public static List<Flashcard> loadPhrases() {
            List<Flashcard> phrases = new ArrayList<>();
    
            try (BufferedReader reader = new BufferedReader(new FileReader(PHRASE_FILE))) {
                StringBuilder content = new StringBuilder();
                String line;
    
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
    
                JSONObject phrasesJSON = (JSONObject) new JSONParser().parse(content.toString());
                JSONArray phrasesArray = (JSONArray) phrasesJSON.get("Spanish");
    
                for (Object obj : phrasesArray) {
                    JSONObject phraseJSON = (JSONObject) obj;
    
                    String text = (String) phraseJSON.get("text");
                    String translation = (String) phraseJSON.get("translation");
                    String pronunciation = (String) phraseJSON.get("pronounce");
    
                    phrases.add(new Flashcard(text, translation, pronunciation));
                    System.out.println("Loaded phrase: " + text);
                }
            } catch (IOException | ParseException e) {
                System.err.println("Error loading phrases: " + e.getMessage());
            }
    
            return phrases;
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