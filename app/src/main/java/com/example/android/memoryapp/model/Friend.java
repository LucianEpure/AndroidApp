package com.example.android.memoryapp.model;

/**
 * Created by Lucian on 15.03.2018.
 */

public class Friend  {
    private int id;
    private String firstName;
    private String lastName;
    private String helpInfo;
    private byte[] image;
    private int known;


    public String toString(){
        String friendInfo = firstName + " " + lastName + " " + helpInfo + " ";
        return friendInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHelpInfo() {
        return helpInfo;
    }

    public byte[] getImage() {
        return image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHelpInfo(String helpInfo) {
        this.helpInfo = helpInfo;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKnown() {
        return known;
    }

    public void setKnown(int known) {
        this.known = known;
    }

    public String getAllName(){
        if (firstName.equals("") && lastName.equals("")) return "no name registered";
        if (firstName.equals("")) return lastName;
        if (lastName.equals("")) return firstName;
        return firstName+" "+lastName;
    }
}
