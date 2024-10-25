package com.app;

import java.util.ArrayList;

/**
 * Represents a lesson in the language learning application.
 * A lesson contains a list of questions, a title, and a status indicating whether the lesson is active.
 */
public class Lesson {
    private ArrayList<Question> questions;
    private Language language;
    private String topic;
    private int numQuestionsComplete;
    private boolean lessonStatus;
    public static final int diff = 1;

    // public Lesson() {
    //     this.topic = "";
    //     this.questions = new ArrayList<Question>();
    //     questions.add(MultipleChoice(), MultipleChoice(), MultipleChoice(), FillBlank(), VocabularyMatching());
    //     this.lessonStatus = false;
    // }

    public Lesson(String topic, Language language){
        this.topic = topic;
        this.language = language;
        this.questions = new ArrayList<Question>();
        WordList wordList = WordList.getInstance();
        questions.add(new MultipleChoice(diff, wordList.getRandomWord(language),language),new MultipleChoice(diff, wordList.getRandomWord(language),language),new MultipleChoice(diff, wordList.getRandomWord(language),language), new FillBlank(diff, Phrase sentence ), new VocabularyMatching(language, diff, wordList.getRandomWord(language)));
        this.lessonStatus = false;

    }

    public void setLessonStatus (Boolean status){
        this.lessonStatus = status;
    }

    /**
     * Displays the title and all questions in the lesson.
     */
    public void displayLesson() {
        System.out.println("Lesson Title: " + topic);
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
        System.out.println("Stopping the lesson: " + topic);
        this.lessonStatus = false;
    }

    /**
     * Returns the title of the lesson.
     * 
     * @return The title of the lesson.
     */
    public String getTitle() {
        return topic;
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
