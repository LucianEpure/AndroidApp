package com.example.android.memoryapp;

/**
 * Created by Lucian on 15.03.2018.
 */

public class Friend {
    private String firstName;
    private String lastName;
    private String helpInfo;
    private String image;

    public Friend(String firstName, String lastName, String helpInfo, String image){
        this.firstName = firstName;
        this.lastName = lastName;
        this.helpInfo = helpInfo;
        this.image = image;
    }

    public String toString(){
        String friendInfo = firstName + " " + lastName + " " + helpInfo + " ";
        return friendInfo;
    }
}
