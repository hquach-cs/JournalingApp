package com.hqdev.journaling;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarMonthAdapter extends RecyclerView.Adapter<CalendarMonthAdapter.CalendarMonthHolder> {
    List<DateClass> data;
    CalendarView parent;
    public CalendarMonthAdapter(List<DateClass> data,CalendarView parent){
        this.data = data;
        this.parent = parent;
    }

    public static class CalendarMonthHolder extends RecyclerView.ViewHolder{
        GridView gridView;
        Context context;
        CalendarMonthGridAdapter mAdapter;

        public CalendarMonthHolder(View view){
            super(view);
            context = view.getContext();
            gridView = view.findViewById(R.id.calender_grid);
        }

        public void CreateGrid(int month, int year,final CalendarView parentView){
            final List<Date> days = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            int monthBeginningCell;
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);
            while (days.size() < 42) {
                days.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            mAdapter = new CalendarMonthGridAdapter(context, days,month,year);
            gridView.setAdapter(mAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    parentView.selectTargetDate(days.get(position));
                    mAdapter.targetDate = parentView.targetDate;
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @NonNull
    @Override
    public CalendarMonthAdapter.CalendarMonthHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_month_layout,parent,false);
        return new CalendarMonthHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarMonthHolder holder, int position) {
        DateClass date = data.get(position);
        holder.CreateGrid(date.month,date.year,parent);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
