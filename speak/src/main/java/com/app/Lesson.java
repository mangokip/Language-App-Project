package com.app;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Array;

/**
 * Represents a lesson in the language learning application.
 * A lesson contains a list of questions, a title, and a status indicating whether the lesson is active.
 */
public class Lesson {
    private ArrayList<Question> questions;
    private Language language;
    private String topic;
    private int numQuestionsCorrect;
    private double percentCorrect;
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
        numQuestionsCorrect = 0;
        WordList wordList = WordList.getInstance();
        questions.add(new MultipleChoice(diff, wordList.getRandomWord(language), language));
        questions.add(new MultipleChoice(diff, wordList.getRandomWord(language), language));
        questions.add(new MultipleChoice(diff, wordList.getRandomWord(language), language));
        questions.add(new VocabularyMatching(language, diff, wordList.getRandomWord(language)));
        this.lessonStatus = false;

    }

    public void playLesson() {
        System.out.println(topic);
        for (Question q : questions) {
            if (q instanceof MultipleChoice) {
                playMultipleChoice((MultipleChoice) q);
            // } else if (q instanceof FillBlank) { //Replace FillBlank with new question type
            //     playFillBlank((FillBlank) q);
            } else if (q instanceof VocabularyMatching) {
                playVocabularyMatching((VocabularyMatching) q);
            }
        }
    }

    public void playMultipleChoice(MultipleChoice q) {
        Scanner keyboard = new Scanner(System.in);
        String promp = q.getPrompt();
        System.out.println(q.getPrompt() + q.getCorrectAnswer().getText());
        ArrayList<Word> wordsForQuestion = q.getWordsForQuestion();
        for(int i = 0; i < wordsForQuestion.size(); i++){
            System.out.println(i + 1 + ". " + wordsForQuestion.get(i).getForeign());
        }
        int userAnswer = keyboard.nextInt();
        if(wordsForQuestion.get(userAnswer - 1).equals(q.getCorrectAnswer())){
            System.out.println("Correct!");
            numQuestionsCorrect++;
        }else{
            System.out.println("Incorrect. The correct answer is: " + q.getCorrectAnswer().getForeign());
        }
    }

        public void playVocabularyMatching(VocabularyMatching q) {
            ArrayList<String> foreignWords = new ArrayList<>(q.getWordPairs().keySet());
            ArrayList<String> englishWords = new ArrayList<>(q.getWordPairs().values());
            Collections.shuffle(foreignWords);
            Collections.shuffle(englishWords);
            Scanner keyboard = new Scanner(System.in);
            String prompt = q.getPrompt();
            System.out.println(prompt);
            HashMap<String, String> userPairs = new HashMap<>();
            for(int i = 0; i < foreignWords.size(); i++){
                System.out.println(foreignWords.get(i) + ": " + englishWords.get(i));
            }
            for(Map.Entry<String, String> entry : q.getWordPairs().entrySet()){
                System.out.println(entry.getKey() + ": ");
                String userInput = keyboard.nextLine();
                userPairs.put(entry.getKey(), userInput);
            }
            if(q.checkAnswer(userPairs)){
                System.out.println("Correct!");
                numQuestionsCorrect++;
            }else{
                System.out.println("Incorrect. The correct answer is: \n" + q.toString());
            }
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

