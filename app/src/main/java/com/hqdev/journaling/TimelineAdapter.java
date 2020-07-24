package com.hqdev.journaling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineHolder> {

    List<TimelineEventClass> events;

    public TimelineAdapter(List<TimelineEventClass> events){
        this.events = events;
    }

    public static class TimelineHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TimelineHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public TimelineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_event_layout, parent, false);
        return new TimelineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
