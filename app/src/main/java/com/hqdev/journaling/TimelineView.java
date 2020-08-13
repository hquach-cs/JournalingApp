package com.hqdev.journaling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimelineView extends LinearLayout {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Integer> eventType;
    List<List<TimelineEventClass>> events;

    public TimelineView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timeline_layout, this);
        recyclerView = findViewById(R.id.recyclerView);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        eventType = new ArrayList<>();
        events = new ArrayList<>();
        createEvents();
        // specify an adapter (see also next example)
        mAdapter = new TimelineAdapter(eventType,events);
        recyclerView.setAdapter(mAdapter);
    }

    void createEvents(){
        eventType.add(0);
        eventType.add(2);
        eventType.add(0);
        events.add(new ArrayList<TimelineEventClass>());
        events.add(new ArrayList<TimelineEventClass>());
        events.add(new ArrayList<TimelineEventClass>());
        events.get(0).add(new TimelineEventClass(new DateClass("1:00")));
        events.get(1).add(new TimelineEventClass("Food Shopping","Kroger on ...",new DateClass("2:00"),new DateClass("1:00")));
        events.get(1).add(new TimelineEventClass("Food Shopping","Kroger on ...",new DateClass("2:00"),new DateClass("1:00")));
        events.get(2).add(new TimelineEventClass(new DateClass("2:00")));
    }

}
