package com.hqdev.journaling;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public TimelineHolder(View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.timeline_card_name);
            eventDesc = itemView.findViewById(R.id.timeline_card_desc);
            eventTime = itemView.findViewById(R.id.timeline_card_time);
        }

        public void setText(String name,String desc, String time){
            eventName.setText(name);
            eventDesc.setText(desc);
            eventTime.setText(time);
        }
    }

    public static class DividerHolder extends RecyclerView.ViewHolder{
        TextView timeView;
        public DividerHolder(View itemView) {
            super(itemView);
            timeView = itemView.findViewById(R.id.divider_time);
        }

        public void setText(String time){
            timeView.setText(time);
        }
    }

    @Override
    public int getItemViewType(int position){
        return events.get(position).getIsDivider();
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
        }else{
            view = layoutInflater.inflate(R.layout.timeline_event_layout,parent,false);
            return new TimelineHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TimelineEventClass event = events.get(position);
        if(getItemViewType(position) == 0) { //Divider
            ((DividerHolder) holder).setText(event.getDivider_time());
        }else{
            ((TimelineHolder) holder).setText(event.getEvent_name(),event.getEvent_desc(),event.getEvent_startTime() + "-" + event.getEvent_endTime());
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
