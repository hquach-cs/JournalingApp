package com.hqdev.journaling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarView extends LinearLayout {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    DateClass targetDate;
    DateClass currentDate;
    List<DateClass> months;
    Boolean minimized;

    public CalendarView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_layout, this);
        initRecyclerView();
    }

    private void MonthData(){
        months = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        currentDate = new DateClass(calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.YEAR));
        months.add(new DateClass(calendar.get(Calendar.MONTH)-1,calendar.get(Calendar.YEAR)));
        months.add(currentDate);
        months.add(new DateClass(calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR)));
    }

    private void initRecyclerView(){
        recyclerView = findViewById(R.id.calendar_recyclerView);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        layoutManager.scrollToPosition(1);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        MonthData();
        //Data
        mAdapter = new CalendarMonthAdapter(months,currentDate,targetDate);
        recyclerView.setAdapter(mAdapter);
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    public String getMonth(int position){
        DateClass date = months.get(position);
        return date.getMonth();
    }

}