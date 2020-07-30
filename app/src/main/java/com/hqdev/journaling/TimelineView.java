package com.hqdev.journaling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimelineView extends LinearLayout {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<TimelineEventClass> events;

    public TimelineView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timeline_layout, this);
        recyclerView = findViewById(R.id.recyclerView);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        events = updateEvent();

        // specify an adapter (see also next example)
        mAdapter = new TimelineAdapter(events);
        recyclerView.setAdapter(mAdapter);
    }

    private List<TimelineEventClass> updateEvent(){
        List<TimelineEventClass> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int hour12hrs = calendar.get(Calendar.HOUR);
        int amorpm = calendar.get(Calendar.AM_PM);
        events.add(new TimelineEventClass(""+hour12hrs+":00 " + ((amorpm == 0) ? "AM" : "PM")));
        events.add(new TimelineEventClass("Food Shopping","Kroger + Publix",hour12hrs + ":00", (hour12hrs+1)+":00"));

        return events;
    }
}
