package com.app;
//carson Sessoms

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

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
        List<Word> genreWords = wordList.getWordsByGenre(wordGenre);
        answers[rand.nextInt(4)] = correctAnswer;
        for(int i = 0; i < answers.length; i++){
            Word tempWord = genreWords.get(rand.nextInt(genreWords.size()));
            if(answers[i].equals(correctAnswer)){
                continue;
            }
            else if(!Arrays.asList(answers).contains(tempWord)){ //makes sure the random word isnt already in the list
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
