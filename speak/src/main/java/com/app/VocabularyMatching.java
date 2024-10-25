package com.app;
//Carson Sessoms

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Str;

import java.util.HashMap;

public class VocabularyMatching extends Question {
    private Map<String, String> wordPairs;
    private Word wordNeedingStudying;
    private static final String vocabularyMatchingPrompt = "Match the words in their english form to their foreign form ";

    public VocabularyMatching(Language language, int diff, Word word) {
        super(vocabularyMatchingPrompt, diff);
        Random rand = new Random();
        wordNeedingStudying = word;
        // wordPairs = new HashMap<>();
        WordList wordList = WordList.getInstance();
        List<Word> words = wordList.getWordsByGenre(language, wordNeedingStudying.getGenre());
        ArrayList<Word> wordsForQuestion = new ArrayList<Word>();
        wordsForQuestion.add(wordNeedingStudying);
        while(wordsForQuestion.size() < 3){
            Word wordToAdd = words.get(rand.nextInt(words.size()));
            if(wordsForQuestion.contains(wordToAdd)){
                continue;
            }else{
                wordsForQuestion.add(wordToAdd);
            }
        }
        for(Word tempWord: wordsForQuestion){
            String english = tempWord.getText();
            String foreign = tempWord.getForeign();
            wordPairs.put(english, foreign);
        }
    }

    public boolean checkAnswer(HashMap<String, String> userPairs){
        boolean correct = true;
        for (Map.Entry<String, String> entry : wordPairs.entrySet()) {
            String englishWord = entry.getKey();
            String correctForeignWord = entry.getValue();
    
            // Get the user's translation for the English word
            String userForeignWord = userPairs.get(englishWord);
    
            // Compare the user's foreign word with the correct one
            if (!correctForeignWord.equals(userForeignWord)) {
                correct = false;  // Incorrect translation
                break;
            }
        }
    
        return correct;
    }

    public Map<String, String> getWordPairs(){
        return wordPairs;
    }
    
 
    public String toString(){
        StringBuilder sB = new StringBuilder();
        for(String key : wordPairs.keySet()){
            sB.append(key + ": " + wordPairs.get(key) + "\n");
        }
        return sB.toString();
    }
}