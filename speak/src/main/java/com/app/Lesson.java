package com.app;
//Carson Sessoms
import java.util.ArrayList;


public class Lesson {
    private String topic;
    private ArrayList<Question> questions;
    private boolean lessonStatus;

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
        
    }

    public boolean getLessonStatus(){
        return lessonStatus;
    }

    public void stopLesson(){
        
    }

    public Object getTopic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopic'");
    }

    public Question[] getQuestions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getQuestions'");
    }

    public Object isLessonStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isLessonStatus'");
    }
}