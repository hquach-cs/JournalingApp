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

import java.util.List;


public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TimelineHolder>{

    List<List<TimelineEventClass>> events;

    public TimelineAdapter(List<List<TimelineEventClass>> events){
        this.events = events;
    }

    public static class TimelineHolder extends RecyclerView.ViewHolder {
        /*
        RecyclerView recyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;
         */
        RelativeLayout newDay;
        TextView newDayDay,newDayEventCnt;
        TextView dividerTime;
        public TimelineHolder(View itemView) {
            super(itemView);
            //recyclerView = itemView.findViewById(R.id.timeline_event_recyclerView);
            //layoutManager = new LinearLayoutManager(itemView.getContext());
            newDay = itemView.findViewById(R.id.newday);
            newDayDay = itemView.findViewById(R.id.timeline_newday_day);
            newDayEventCnt = itemView.findViewById(R.id.timeline_newday_event_cnt);
            dividerTime = itemView.findViewById(R.id.divider_time);
        }

        public void setEvents(List<TimelineEventClass> event){
            DateClass day = event.get(0).DividerTime;
            if(event.get(0).NewDay){
                newDay.setVisibility(View.VISIBLE);
                newDayDay.setText(day.getWholeDate());
                newDayEventCnt.setText("Events: " + (event.size()-1));
            }
            dividerTime.setText(day.getTotalTime());
            /*
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new TimelineEventAdapter(events);
            recyclerView.setAdapter(mAdapter);
             */
        }
    }



    @NonNull
    @Override
    public TimelineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.timeline_event_layout,parent,false);
        return new TimelineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineHolder holder, int position) {
            holder.setEvents(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

}
