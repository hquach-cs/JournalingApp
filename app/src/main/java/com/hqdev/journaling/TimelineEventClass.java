package com.hqdev.journaling;

import java.util.Calendar;
import java.util.Date;

public class TimelineEventClass {

    String event_name;
    String event_desc;
    Date event_startDate;
    Date event_endDate;
    Date event_createDate;

    public TimelineEventClass(String event_name, String event_desc, Date event_startDate, Date event_endDate){
        this.event_name = event_name;
        this.event_desc = event_desc;
        this.event_startDate = event_startDate;
        this.event_endDate = event_endDate;
        this.event_createDate = Calendar.getInstance().getTime();
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
}
