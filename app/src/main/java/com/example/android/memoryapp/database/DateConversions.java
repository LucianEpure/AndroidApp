package com.example.android.memoryapp.database;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anca on 20/03/2018.
 */

public class DateConversions {
    private int monthToInt(String init) {
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
    private String intToMonth(int init) {
        switch (init) {
            case (1):
                return "Jan";
            case (2):
                return "Feb";
            case (3):
                return "Mar";
            case (4):
                return "Apr";
            case (5):
                return "May";
            case (6):
                return "Jun";
            case (7):
                return "Jul";
            case (8):
                return "Aug";
            case (9):
                return "Sep";
            case (10):
                return "Oct";
            case (11):
                return "Nov";
            case (12):
                return "Dec";
            default:
                return "";
        }
    }
    public String getSQLFormattedDate(String dateString){
        String[] splited = dateString.split("\\s+");
        int day = Integer.parseInt(splited[0]);
        int month = monthToInt(splited[1]);
        int year = Integer.parseInt(splited[2]);

        String sqlFormat = year+ "-" +month+ "-" +day;
        return sqlFormat;
    }

    public Date getDateFromSQLFormat(String dateString){
        String[] splited = dateString.split("\\-");
        int day = Integer.parseInt(splited[2]);
        int month =  Integer.parseInt(splited[1]);
        int year = Integer.parseInt(splited[0]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Date myDate = cal.getTime();
        return myDate;
    }

    public String getAppFormatFromSQL(String dateString){
        String[] splited = dateString.split("\\-");
        int day = Integer.parseInt(splited[2]);
        int month =  Integer.parseInt(splited[1]);
        int year = Integer.parseInt(splited[0]);
        String appFormat = day +" "+intToMonth(month)+" "+year;
        return appFormat;
    }

}
