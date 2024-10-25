package com.app;

public class PhraseList {
    private static PhraseList phraseList;

    private PhraseList(){
        
    }

    public static PhraseList getInstance(){
        if(phraseList == null){
            phraseList = new PhraseList();
        }
        return phraseList;
    }
    
}
