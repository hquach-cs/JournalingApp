package com.hqdev.journaling;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TimelineAdapter extends RecyclerView.Adapter{
    List<List<TimelineEventClass>> events;
    Boolean isVisible = false;
    public TimelineAdapter(List<List<TimelineEventClass>> events){
        this.events = events;
    }

    public static class TimelineHolder extends RecyclerView.ViewHolder {
        TextView dividerTime;
        RecyclerView recyclerView;
        RecyclerView.Adapter mmAdapter;
        RecyclerView.LayoutManager layoutManager;
        List<TimelineEventClass> events;
        public TimelineHolder(View view) {
            super(view);
            dividerTime = view.findViewById(R.id.divider_time);
            recyclerView = view.findViewById(R.id.timeline_event_recyclerView);
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(false);

        }

        public void init(List<TimelineEventClass> events){
            this.events = events;
            dividerTime.setText(events.get(0).DividerTime.getWholeTime());
            mmAdapter = new TimelineEventAdapter(events);
            new ItemTouchHelper(simpleItemTouchCallback).attachToRecyclerView(recyclerView);
            recyclerView.setAdapter(mmAdapter);
        }


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                //Remove swiped item from list and notify the RecyclerView
                int pos = viewHolder.getAdapterPosition()+1;
                events.remove(pos);
                init(events);
            }
        };
    }

    public static class TimelineDayHolder extends RecyclerView.ViewHolder{
        TextView date;
        public TimelineDayHolder(View view){
            super(view);
            date = view.findViewById(R.id.timeline_newday_title);
        }

        public void init(TimelineEventClass event){
            date.setText(event.DividerTime.getWholeDate());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(events.get(position).get(0).NewDay)
            return 1;
        else
            return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == 1){
            view = layoutInflater.inflate(R.layout.timeline_event_day,parent,false);
            return new TimelineDayHolder(view);
        }
        view = layoutInflater.inflate(R.layout.timeline_event_layout,parent,false);
        return new TimelineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 1){
            ((TimelineDayHolder)holder).init(events.get(position).get(0));
        }else{
            ((TimelineHolder)holder).init(events.get(position));
        }

        this.isVisible = true;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
