package com.app;

import java.util.ArrayList;

/**
 * Represents a lesson in the language learning application.
 * A lesson contains a list of questions, a title, and a status indicating whether the lesson is active.
 */
public class Lesson {
    private ArrayList<Question> questions;
    private String title;
    private boolean lessonStatus;

<<<<<<< HEAD
    /**
     * Constructs a Lesson with a given title.
     * The lesson is inactive by default.
     * 
     * @param title The title of the lesson.
     */
    public Lesson(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.lessonStatus = false; // Default status is inactive
    }

    /**
     * Adds a question to the lesson.
     * 
     * @param question The question to add to the lesson.
     */
    public void addQuestion(Question question) {
        questions.add(question);
=======
    public Lesson() {
        this.topic = "";
        this.questions = new ArrayList<Question>();
        this.lessonStatus = false;
    }
    public Lesson(String topic, ArrayList<Question> questions){
        this.topic = topic;
        this.questions = questions;
        this.lessonStatus = false;

    }

    public void setLessonStatus (Boolean status){
        
>>>>>>> 5a16735b4a827493d6d1928ce248ded47c5e6a18
    }

    /**
     * Displays the title and all questions in the lesson.
     */
    public void displayLesson() {
        System.out.println("Lesson Title: " + title);
        for (Question question : questions) {
            System.out.println(question);
        }
    }

    /**
     * Sets the status of the lesson.
     * 
     * @param status The new status of the lesson (true for active, false for inactive).
     */
    public void setLessonStatus(boolean status) {
        //TODO implement better??
        this.lessonStatus = status;
    }

    /**
     * Returns the current status of the lesson.
     * 
     * @return True if the lesson is active, false otherwise.
     */
    public boolean isLessonStatus() {
        return lessonStatus;
    }

    /**
     * Stops the lesson by setting its status to inactive.
     */
    public void stopLesson() {
        System.out.println("Stopping the lesson: " + title);
        this.lessonStatus = false;
    }

    /**
     * Returns the title of the lesson.
     * 
     * @return The title of the lesson.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the list of questions in the lesson.
     * 
     * @return An ArrayList of questions in the lesson.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
