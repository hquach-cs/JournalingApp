package com.hqdev.journaling;

import java.util.Calendar;
import java.util.Date;

public class TimelineEventClass {

    Boolean isDivider;
    String event_name;
    String event_desc;
    Date event_startDate;
    Date event_endDate;
    Date event_createDate;
    Date divider_time;

    public TimelineEventClass(String event_name, String event_desc, Date event_startDate, Date event_endDate){
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.event_startDate = event_startDate;
        this.event_endDate = event_endDate;
        this.event_createDate = Calendar.getInstance().getTime();
        this.isDivider = false;
    }

    public TimelineEventClass(Date divider_time){
        this.divider_time = divider_time;
        this.isDivider = true;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public Date getEvent_startDate() {
        return event_startDate;
    }

    public Date getEvent_endDate() {
        return event_endDate;
    }

    public Date getEvent_createDate() {
        return event_createDate;
    }

    public int getIsDivider(){
        if(this.isDivider)
            return 0;
        else
            return 1;
    }
}
