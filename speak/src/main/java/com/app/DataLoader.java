package com.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * Loads users from file
     *
     * @return ArrayList<User> of users
     */
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            System.out.println("File content loaded: " + content.toString());  // Confirm file content here
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(content.toString());
            System.out.println("Parsed JSON array: " + usersJSON.toString());  // Confirm JSON parsing here

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
     * TESTER
     *
     */
    public static ArrayList<User> loadUsersFromResource(String filePath) {
        ArrayList<User> users = new ArrayList<>();
        System.out.println("Attempting to load users from resource: " + filePath);
        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(inputStreamReader)) {
            if (inputStream == null) {
                System.err.println("Resource file not found: " + filePath);
                return users;
            }
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            System.out.println("File content loaded: " + content.toString());  // Confirm file content here
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(content.toString());
            System.out.println("Parsed JSON array: " + usersJSON.toString());  // Confirm JSON parsing here
            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;
                String username = (String) userJSON.get(USER_NAME);
                String password = (String) userJSON.get(PASSWORD);
                String email = (String) userJSON.get(EMAIL);
                users.add(new User(username, password, email));
                System.out.println("Successfully loaded user: " + username);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Total users loaded in method: " + users.size());  // Verify loaded users count
        return users;
    }

    /**
     * Loads words from the JSON file and returns them as a list of Word
     * objects.
     *
     * @return List of Word objects
     */
    public static List<Word> loadWordsToList() {
        List<Word> words = new ArrayList<>();
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
                String pronounce = (String) wordJSON.get("pronounce");
                String genreStr = (String) wordJSON.get("genre");
                Long difficultyLong = (Long) wordJSON.get("difficulty");

                if (text != null && translation != null && pronounce != null && genreStr != null && difficultyLong != null) {
                    int difficulty = difficultyLong.intValue();

                    try {
                        Genre tempGenre = Genre.valueOf(genreStr.toUpperCase());
                        words.add(new Word(text, translation, pronounce, tempGenre, difficulty));
                        System.out.println("Loaded word: " + text);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid genre '" + genreStr + "' for word: " + text);
                    }
                } else {
                    System.out.println("Error: One or more fields are null for word entry.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    public static List<Word> loadWordsToListFromResource(String filePath) {
        List<Word> words = new ArrayList<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(inputStreamReader)) {

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
                String pronounce = (String) wordJSON.get("pronounce");
                String genreStr = (String) wordJSON.get("genre");
                Long difficultyLong = (Long) wordJSON.get("difficulty");

                if (text != null && translation != null && pronounce != null && genreStr != null && difficultyLong != null) {
                    int difficulty = difficultyLong.intValue();

                    try {
                        Genre tempGenre = Genre.valueOf(genreStr.toUpperCase());
                        words.add(new Word(text, translation, pronounce, tempGenre, difficulty));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Invalid genre '" + genreStr + "' for word: " + text);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    }

    /**
     * Loads words from the JSON file and returns them as a map of language code
     * to list of Word objects.
     *
     * @return Map of language code to list of Word objects
     */
    public static Map<String, List<Word>> loadWords() {
        Map<String, List<Word>> languageWords = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(WORD_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            JSONObject wordsJSON = (JSONObject) new JSONParser().parse(content.toString());

            for (Object key : wordsJSON.keySet()) {
                String languageCode = (String) key;
                JSONArray wordsArray = (JSONArray) wordsJSON.get(languageCode);

                List<Word> wordList = new ArrayList<>();
                for (Object obj : wordsArray) {
                    JSONObject wordJSON = (JSONObject) obj;

                    String text = (String) wordJSON.get("text");
                    String translation = (String) wordJSON.get("foreign");
                    String pronunciation = (String) wordJSON.get("pronounce");
                    String genre = (String) wordJSON.get("genre");
                    int difficulty = ((Long) wordJSON.get("difficulty")).intValue();

                    wordList.add(new Word(text, translation, pronunciation, Genre.valueOf(genre), difficulty));
                }

                languageWords.put(languageCode, wordList);
            }

        } catch (IOException | ParseException e) {
            System.err.println("Error loading words: " + e.getMessage());
        }

        return languageWords;
    }

    public static Map<String, List<Word>> loadWordsFromResource(String filePath) {
        Map<String, List<Word>> languageWords = new HashMap<>();
        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(inputStreamReader)) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            JSONObject wordsJSON = (JSONObject) new JSONParser().parse(content.toString());
            for (Object key : wordsJSON.keySet()) {
                String languageCode = (String) key;
                JSONArray wordsArray = (JSONArray) wordsJSON.get(languageCode);
                List<Word> wordList = new ArrayList<>();
                for (Object obj : wordsArray) {
                    JSONObject wordJSON = (JSONObject) obj;
                    String text = (String) wordJSON.get("text");
                    String translation = (String) wordJSON.get("foreign");
                    String pronunciation = (String) wordJSON.get("pronounce");
                    String genre = (String) wordJSON.get("genre");
                    int difficulty = ((Long) wordJSON.get("difficulty")).intValue();
                    wordList.add(new Word(text, translation, pronunciation, Genre.valueOf(genre), difficulty));
                }
                languageWords.put(languageCode, wordList);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return languageWords;
    }

    /**
     * Loads phrases from the JSON file and returns them as a list of Phrase
     * objects.
     *
     * @return List of Phrase objects
     */
    public static List<Phrase> loadPhrases() {
        List<Phrase> phrases = new ArrayList<>();

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
                String pronounce = (String) phraseJSON.get("pronounce");

                if (text != null && translation != null && pronounce != null) {
                    phrases.add(new Phrase(text, translation, pronounce));
                    System.out.println("Loaded phrase: " + text);
                } else {
                    System.out.println("Error: One or more fields are null for phrase entry.");
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error loading phrases: " + e.getMessage());
        }

        return phrases;
    }

    public static List<Phrase> loadPhrasesFromResource(String filePath) {
        List<Phrase> phrases = new ArrayList<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(inputStreamReader)) {

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
                String pronounce = (String) phraseJSON.get("pronounce");

                if (text != null && translation != null && pronounce != null) {
                    phrases.add(new Phrase(text, translation, pronounce));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return phrases;
    }

    /**
     * Loads phrases from the JSON file and returns them as a list of Flashcard
     * objects.
     *
     * @return List of Flashcard objects
     */
    public static List<Flashcard> loadPhraseCards() {
        List<Flashcard> flashcards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PHRASE_FILE))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            JSONObject phrasesJSON = (JSONObject) new JSONParser().parse(content.toString());
            JSONArray phrasesArray = (JSONArray) phrasesJSON.get("Spanish");

            if (phrasesArray != null) {
                for (Object obj : phrasesArray) {
                    JSONObject phraseObject = (JSONObject) obj;

                    String text = (String) phraseObject.get("text");
                    String translation = (String) phraseObject.get("translation");
                    String pronunciation = (String) phraseObject.get("pronounce");

                    Flashcard phraseFlashcard = new Flashcard(text, translation, pronunciation);
                    flashcards.add(phraseFlashcard);
                }
            } else {
                System.err.println("No phrases found under 'Spanish' key in the JSON file.");
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error loading phrases: " + e.getMessage());
        }

        return flashcards;
    }

    public static List<Flashcard> loadPhraseCardsFromResource(String filePath) {
        List<Flashcard> flashcards = new ArrayList<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath); InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader reader = new BufferedReader(inputStreamReader)) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            JSONObject phrasesJSON = (JSONObject) new JSONParser().parse(content.toString());
            JSONArray phrasesArray = (JSONArray) phrasesJSON.get("Spanish");

            if (phrasesArray != null) {
                for (Object obj : phrasesArray) {
                    JSONObject phraseObject = (JSONObject) obj;

                    String text = (String) phraseObject.get("text");
                    String translation = (String) phraseObject.get("translation");
                    String pronunciation = (String) phraseObject.get("pronounce");

                    Flashcard phraseFlashcard = new Flashcard(text, translation, pronunciation);
                    flashcards.add(phraseFlashcard);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return flashcards;
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

    }
}
