package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class MultipleChoice extends Question {
    private static final String prompt = "Select the foreign word that matches this english word: ";
    private ArrayList<Word> wordsForQuestion;
    private Word correctAnswer;



    public MultipleChoice(int diff, Word correctAnswer, Language language){
        super(prompt, diff);
        Random rand = new Random();
        WordList wordList = WordList.getInstance();
        this.correctAnswer = correctAnswer;
        List<Word> words = wordList.getWordsByGenre(language, correctAnswer.getGenre());
        wordsForQuestion = new ArrayList<Word>();
        wordsForQuestion.add(correctAnswer);
        while(wordsForQuestion.size() < 4){
            Word wordToAdd = words.get(rand.nextInt(words.size()));
            if(wordsForQuestion.contains(wordToAdd)){
                continue;
            }else{
                wordsForQuestion.add(wordToAdd);
            }
        }
        Collections.shuffle(wordsForQuestion);
    }

    public boolean checkAnswer(Word userAnswer){
        if(userAnswer.equals(correctAnswer)){
            return true;
        }
        else return false;
    }
    public ArrayList<Word> getWordsForQuestion(){
        return wordsForQuestion;
    }
    public Word getCorrectAnswer(){
        return correctAnswer;
    }
}
