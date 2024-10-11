package com.app;

import java.util.UUID;

public class CockySpeakUI {

    private CockySpeak 
    public static void main(String[] args) {
        String uniqueID = UUID.randomUUID().toString();
        System.out.println(uniqueID);
        //todo - add stub info in future

    }

    public void login(String username, String password){
        if(userList.hasUser(username)){
            User thisUser = userList.getUser(username);
            if(password.equals(thisUser.getPassword())){

            }else
            System.out.println("Invalid password")
        }else{
            System.out.println("Username not found")
        }
    }

    

}
