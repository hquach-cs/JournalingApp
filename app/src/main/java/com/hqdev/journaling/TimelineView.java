package com.hqdev.journaling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
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
        getCurrentTimelinePos();
        recyclerView.setAdapter(mAdapter);
    }

    void createEvents(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        int startingpos;
        for(int cnt = 0; cnt < 7;cnt++){
            startingpos = cnt*25;
            for(int i = 0; i < 25;i++){
                events.add(new ArrayList<TimelineEventClass>());
            }
            events.get(startingpos).add(new TimelineEventClass(new DateClass(calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.YEAR)),true));
            events.get(startingpos+1).add(new TimelineEventClass(new DateClass("12:00",false),false));
            for(int i = 1; i < 12;i++){
                events.get(startingpos+i+1).add(new TimelineEventClass(new DateClass(""+i+":00",false),false));
            }
            events.get(startingpos+13).add(new TimelineEventClass(new DateClass("12:00",true),false));
            for(int i = 13; i < 24;i++){
                events.get(startingpos+i+1).add(new TimelineEventClass(new DateClass(""+(i-12)+":00",true),false));
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }



    public void addEvent(TimelineEventClass event){
        this.events.get(1).add(event);
        mAdapter.notifyItemChanged(1);
    }

    int getTimelinePos(){
        Calendar calendar = Calendar.getInstance();
        return (((calendar.get(Calendar.DAY_OF_WEEK) - 1)*25)+1+((calendar.get(Calendar.AM_PM) == 1) ? calendar.get(Calendar.HOUR) + 12: calendar.get(Calendar.HOUR)));
    }

    public void getCurrentTimelinePos(){
        RecyclerView.SmoothScroller smoothScroller = new
                LinearSmoothScroller(getContext()) {
                    @Override protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
        smoothScroller.setTargetPosition(getTimelinePos());
        this.layoutManager.startSmoothScroll(smoothScroller);
    }

}
