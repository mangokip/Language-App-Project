package com.app;


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
        questionsCompleted = 0;
        lessonsCompleted = 0;
        xp = 0;
        streak = 0;
        currentState = null;
        //Edit later
        beginnerState = null;
        intermediateState = null;
        expertState = null;
    }

    public ProgressTracker(int questionsCompleted, int lessonsCompleted, int xp, int streak, int completedLessons, int totalLessons, int progressPercentage, State currentState) {
        this.questionsCompleted = questionsCompleted;
        this.lessonsCompleted = lessonsCompleted;
        this.xp = xp;
        this.streak = streak;
        this.completedLessons = completedLessons;
        this.totalLessons = totalLessons;
        this.progressPercentage = progressPercentage;
        this.currentState = currentState;
        //Edit later
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
        this.currentState = currentState;
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
    

    public void increaseXP(int xp) {
        this.xp += xp;
    }

    public void increaseStreak() {
        this.streak++;
    }

}
