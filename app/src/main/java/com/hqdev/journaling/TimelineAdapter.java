package com.hqdev.journaling;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TimelineAdapter extends RecyclerView.Adapter {

    List<List<TimelineEventClass>> events;
    List<Integer> eventType;

    public TimelineAdapter(List<Integer> eventType,List<List<TimelineEventClass>> events){
        this.eventType = eventType;
        this.events = events;
    }

    public static class TimelineHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;

        public TimelineHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.timeline_event_recyclerView);
            layoutManager = new LinearLayoutManager(itemView.getContext());
        }

        public void setRecyclerView(List<TimelineEventClass> events){
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new TimelineEventAdapter(events);
            recyclerView.setAdapter(mAdapter);
        }

    }

    public static class DividerHolder extends RecyclerView.ViewHolder{
        TextView time;
        public DividerHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.divider_time);
        }

        public void setTimeText(String time){
            this.time.setText(time);
        }
    }

    @Override
    public int getItemViewType(int position){
        return eventType.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == 0){
            view = layoutInflater.inflate(R.layout.timeline_divider_layout,parent,false);
            return new DividerHolder(view);
        }
        view = layoutInflater.inflate(R.layout.timeline_event_layout,parent,false);
        return new TimelineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) > 0) {
            ((TimelineHolder)holder).setRecyclerView(events.get(position));
        }else{
            ((DividerHolder)holder).setTimeText(events.get(position).get(0).DividerTime.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return eventType.size();
    }

}
