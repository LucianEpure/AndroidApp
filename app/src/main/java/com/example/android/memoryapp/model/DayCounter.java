package com.example.android.memoryapp.model;

import java.util.Calendar;
import java.util.Date;

public class DayCounter {
    String dateString;

    public DayCounter(String dateString) {
        this.dateString = dateString;
    }

    private int monthTransformer(String init) {
        switch (init) {
            case ("Jan"):
                return 1;
            case ("Feb"):
                return 2;
            case ("Mar"):
                return 3;
            case ("Apr"):
                return 4;
            case ("May"):
                return 5;
            case ("Jun"):
                return 6;
            case ("Jul"):
                return 7;
            case ("Aug"):
                return 8;
            case ("Sep"):
                return 9;
            case ("Oct"):
                return 10;
            case ("Nov"):
                return 11;
            case ("Dec"):
                return 12;
            default:
                return -1;
        }
    }

    private Date convertStringToDate() {
        String[] splited = dateString.split("\\s+");
        int day = Integer.parseInt(splited[0]);
        int month = monthTransformer(splited[1]);
        int year = Integer.parseInt(splited[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Date then = cal.getTime();
        return then;
    }

    public long getDaysPassed() {

        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Date then = convertStringToDate();
        long diff = (now.getTime() - then.getTime()) / (1000 * 60 * 60 * 24);
        return diff;
    }
}
