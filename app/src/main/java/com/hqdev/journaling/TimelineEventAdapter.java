package com.hqdev.journaling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimelineEventAdapter extends RecyclerView.Adapter<TimelineEventAdapter.TimelineEventHolder> {
    List<TimelineEventClass> events;

    public TimelineEventAdapter(List<TimelineEventClass> events){ this.events = events;}

    public static class TimelineEventHolder extends RecyclerView.ViewHolder{
        TextView eventTitle,eventDesc,eventTime;

        public TimelineEventHolder(View itemView){
            super(itemView);
            eventTitle = itemView.findViewById(R.id.timeline_card_name);
            eventDesc = itemView.findViewById(R.id.timeline_card_desc);
            eventTime = itemView.findViewById(R.id.timeline_card_time);
        }

        public void setText(String title,String desc,String time){
            eventTitle.setText(title);
            eventDesc.setText(desc);
            eventTime.setText(time);
        }
    }

    @NonNull
    @Override
    public TimelineEventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.timeline_event_card,parent,false);
        return new TimelineEventAdapter.TimelineEventHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineEventHolder holder, int position) {
        TimelineEventClass event = events.get(position);
        holder.setText(event.Title,event.Description,event.getEventTime());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
