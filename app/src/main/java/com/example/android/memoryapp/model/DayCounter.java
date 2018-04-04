package com.example.android.memoryapp.model;

import java.util.Calendar;
import java.util.Date;

public class DayCounter {
    Date dateMemory;

    public DayCounter(Date dateMemory) {
        this.dateMemory = dateMemory;
    }

    public long getDaysPassed() {

        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        long diff = (now.getTime() - dateMemory.getTime()) / (1000 * 60 * 60 * 24);
        return diff;
    }
}
