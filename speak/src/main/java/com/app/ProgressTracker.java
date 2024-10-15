package com.app;


public class ProgressTracker {

    private int questionsCompleted;
    private int lessonsCompleted;
    private int xp;
    private int streak;
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

    public ProgressTracker(int questionsCompleted, int lessonsCompleted, int xp, int streak, State currentState, State beginnerState, State intermediateState, State expertState) {
        this.questionsCompleted = questionsCompleted;
        this.lessonsCompleted = lessonsCompleted;
        this.xp = xp;
        this.streak = streak;
        this.currentState = currentState;
        //Edit later
        this.beginnerState = beginnerState;
        this.intermediateState = intermediateState;
        this.expertState = expertState;
    }

    /*test line */
    public void updateProgress(int questionsCompleted, int lessonsCompleted, int xp, int streak, State currentState, State beginnerState, State intermediateState, State expertState) {
        this.questionsCompleted = questionsCompleted;
        this.lessonsCompleted = lessonsCompleted;
        this.xp = xp;
        this.currentState = currentState;
        this.beginnerState = beginnerState;
        this.intermediateState = intermediateState;
        this.expertState = expertState;
    }

    public void displayProgress() {
        System.out.println("Questions Completed: " + this.questionsCompleted + 
                            "\nLessons Completed: " + this.lessonsCompleted + 
                            "\nTotal XP: " + this.xp + 
                            "\nCurrent Streak: " + this.streak + 
                            "\nCurrent Level: " + this.currentState);
    }

    public State getState() {
        return this.currentState;
    }

    public void increaseXP(int xp) {
        this.xp += xp;
    }

    public void increaseStreak() {
        this.streak++;
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
}
