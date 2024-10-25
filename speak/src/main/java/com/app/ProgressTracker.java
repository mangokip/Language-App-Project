package com.app;

import java.io.FileWriter;
import java.io.IOException;

public class ProgressTracker {

    private int questionsCompleted;
    private int lessonsCompleted;
    private int xp;
    private int streak;
    private int completedLessons;
    private int totalLessons;
    private int progressPercentage;
    private State currentState;
    private State beginnerState;
    private State intermediateState;
    private State expertState;


    /**
     * Basic constructor
     */
    public ProgressTracker() {
        // questionsCompleted = 0;
        // lessonsCompleted = 0;
        // xp = 0;
        // streak = 0;
        // currentState = null;
        // //Edit later
        // beginnerState = null;
        // intermediateState = null;
        // expertState = null;

        this.questionsCompleted = 0;
        this.lessonsCompleted = 0;
        this.xp = 0;
        this.streak = 0;
        this.completedLessons = 0; // how many question they have done and how much they have gotten right 
        this.totalLessons = 30; //this changes depending on how many lessons we have 
        this.progressPercentage = 0;

        //I am intilizing the states here
        this.beginnerState = new BeginnerState();
        this.intermediateState = new IntermediateState();
        this.expertState = new ExpertState();

        this.currentState = beginnerState; //Sets intial state 
    }

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
        updateProgressPercentage();
        // Edit later
    }

    private void updateProgressPercentage() {
        this.progressPercentage = (this.completedLessons / this.totalLessons) * 100;
    }


    /*test line */
    public void updateProgress(int questionsCompleted, int lessonsCompleted, int xp, int streak, int completedLessons, int totalLessons, int progressPercentage, State currentState) {
        this.questionsCompleted = questionsCompleted;
        this.lessonsCompleted = lessonsCompleted;
        this.xp = xp;
        this.streak = streak;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        this.progressPercentage = progressPercentage;
        updateProgressPercentage();
        this.currentState = currentState;
        currentState.handleProgress(this); 

        //For David or whoever is working in state. 
        // This line calls handleProgress() on whatever state object is currently assigned
        // Example: if currentState is BeginnerState, it calls BeginnerState.handleProgress()
        // 'this' is the ProgressTracker instance, passed so the state can access user's stats
        //currentState.handleProgress(this);
        
        /* What really happens:
         * 1. currentState points to one of our state objects (e.g., BeginnerState)
         * 2. handleProgress() is called on that state object
         * 3. The state object can access user's XP via the tracker parameter
         * 4. If XP threshold is met, state object calls setState() to change the state
         */
    }

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

    public State getBeginnerState() {
        return beginnerState;
    }

    public State getIntermediateState() {
        return intermediateState;
    }

    public State getExpertState() {
        return expertState;
    }

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
    public void setState(State state) {
        this.currentState = state;
    }

    public State getCurrentState(){
        return this.currentState;
    }
    
    public void increaseStreak() {
        this.streak++;
    }

    public void setStreak(int streak){
        this.streak = streak;
    }

    public void increaseXP(int amount) {
        this.xp += amount;
        currentState.handleProgress(this);
    }

    public void completeLesson() {
        this.completedLessons++;
        updateProgressPercentage();
        increaseXP(50); // Award XP for completing a lesson
    }

    public void completeQuestion() {
        this.questionsCompleted++;
        increaseXP(10); // Award XP for completing a question
    }


    /**
     * Converts the ProgressTracker's current state into a formatted string representation.
     * This method creates a human-readable summary of all progress metrics.
     * 
     * Format specifiers used:
     * %d - for integer values (questions, lessons, XP, etc.)
     * %s - for string values (state name)
     * %n - for platform-independent newline
     * %% - to print a literal % symbol
     * 
     * @return A formatted string containing:
     *         - Number of questions completed
     *         - Number of lessons completed
     *         - Total XP earned
     *         - Current streak
     *         - Progress ratio (completed/total lessons)
     *         - Progress percentage
     *         - Current level (state name)
     */

     //This method I am not to sure about so it can be change if needed be. 

    public String toString(){
        return String.format(
            "Progress Status:%n" +
            "Questions Completed: %d%n" +
            "Lessons Completed: %d%n" +
            "Total XP: %d%n" +
            "Current Streak: %d%n" +
            "Progress: %d/%d lessons (%d%%)%n" +
            "Current Level: %s",
            questionsCompleted, lessonsCompleted, xp, streak,
            completedLessons, totalLessons, progressPercentage,
            currentState.getStateName() //red becuase it is suppoed to call it from the state 
            //example public String getStateName() {
        //return "Expert";
        //}
        //);
    }

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

    
    }


