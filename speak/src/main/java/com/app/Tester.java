package com.app;

import java.util.ArrayList;
public class Tester {

    public static void main(String args[]){
        Genre myGenre = Genre.TEST;
    Word word1 = new Word("hi", "hola", " ho-la", myGenre,1,false);
    Word word2 = new Word("milk", "leche", "le-che", myGenre,1,false);
    Word word3 = new Word("bye", "adios", "adi-ous", myGenre,1,false);
    Word word4 = new Word("this", "idk", " ho-la", myGenre,1,false);
    ArrayList<Word> myWords = new ArrayList<Word>();
    myWords.add(word1);
    myWords.add(word2);
    myWords.add(word3);
    myWords.add(word4);
    Phrase myPhrase = new Phrase(myWords, "idk");
    FillBlank myFillBlank = new FillBlank(0, word4, myPhrase, );
    System.out.println(myFillBlank.toString());
    }

    // private void testFillBlank(){
    // Genre myGenre = Genre.TEST;
    // Word word1 = new Word("hi", "hola", " ho-la", myGenre,1,false);
    // Word word2 = new Word("milk", "leche", "le-che", myGenre,1,false);
    // Word word3 = new Word("bye", "adios", "adi-ous", myGenre,1,false);
    // Word word4 = new Word("this", "idk", " ho-la", myGenre,1,false);
    // ArrayList<Word> myWords = new ArrayList<Word>();
    // myWords.add(word1);
    // myWords.add(word2);
    // myWords.add(word3);
    // myWords.add(word4);
    // Phrase myPhrase = new Phrase(myWords, "idk");
    // FillBlank myFillBlank = new FillBlank(0, word4, myPhrase);
    
    // }
    
}


