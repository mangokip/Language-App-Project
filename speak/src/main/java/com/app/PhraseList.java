package com.app;

import java.util.List;

public class PhraseList {
    private static PhraseList instance;
    private List<Phrase> phrases;


    private PhraseList() {
        loadPhrases();
    }


    public static PhraseList getInstance() {
        if (instance == null) {
            instance = new PhraseList();
        }
        return instance;
    }

    private void loadPhrases() {
        DataLoader dataLoader = new DataLoader();
        this.phrases = dataLoader.loadPhrases();

        if (phrases == null || phrases.isEmpty()) {
            System.out.println("Error: No phrases loaded from phrases.json");
        }
    }

    // Getter for the loaded phrases
    public List<Phrase> getPhrases() {
        return phrases;
    }
}
