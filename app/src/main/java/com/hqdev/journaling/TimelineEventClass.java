package com.hqdev.journaling;

import java.util.Calendar;

public class TimelineEventClass {

    int Divider;
    String Title;
    String Description;
    DateClass StartTime;
    DateClass EndTime;
    DateClass CreationDate;
    DateClass DividerTime;

    //Divider Constructor
    public TimelineEventClass(DateClass date){
        Divider = 0;
        DividerTime = date;
    }

    //Divider Event
    public TimelineEventClass(String eventTitle, String eventDesc,DateClass startTime, DateClass endTime){
        Divider = 1;
        Title = eventTitle;
        Description = eventDesc;
        StartTime = startTime;
        EndTime = endTime;
        CreationDate = new DateClass();
    }

    //Empty Event
    public TimelineEventClass(){
        Divider = 1;
    }

}
