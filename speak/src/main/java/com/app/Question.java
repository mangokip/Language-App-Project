package com.app;
//Carson Sessoms

import java.util.ArrayList;

public class Question {
    private String prompt;
    private int difficulty;

    public Question(String prompt, int difficulty) {
        this.prompt = prompt;
        this.difficulty = difficulty;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
    public String toString(){
        return " ";
    }
}

