package com.app;


import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ProgressTrackerTest {
    private ProgressTracker tracker;
    private State mockBeginnerState;

    @Before
    public void setUp() {
        // Initialize a new tracker before each test
        tracker = new ProgressTracker();
        // mockBeginnerState = new State() {}; // Creating an anonymous implementation of State interface
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, tracker.getQuestionsCompleted());
        assertEquals(0, tracker.getLessonsCompleted());
        //assertEquals(0, tracker.getXP());
        //assertEquals(0, tracker.getStreak());
        assertEquals(0, tracker.getCompletedLessons());
        assertNull(tracker.getState());
    }

    @Test
    public void testParameterizedConstructor() {
        ProgressTracker customTracker = new ProgressTracker(
            5, // questionsCompleted
            3, // lessonsCompleted
            100, // xp
            2, // streak
            3, // completedLessons
            10, // totalLessons
            30, // progressPercentage
            mockBeginnerState // currentState
        );

        assertEquals(5, customTracker.getQuestionsCompleted());
        assertEquals(3, customTracker.getLessonsCompleted());
        assertEquals(100, customTracker.getXP());
        assertEquals(2, customTracker.getStreak());
        assertEquals(3, customTracker.getCompletedLessons());
        assertEquals(10, customTracker.getTotalLessons());
        assertEquals(30, customTracker.getProgressPercentage());
        assertEquals(mockBeginnerState, customTracker.getState());
    }

    @Test
    public void testUpdateProgress() {
        tracker.updateProgress(
            10, // questionsCompleted
            5, // lessonsCompleted
            200, // xp
            3, // streak
            5, // completedLessons
            15, // totalLessons
            33, // progressPercentage
            mockBeginnerState // currentState
        );

        assertEquals(10, tracker.getQuestionsCompleted());
        assertEquals(5, tracker.getLessonsCompleted());
        assertEquals(200, tracker.getXP());
        assertEquals(3, tracker.getStreak());
        assertEquals(5, tracker.getCompletedLessons());
        assertEquals(15, tracker.getTotalLessons());
        assertEquals(33, tracker.getProgressPercentage());
        assertEquals(mockBeginnerState, tracker.getState());
    }

    

    

    @Test
    public void testCompleteLesson() {
        // Set initial total lessons
        tracker.updateProgress(0, 0, 0, 0, 0, 10, 0, null);
        
        // Complete one lesson
        tracker.completeLesson();
        assertEquals(1, tracker.getCompletedLessons());
        assertEquals(1, tracker.getLessonsCompleted());
        assertEquals(10, tracker.getProgressPercentage());
        
        // Complete another lesson
        tracker.completeLesson();
        assertEquals(2, tracker.getCompletedLessons());
        assertEquals(2, tracker.getLessonsCompleted());
        assertEquals(20, tracker.getProgressPercentage());
    }

    @Test
    public void testDisplayProgress() {
        tracker.updateProgress(5, 3, 100, 2, 3, 10, 30, mockBeginnerState);
        String progress = tracker.displayProgress();
        
        assertTrue(progress.contains("Questions Completed: 5"));
        assertTrue(progress.contains("Lessons Completed: 3"));
        assertTrue(progress.contains("Total XP: 100"));
        assertTrue(progress.contains("Current Streak: 2"));
        assertTrue(progress.contains("Completed Lessons: 3"));
        assertTrue(progress.contains("Total Lessons: 10"));
        assertTrue(progress.contains("Progress Percentage: 30%"));
    }

    @Test
    public void testSaveProgressToFile() throws Exception {
        tracker.updateProgress(5, 3, 100, 2, 3, 10, 30, mockBeginnerState);
        String filename = "test_progress.txt";
        
        // Save progress to file
        tracker.saveProgressToFile(filename);
        
        // Read the file and verify contents
        File file = new File(filename);
        assertTrue(file.exists());
        
        List<String> lines = Files.readAllLines(Path.of(filename));
        assertTrue(lines.contains("Questions Completed: 5"));
        assertTrue(lines.contains("Lessons Completed: 3"));
        assertTrue(lines.contains("Total XP: 100"));
        assertTrue(lines.contains("Current Streak: 2"));
        
        // Clean up
        file.delete();
    }

}