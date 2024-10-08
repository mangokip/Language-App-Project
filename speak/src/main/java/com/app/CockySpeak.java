package com.app;

/*
 * LaMorra Strong
 */
public class CockySpeak {
    private Language currentLanguage;

    /**
     * Initializes the CockySpeak system with a default language.
     * 
     * @param languageCode the code for the default language (e.g., "en" for English)
     */
    public CockySpeak(String languageCode) {
        this.currentLanguage = new Language(languageCode);
        currentLanguage.loadLanguage();
    }

    /**
     * Sets the active language for the CockySpeak system.
     * 
     * @param languageCode the code for the language to switch to
     */
    public void setLanguage(String languageCode) {
        this.currentLanguage.setLanguageCode(languageCode);
        currentLanguage.loadLanguage();
    }

    /**
     * Processes a single word by creating a Word object and analyzing it.
     * 
     * @param wordText the word to process
     */
    public void processWord(String wordText) {
        Word word = new Word(wordText);
        word.analyze();
        // TODO: Further processing logic for the word
    }

    /**
     * Processes a phrase by creating a Phrase object and analyzing or translating it.
     * 
     * @param phraseText the phrase to process
     */
    public void processPhrase(String phraseText) {
        Phrase phrase = new Phrase(phraseText);
        phrase.analyzePhrase();
        // TODO: Further processing logic for the phrase
    }

    /**
     * Translates a given phrase based on the current language.
     * 
     * @param phraseText the phrase to translate
     */
    public void translatePhrase(String phraseText) {
        Phrase phrase = new Phrase(phraseText);
        phrase.translate();
        // TODO: Implement further translation logic
    }

    /**
     * Runs a demo of the CockySpeak system with sample word and phrase inputs.
     * 
     * TODO: Implement a full demo for testing.
     */
    public void demo() {
        // Demo for processing a word
        processWord("hello");

        // Demo for processing a phrase
        processPhrase("hello world");

        // Demo for translating a phrase
        translatePhrase("good morning");
    }
}
