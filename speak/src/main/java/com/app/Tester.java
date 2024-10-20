package com.app;

public class Tester {

    public static void main(String args[]){

    }

    private void testFillBlank(){
    Genre myGenre = Genre.TEST;
    Word word1 = new Word("hi", "hola", " ho-la", myGenre);
    Word word2 = new Word("milk", "leche", "le-che", myGenre);
    Word word3 = new Word("bye", "adios", "adi-ous", myGenre);
    Word word4 = new Word("", "hola", " ho-la", myGenre);


    }
    
}


