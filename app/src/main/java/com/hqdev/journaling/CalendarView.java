package com.hqdev.journaling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.core.view.GestureDetectorCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarView extends LinearLayout {
    GridView gridView;
    GestureDetectorCompat gestureDetectorCompat;
    Boolean minimized;
    com.hqdev.journaling.Date targetDate;


    public CalendarView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.calendar_layout, this);
        gridView = findViewById(R.id.calender_grid);
        minimized = false;
        targetDate = new com.hqdev.journaling.Date();
        updateCalendar();
        gestureDetectorCompat = new GestureDetectorCompat(context,new GestureListener());
    }

    public void updateCalendar() {
        List<Date> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int monthBeginningCell;
        targetDate.month = calendar.get(Calendar.MONTH);
        if(minimized){
            monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            calendar.add(Calendar.DAY_OF_MONTH,-monthBeginningCell);
            while(days.size() < 7){
                days.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);
            while (days.size() < 35) {
                days.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        gridView.setAdapter(new CalendarAdapter(getContext(), days));
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(distanceY > 30) {
                minimized = true;
                updateCalendar();
            }else if(distanceY < -30){
                minimized = false;
                updateCalendar();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            minimized = !minimized;
            updateCalendar();
            return super.onDoubleTap(e);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onInterceptTouchEvent(event);
    }

    public String getTargetDate(){
        return targetDate.getMonth();
    }


}
