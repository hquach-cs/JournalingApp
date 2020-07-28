package com.hqdev.journaling;

import java.util.Calendar;

public class TimelineEventClass {

    Boolean isDivider;
    String event_name;
    String event_desc;
    String event_startTime;
    String event_endTime;
    Calendar event_createDate;
    String divider_time;

    public TimelineEventClass(String event_name, String event_desc, String event_startTime, String event_endTime){
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.event_startTime = event_startTime;
        this.event_endTime = event_endTime;
        this.event_createDate = Calendar.getInstance();
        this.isDivider = false;
    }

    public TimelineEventClass(String divider_time){
        this.divider_time = divider_time;
        this.isDivider = true;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public String getEvent_startTime() {
        return event_startTime;
    }

    public String getEvent_endTime() {
        return event_endTime;
    }

    public Calendar getEvent_createDate() {
        return event_createDate;
    }

    public String getDivider_time(){ return divider_time; }

    public int getIsDivider(){
        if(this.isDivider)
            return 0;
        else
            return 1;
    }
}
