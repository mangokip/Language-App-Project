package com.app;

import java.util.UUID;

public class CockySpeakUI {
    private CockySpeak cockySpeak = new CockySpeak();
    public static void main(String[] args) {
        CockySpeakUI uI = new CockySpeakUI();
        String uniqueID = UUID.randomUUID().toString();
        System.out.println(uniqueID);
        //todo - add stub info in future

        uI.login("mike", "abc");

    }

    private void login(String userName, String password){
        cockySpeak.login(userName, password);
    }
}
