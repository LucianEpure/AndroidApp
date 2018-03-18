package com.example.android.memoryapp.model;

/**
 * Created by Anca on 18/03/2018.
 */

public class Memory {
    private int id;
    private String title;
    private String date;
    private String description;
    private byte[] image;

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
