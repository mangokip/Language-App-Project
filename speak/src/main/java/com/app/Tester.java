package com.app;

import java.util.ArrayList;

public class Tester {

    public static void main(String args[]){

    }

    private void testFillBlank(){
    Genre myGenre = Genre.TEST;
    Word word1 = new Word("hi", "hola", " ho-la", myGenre);
    Word word2 = new Word("milk", "leche", "le-che", myGenre);
    Word word3 = new Word("bye", "adios", "adi-ous", myGenre);
    Word word4 = new Word("", "hola", " ho-la", myGenre);
    ArrayList<Word> tempList = new ArrayList<Word>();
    tempList.add(word1);
    tempList.add(word2);
    tempList.add(word3);
    tempList.add(word4);
    Phrase thisPhrase = new Phrase(tempList, "idk");
    FillBlank thisFillBlank = new FillBlank(1, "this", word4, thisPhrase);
    ArrayList<Question> questions = new ArrayList<Question>();
    questions.add(thisFillBlank);



    }
    
}


