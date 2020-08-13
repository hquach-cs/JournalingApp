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

    public TimelineEventClass(Boolean d){
        if(d)
            Divider = 1;
        else
            Divider = 0;
    }

    public String getEventTime(){
        return (StartTime.getTime()+ "-" + EndTime.getTime());
    }

}
