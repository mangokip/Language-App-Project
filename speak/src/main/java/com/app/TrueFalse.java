package com.app;

import java.util.Random;

public class TrueFalse extends Question {
    private static final String prompt = " True or False, do the following statements match the given translations: ";
    private boolean tOrF;


    public TrueFalse(int diff, Phrase phrase){
        super(prompt, diff);
        Random rand = new Random();
        tOrF = rand.nextBoolean();
        if(tOrF){

        }else{

        }

    }
}

