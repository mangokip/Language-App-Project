package com.app;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class CockySpeakTest {

    @Test
    public void testLoadUsersJson() throws Exception {
       
        System.out.println("Looking for users.json in the classpath...");

        
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("user.json");

        assertNotNull(inputStream, "JSON file not found! Make sure it is in src/test/resources/users.json.");

        JSONParser parser = new JSONParser();
        JSONArray users = (JSONArray) parser.parse(new InputStreamReader(inputStream));

       
        assertEquals(12, users.size(), "There should be 12 users in the JSON file.");

       
        JSONObject firstUser = (JSONObject) users.get(0);
        assertEquals("JaneDoe", firstUser.get("username"));
        assertEquals("john.doe@example.com", firstUser.get("email"));

      
        JSONObject userWithProgress = (JSONObject) users.get(11);
        JSONObject progressTrackers = (JSONObject) userWithProgress.get("progressTrackers");
        JSONObject spanishProgress = (JSONObject) progressTrackers.get("Spanish");

        
        assertNotNull(spanishProgress, "Progress tracker for Spanish should not be null.");
        assertEquals(0L, spanishProgress.get("questionsCompleted"));
        assertEquals("BEGINNER", spanishProgress.get("currentState"));
        assertEquals(10L, spanishProgress.get("totalLessons"));
    }
}
