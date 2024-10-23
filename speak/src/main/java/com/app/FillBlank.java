package com.app;
//carson Sessoms

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FillBlank extends Question {
    private Phrase sentence;
    private Word[] answers;
    private Word correctAnswer;
    private static final String sharedPrompt = "Select the answer choice that best completes the sentence: ";



    public FillBlank(int diff, Word correctAnswer, Phrase sentence, Language language) {
        super(sharedPrompt, diff);
        this.sentence = sentence;
        this.correctAnswer = correctAnswer;
        answers = new Word[4];
        Random rand = new Random();
        Genre wordGenre = correctAnswer.getGenre();
        WordList wordList = WordList.getInstance();
        List<Word> genreWords = wordList.getWordsByGenre(language, wordGenre);
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
        return(userAnswer.equals(correctAnswer));
    }

    public String toString(){
        StringBuilder sB = new StringBuilder();
        sB.append(this.sharedPrompt + "\n");
        ArrayList<String> foreignWords = new ArrayList<String>();
        foreignWords = sentence.getForeignPhrase(); 
        for(String word : foreignWords){
            if(word.equals(correctAnswer.getForeign())){
                sB.append(" ________ ");
            }
            else{
                sB.append(word + " ");
            }
        }
        for (int i = 0; i < answers.length; ++i) {
            sB.append("\n" + (i + 1) + ". " + answers[i].getForeign());
        }
        sB.append("\n");
        return sB.toString();
    }

}