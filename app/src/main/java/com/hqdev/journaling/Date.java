package com.hqdev.journaling;

public class Date {
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public int year;
    public int day;
    public int month;
    public int hour;
    public int min;

    public String getMonth(){
        return monthNames[month];
    }

    public String getDate(){
        return ("" + month + "/" + day + "/" + year);
    }
}