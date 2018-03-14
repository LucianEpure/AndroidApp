package com.example.android.memoryapp;

import java.util.ArrayList;


public class DatabaseSimu {
    private ArrayList<String> myMemories;

    public DatabaseSimu(){
        myMemories= new ArrayList<String>();
        populate();
    }

    private void populate() {
        myMemories.add("Inceput facultate 1000 de ani");
        myMemories.add("Cumparat catelus astazi");
        myMemories.add("Vazut Dublin 100 de zile");
        myMemories.add("Imbratisare acum!!!");
        myMemories.add("iubire tuturora mereu");
    }

    public int getNoData(){
        return myMemories.size();
    }

    public String getItemFromPos(int pos){
        return myMemories.get(pos);
    }
}
