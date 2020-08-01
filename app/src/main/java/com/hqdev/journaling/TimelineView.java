package com.hqdev.journaling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.ArrayList;
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
        initEvent();
        // specify an adapter (see also next example)
        mAdapter = new TimelineAdapter(events);
        recyclerView.setAdapter(mAdapter);
    }

    private void initEvent(){
        events = new ArrayList<>();
        events.add(new TimelineEventClass(new DateClass("12:00",false)));
        events.add(new TimelineEventClass("Food Shopping","Kroger at ...", new DateClass("12:00",true),new DateClass("1:00",true)));
        events.add(new TimelineEventClass(new DateClass("1:00",false)));

    }
}
