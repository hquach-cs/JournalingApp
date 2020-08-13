package com.hqdev.journaling;

import java.util.Calendar;

public class DateClass {
    public String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public int year;
    public int day;
    public int month;
    public int hour;
    public int minute;
    public boolean PM; //0 = AM | 1 = PM

    //Constructor Current Date
    public DateClass(){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
    }

    public DateClass(String time,Boolean PM){
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        String[] timeSplit = time.split(":");
        hour = Integer.parseInt(timeSplit[0]);
        minute = Integer.parseInt(timeSplit[1]);
        this.PM = PM;
    }

    public DateClass(int month, int day, int year){
        this.year = year;
        this.day = day;
        this.month = month;
    }


    public DateClass(int month,int year){
        this.month = month;
        this.year = year;
    }

    public String getMonth(){
        return monthNames[month];
    }

    public String getDate(){
        return ("" + month + "/" + day + "/" + year);
    }

    public String getTime(){
        return "" + hour + ":" + ((minute == 0) ? "00" : (minute < 10) ? "0" + minute : minute) ;
    }

    public String getWholeDate(){
        return( monthNames[month] + " " + day + ", " + year );
    }

    public String getTotalTime(){
        return "" + hour + ":" + ((minute == 0) ? "00" : (minute < 10) ? "0" + minute : minute) + " " + ((PM) ? "PM" : "AM") ;
    }

}
