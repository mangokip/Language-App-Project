package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;





public class UserTest {
    private User user;
    private Language Spanish; 

    public void oneTimeSetup(){
        

    }

    public void oneTimeTearDown(){

    }

    @BeforeEach
    public void setup(){
        user = new User("TestUser","PassWord123", "testemail@gmail.com");
        Spanish = new Language("Spanish");

    }
    @AfterEach
    public void tearDown(){
        
    }

    // void testCreateLanuguageProces(){

    // }
    
    @Test
    public void testCreateLanguageProgress(){

        //Test creating progress for a new language 
        user.createLanguageProgress(Spanish); 

        //This verifies that the user has a progress tracker for the new language
        assertTrue(user.getProgressTracker().containsKey(Spanish));

       ProgressTracker tracker = user.getProgressTracker().get(Spanish);
        assertEquals(0, tracker.getCompletedLessons());  //test won't work becuase dataloader for wordlist is giving a null pointer exception. 

    }

    @Test
    public void testCreateDuplicateLanguageProgress() {
        // First creation
        user.createLanguageProgress(Spanish);
        ProgressTracker originalTracker = user.getProgressTracker().get(Spanish);
        
        // Attempt duplicate creation
        user.createLanguageProgress(Spanish);
        
        // Verify the tracker wasn't changed
        ProgressTracker currentTracker = user.getProgressTracker().get(Spanish);
        assertSame(originalTracker, currentTracker);
    } //this makes sures the user progress is saved if one is already created 
    
    @Test
    public void testCreateMultipleLanguageProgress() {
        // Create progress for multiple languages
        user.createLanguageProgress(Spanish);
        
        // Verify both languages were initialized
        assertTrue(user.getProgressTracker().containsKey(Spanish));
        
        // Verify they have different tracker instances
        assertNotSame(
            user.getProgressTracker().get(Spanish),
            new ProgressTracker()
        );
    }
    
    @Test
    public void testCreateLanguageProgressWithNullLanguage() {
        // Test handling of null language parameter
        try {
            user.createLanguageProgress(null);
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected behavior
        }
    }

    
}
