package com.hqdev.journaling;

import java.util.Calendar;

public class TimelineEventClass {
    Boolean NewDay;
    String Title;
    String Description;
    DateClass StartTime;
    DateClass EndTime;
    DateClass CreationDate;
    DateClass DividerTime;

    //Divider Event
    public TimelineEventClass(String eventTitle, String eventDesc,DateClass startTime, DateClass endTime){
        Title = eventTitle;
        Description = eventDesc;
        StartTime = startTime;
        EndTime = endTime;
        CreationDate = new DateClass();
    }

    public TimelineEventClass(DateClass DividerTime,Boolean NewDay){
        this.NewDay = NewDay;
        this.DividerTime = DividerTime;
    }


    public String getEventTime(){
        return (StartTime.getTime()+ "-" + EndTime.getTime());
    }

}
