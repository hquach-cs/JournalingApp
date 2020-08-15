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
    List<List<TimelineEventClass>> events;

    public TimelineView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timeline_layout, this);
        recyclerView = findViewById(R.id.recyclerView);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        events = new ArrayList<>();
        createEvents();
        // specify an adapter (see also next example)
        mAdapter = new TimelineAdapter(events);
        recyclerView.setAdapter(mAdapter);
    }

    void createEvents(){
        for(int i = 0; i < 24;i++){
            events.add(new ArrayList<TimelineEventClass>());
        }
        events.get(0).add(new TimelineEventClass(new DateClass("12:00",false),true));
        for(int i = 1; i < 12;i++){
            events.get(i).add(new TimelineEventClass(new DateClass(""+i+":00",false),false));
        }
        events.get(12).add(new TimelineEventClass(new DateClass("12:00",true),false));
        for(int i = 13; i < 24;i++){
            events.get(i).add(new TimelineEventClass(new DateClass(""+(i-12)+":00",true),false));
        }
    }

    public void addEvent(TimelineEventClass event){
        this.events.get(1).add(event);
        mAdapter.notifyItemChanged(1);
    }

}
