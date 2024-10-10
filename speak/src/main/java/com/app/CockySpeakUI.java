package com.app;

import java.util.UUID;
import java.util.Scanner;

public class CockySpeakUI {

    Scanner keyboard = new Scanner(System.in);
    CockySpeak cs = CockySpeak();
    public static void main(String[] args) {
        
    }

    private void login() {

        String username = getField("Enter Username");
        String password = getField("Enter Password");

        if(cs.login(username, password)) {}
        
        
    }

    private String getField(String prompt) {
        System.out.println(prompt + ": ");
        return keyboard.nextLine();
    }

    

}
