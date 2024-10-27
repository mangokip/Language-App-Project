package com.app;

public class CockySpeakUI {

    private static CockySpeak cockySpeak = new CockySpeak(); // Initialize CockySpeak

    public static void main(String[] args) {
        scenarioJimSmith();
    }

    public static void scenarioJimSmith() {
        // Jim logs in
        System.out.println("Jim Smith registers...");
        cockySpeak.register("JimSmith", "securePass", "jimsmith@example.com");
        System.out.println("Jim logs in...");  
        cockySpeak.login("JimSmith", "securePass");

        // Set language to Spanish for Jim
        cockySpeak.setLanguage(new Language("Spanish"));

        // Jim starts Module 1
        System.out.println("Jim starts Module 1...");
        cockySpeak.loadFlashcards();
        cockySpeak.searchAndStorePhraseOrWord();
        cockySpeak.startModule("Module 1");
    

        // Jim logs out after Module 1
        System.out.println("Jim logs out...");
        cockySpeak.logout();

        // Jim logs back in
        System.out.println("Jim logs back in...");
        cockySpeak.login("JimSmith", "securePass");

        // Resume from progress (should start Module 2 if Module 1 is completed)
        System.out.println("Resuming from where Jim left off...");
        cockySpeak.resumeFromProgress("Spanish");

        // Jim logs out again after completing his session
        System.out.println("Jim logs out...");
        cockySpeak.logout();
    }
}
