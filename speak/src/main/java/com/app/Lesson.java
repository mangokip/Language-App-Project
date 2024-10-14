package com.app;
//Carson Sessoms
import java.util.ArrayList;


public class Lesson {
    private String topic;
    private ArrayList<Question> questions;
    private boolean lessonStatus;

    public Lesson(String topic){

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