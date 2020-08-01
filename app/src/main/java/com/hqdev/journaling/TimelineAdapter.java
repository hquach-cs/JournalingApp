package com.hqdev.journaling;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TimelineAdapter extends RecyclerView.Adapter {

    List<TimelineEventClass> events;

    public TimelineAdapter(List<TimelineEventClass> events){
        this.events = events;
    }

    public static class TimelineHolder extends RecyclerView.ViewHolder {
        TextView eventName, eventDesc, eventTime;
        RelativeLayout eventCard;
        public TimelineHolder(View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.timeline_card_name);
            eventDesc = itemView.findViewById(R.id.timeline_card_desc);
            eventTime = itemView.findViewById(R.id.timeline_card_time);
            eventCard = itemView.findViewById(R.id.timeline_card);
        }

        public void setText(TimelineEventClass event){
            if(event.Title != null) {
                eventName.setText(event.Title);
                eventDesc.setText(event.Description);
                eventTime.setText(event.StartTime.getTime() + "-" + event.EndTime.getTime());
            }else{
                eventName.setText("");
                eventDesc.setText("");
                eventTime.setText("");
                eventCard.setBackgroundResource(0);
            }
        }
    }

    public static class DividerHolder extends RecyclerView.ViewHolder{
        TextView timeView;
        public DividerHolder(View itemView) {
            super(itemView);
            timeView = itemView.findViewById(R.id.divider_time);
        }

        public void setText(TimelineEventClass event){
            timeView.setText(event.DividerTime.getTime() + " " + ((event.DividerTime.AMorPM) ? "AM" : "PM"));
        }
    }

    @Override
    public int getItemViewType(int position){
        return events.get(position).Divider;
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
        TimelineEventClass event = events.get(position);
        if(getItemViewType(position) == 0) { //Divider
            ((DividerHolder) holder).setText(event);
        }else{
            ((TimelineHolder) holder).setText(event);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
