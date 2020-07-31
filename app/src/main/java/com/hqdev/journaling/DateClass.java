package com.hqdev.journaling;

public class DateClass {
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public int year;
    public int day;
    public int month;
    public int hour;
    public int min;

    public DateClass(){ }

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
}
