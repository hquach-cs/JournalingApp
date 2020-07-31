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
import java.util.Date;
import java.util.List;

public class CalendarView extends LinearLayout {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<DateClass> months;
    DateClass nextYear;
    DateClass prevYear;
    DateClass targetDate;
    int currentDatePos;

    public CalendarView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_layout, this);
        initRecyclerView();
    }

    private void MonthData(int year,boolean next){
        if(months == null) {
            nextYear = new DateClass();
            prevYear = new DateClass();
            nextYear.year = year;
            prevYear.year = year;
            months = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                months.add(new DateClass(i, year));
            }
            return;
        }
        if(next){
            for (int i = 0; i < 12; i++) {
                months.add(new DateClass(i, year));
            }
        }else{
            for (int i = 11; i >= 0; i--) {
                months.add(0,new DateClass(i, year));
            }
        }
    }

    private void initRecyclerView(){
        targetDate = new DateClass();
        recyclerView = findViewById(R.id.calendar_recyclerView);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        currentDatePos = Calendar.getInstance().get(Calendar.MONTH);
        layoutManager.scrollToPosition(currentDatePos);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        MonthData(Calendar.getInstance().get(Calendar.YEAR),false);
        mAdapter = new CalendarMonthAdapter(months,this);
        recyclerView.setAdapter(mAdapter);
    }

    public RecyclerView getRecyclerView(){
        return recyclerView;
    }

    public String getMonth(int position){
        DateClass date = months.get(position);
        return date.getMonth() + " " + date.year;
    }

    public void getNextYearMonth(){
        Log.i("Calendar","Next");
        nextYear.year++;
        MonthData(nextYear.year,true);
        mAdapter.notifyDataSetChanged();
    }

    public void getPrevYearMonth(){
        Log.i("Calendar","Prev");
        prevYear.year--;
        MonthData(prevYear.year,false);
        mAdapter.notifyDataSetChanged();
        layoutManager.scrollToPosition(12);
        currentDatePos += 12;
    }

    public void selectTargetDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        targetDate.year = calendar.get(Calendar.YEAR);
        targetDate.month = calendar.get(Calendar.MONTH);
        targetDate.day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i("TargetDate","" + targetDate.month + "/" + targetDate.day + "/" + targetDate.year);
    }

    public void returnToday(){
        layoutManager.scrollToPosition(currentDatePos);
        selectTargetDate(Calendar.getInstance().getTime());
        mAdapter.notifyDataSetChanged();
    }
}
