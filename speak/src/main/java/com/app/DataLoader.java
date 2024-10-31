package com.app;

import java.io.BufferedReader;
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

public class DataLoader extends DataConstants {

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        
        try (InputStream inputStream = DataLoader.class.getResourceAsStream(USER_FILE);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            JSONArray usersJSON = (JSONArray) new JSONParser().parse(content.toString());

            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;

                String username = (String) userJSON.get(USER_NAME);
                String password = (String) userJSON.get(PASSWORD);
                String email = (String) userJSON.get(EMAIL);

                if (username != null && password != null && email != null) {
                    users.add(new User(username, password, email));
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static ArrayList<User> loadUsers(String filePath) {
        ArrayList<User> users = new ArrayList<>();
        
        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
    
            StringBuilder content = new StringBuilder();
            String line;
    
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
    
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(content.toString());
    
            for (Object obj : usersJSON) {
                JSONObject userJSON = (JSONObject) obj;
    
                String username = (String) userJSON.get(USER_NAME);
                String password = (String) userJSON.get(PASSWORD);
                String email = (String) userJSON.get(EMAIL);
    
                if (username != null && password != null && email != null) {
                    users.add(new User(username, password, email));
                }
            }
    
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    
        return users;
    }
    

    public static List<Word> loadWordsToList() {
        List<Word> words = new ArrayList<>();
        
        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/words.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

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

    public static Map<String, List<Word>> loadWords() {
        Map<String, List<Word>> languageWords = new HashMap<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/words.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

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

    public static Map<String, List<Word>> loadWords(String filePath) {
        Map<String, List<Word>> languageWords = new HashMap<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

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

    public static List<Phrase> loadPhrases() {
        List<Phrase> phrases = new ArrayList<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/phrases.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

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
            System.err.println("Error loading phrases: " + e.getMessage());
        }

        return phrases;
    }

    public static List<Flashcard> loadPhraseCards() {
        List<Flashcard> flashcards = new ArrayList<>();

        try (InputStream inputStream = DataLoader.class.getResourceAsStream("/phrases.json");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

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
            System.err.println("Error loading phrases: " + e.getMessage());
        }

        return flashcards;
    }
    
}
