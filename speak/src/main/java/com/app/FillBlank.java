package com.app;
//carson Sessoms

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class FillBlank extends Question {
    private Phrase sentence;
    private Word[] answers;
    private Word correctAnswer;

    public FillBlank(int diff, String prompt, Word correctAnswer, Phrase sentence) {
        super(prompt, diff);
        this.sentence = sentence;
        this.correctAnswer = correctAnswer;
        answers = new Word[4];
        Random rand = new Random();
        Genre wordGenre = correctAnswer.getGenre();
        WordList wordList = WordList.getInstance();
        ArrayList<Word> genreWords = wordList.getWordsGenre(wordGenre);
        answers[0] = correctAnswer;
        for(int i = 1; i < answers.length; i++){
            Word tempWord = genreWords.get(rand.nextInt(genreWords.size()));
            if(!Arrays.asList(answers).contains(tempWord)){ //makes sure the random word isnt already in the list
                answers[i] = tempWord;
            }
            else{
                i--;
            }
        }
    }

    public boolean checkAnswer(Word userAnswer){
        return(userAnswer == correctAnswer);
    }

}
