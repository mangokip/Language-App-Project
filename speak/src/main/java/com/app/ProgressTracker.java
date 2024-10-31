package com.app;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The ProgressTracker class tracks the user's progress through lessons and questions.
 * It maintains various fields like questions completed, lessons completed, experience points (XP), streak,
 * completed lessons, total lessons, progress percentage, and the current state. The class also offers methods
 * to update and save progress to a file, as well as manage different states (beginner, intermediate, expert).
 */
public class ProgressTracker {

    private int questionsCompleted; // Total questions completed by the user
    private int lessonsCompleted; // Total lessons completed by the user
    private int xp; // Total XP earned by the user
    private int streak; // Current streak of consecutive progress
    private int completedLessons; // Lessons completed in the current session
    private int totalLessons; // Total number of lessons available
    private int progressPercentage; // Percentage of total lessons completed
    private State currentState; // Current state of user (e.g., beginner, intermediate, expert)
    private State beginnerState; // Beginner state instance
    private State intermediateState; // Intermediate state instance
    private State expertState; // Expert state instance

    /**
     * Basic constructor that initializes the tracker with default values.
     */
    public ProgressTracker() {
        questionsCompleted = 0;
        lessonsCompleted = 0;
        xp = 0;
        streak = 0;
        currentState = null;
        beginnerState = null;
        intermediateState = null;
        expertState = null;
    }

    /**
     * Constructor with parameters to set the initial values of the tracker.
     * 
     * @param questionsCompleted Number of questions completed
     * @param lessonsCompleted Number of lessons completed
     * @param xp Total experience points
     * @param streak Current streak count
     * @param completedLessons Completed lessons in the current session
     * @param totalLessons Total lessons available
     * @param progressPercentage Completion percentage of total lessons
     * @param currentState Current state of the user
     */
    public ProgressTracker(int questionsCompleted, int lessonsCompleted, int xp, int streak, int completedLessons, int totalLessons, int progressPercentage, State currentState) {
        this(); // calls default constructor to intialize states 
        this.questionsCompleted = questionsCompleted;
        this.lessonsCompleted = lessonsCompleted;
        this.xp = xp;
        this.streak = streak;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        this.progressPercentage = progressPercentage;
        this.currentState = currentState;
    }

    /**
     * Updates the progress tracker with new values.
     * 
     * @param questionsCompleted Number of questions completed
     * @param lessonsCompleted Number of lessons completed
     * @param xp Total experience points
     * @param streak Current streak count
     * @param completedLessons Completed lessons in the current session
     * @param totalLessons Total lessons available
     * @param progressPercentage Completion percentage of total lessons
     * @param currentState Current state of the user
     */
    public void updateProgress(int questionsCompleted, int lessonsCompleted, int xp, int streak, int completedLessons, int totalLessons, int progressPercentage, State currentState) {
        this.questionsCompleted = questionsCompleted;
        this.lessonsCompleted = lessonsCompleted;
        this.xp = xp;
        this.streak = streak;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        this.progressPercentage = progressPercentage;
        this.currentState = currentState;
    }

    /**
     * Displays the current progress as a formatted string.
     * 
     * @return A string representation of the user's progress.
     */
    public String displayProgress() {
        return "Questions Completed: " + this.questionsCompleted + 
               "\nLessons Completed: " + this.lessonsCompleted + 
               "\nTotal XP: " + this.xp + 
               "\nCurrent Streak: " + this.streak + 
               "\nCompleted Lessons: " + this.completedLessons +
               "\nTotal Lessons: " + this.totalLessons +
               "\nProgress Percentage: " + this.progressPercentage + "%" +
               "\nCurrent Level: " + this.currentState;
    }

    // Getters for each field, returning the current values.

    public int getQuestionsCompleted() {
        return this.questionsCompleted;
    }

    public int getLessonsCompleted() {
        return this.lessonsCompleted;
    }

    public int getXP() {
        return this.xp;
    }

    public int getStreak() {
        return this.streak;
    }

    public int getCompletedLessons() {
        return this.completedLessons;
    }

    public int getTotalLessons() {
        return this.totalLessons;
    }

    public int getProgressPercentage() {
        return this.progressPercentage;
    }

    public State getState() {
        return this.currentState;
    }

    /**
     * Sets the current state of the user.
     * 
     * @param state New state to be set.
     */
    public void setState(State state) {
        this.currentState = state;
    }

    /**
     * Increases the user's XP by a specified amount.
     * 
     * @param xp Amount of XP to add.
     */
    public void increaseXP(int xp) {
        this.xp += xp;
    }

    /**
     * Increases the user's streak by one.
     */
    public void increaseStreak() {
        this.streak++;
    }

    /**
     * Changes the user's state to a specified state.
     * 
     * @param state The state to change to (beginner, intermediate, expert).
     */
    public void changeState(State state) {
        if(state == this.beginnerState) {
            this.currentState = beginnerState;
        } else if(state == this.intermediateState) {
            this.currentState = intermediateState;
        } else if(state == this.expertState) {
            this.currentState = expertState;
        } else {
            System.out.println("Incorrect State.");
        }
    }

    /**
     * Saves the current progress details to a specified file.
     * 
     * @param filename Name of the file where progress is saved.
     */
    public void saveProgressToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Questions Completed: " + this.questionsCompleted + "\n");
            writer.write("Lessons Completed: " + this.lessonsCompleted + "\n");
            writer.write("Total XP: " + this.xp + "\n");
            writer.write("Current Streak: " + this.streak + "\n");
            writer.write("Completed Lessons: " + this.completedLessons + "\n");
            writer.write("Total Lessons: " + this.totalLessons + "\n");
            writer.write("Progress Percentage: " + this.progressPercentage + "%\n");
            writer.write("Current Level: " + (this.currentState != null ? this.currentState : "Unknown") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a lesson as completed, updating related fields and optionally
     * calculating progress percentage.
     */
    public void completeLesson() {
        this.completedLessons++;  // Increment the completed lessons
        this.lessonsCompleted++;  // Increment the total lessons completed count
        
        if (totalLessons > 0) {
            this.progressPercentage = (completedLessons * 100) / totalLessons;
        }
    
        System.out.println("Lesson completed! Total completed lessons: " + this.completedLessons);
    }
    
}
