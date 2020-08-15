package com.hqdev.journaling;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimelineEventAdapter extends RecyclerView.Adapter<TimelineEventAdapter.TimelineEventHolder> {
    List<TimelineEventClass> events;
    public TimelineEventAdapter(List<TimelineEventClass> events){
        List<TimelineEventClass> e = new ArrayList<>(events);
        e.remove(0);
        this.events = e;
    }

    public static class TimelineEventHolder extends RecyclerView.ViewHolder{
        public TimelineEventHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public TimelineEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.timeline_event_card,parent,false);
        return new TimelineEventHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineEventHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
