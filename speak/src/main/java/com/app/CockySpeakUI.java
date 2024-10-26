package com.app;

public class CockySpeakUI {

    private static CockySpeak cockySpeak = new CockySpeak(); // Initialize CockySpeak

    public static void main(String[] args) {
        scenarioJimSmith();
    }

    public static void scenarioJimSmith() {

        System.out.println("Registering Jim Smith...");
        cockySpeak.register("JimSmith", "securePass", "jim@smith.com");

        System.out.println("Jim Smith logging in...");
        cockySpeak.login("JimSmith", "securePass");

        // Set Language and Start Module 1
        Language spanish = new Language("Spanish");
        cockySpeak.setLanguage(spanish);

        System.out.println("Jim starts Module 1...");
        Lesson module1 = new Lesson("Module 1", spanish);
        int module1Score = module1.playLesson();

        // Check if the user passed Module 1 before starting Module 2
        if (module1Score >= 80) {
            System.out.println("\nJim starts Module 2...");
            Lesson module2 = new Lesson("Module 2", spanish);
            module2.playLesson();
        } else {
            System.out.println("Jim did not pass Module 1. Please try again.");
        }

        cockySpeak.logout();

        System.out.println("Jim logs back in...");
        cockySpeak.login("JimSmith", "securePass");

        System.out.println("Jim continues from Module 2.");
    }
}
