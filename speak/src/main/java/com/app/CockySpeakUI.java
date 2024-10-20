package com.app;
import java.util.Scanner;

public class CockySpeakUI {
    private CockySpeak cockySpeak = new CockySpeak(); // Initialize CockySpeak

    public static void main(String[] args) {
        CockySpeakUI uI = new CockySpeakUI();
        Scanner keyboard = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("Welcome to CockySpeak!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int choice = keyboard.nextInt();
            keyboard.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1: // Login
                    System.out.println("Please enter your username: ");
                    String username = keyboard.nextLine();
                    System.out.println("Please enter your password: ");
                    String password = keyboard.nextLine();

                    if (uI.login(username, password)) {
                        System.out.println("Logged in successfully!");
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;

                case 2: // Register
                    System.out.println("Please enter a username to register: ");
                    String newUsername = keyboard.nextLine();
                    System.out.println("Please enter a password: ");
                    String newPassword = keyboard.nextLine();
                    System.out.println("Please enter your email: ");
                    String email = keyboard.nextLine();

                    // Assuming a method to register new users
                    if (uI.cockySpeak.register(newUsername, newPassword, email)){
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Exiting... Goodbye!");
                    keyboard.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Attempts to log in a user.
     *
     * @param userName The username to log in with.
     * @param password The password to log in with.
     * @return true if login is successful, false otherwise.
     */
    private boolean login(String userName, String password) {
        cockySpeak.login(userName, password);
        return cockySpeak.getCurrentUser() != null;
    }


}
