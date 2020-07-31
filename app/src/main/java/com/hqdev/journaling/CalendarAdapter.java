package com.hqdev.journaling;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarAdapter extends ArrayAdapter<Date> {

    LayoutInflater inflater;
    List<Date> days;
    DateClass targetDate;


    public CalendarAdapter(Context context, List<Date> days, DateClass targetDate){
        super(context,R.layout.calendar_layout,days);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.days = days;
        this.targetDate = targetDate;
    }

    @Override
    public int getCount(){
        return days.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Date getItem(int position){
        return days.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        Calendar calendar = Calendar.getInstance();
        Date date = getItem(position);
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Date today = new Date();
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(today);
        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.calendar_day_layout, parent, false);
        TextView textView = (TextView)view.findViewById(R.id.day_date);
        // clear styling
        textView.setTextColor(Color.WHITE);

        if (month != calendarToday.get(Calendar.MONTH) || year != calendarToday.get(Calendar.YEAR)) {
            // if this day is outside current month, grey it out
            textView.setTextColor(Color.DKGRAY);
        } else if (day == calendarToday.get(Calendar.DATE)) {
            // if it is today, set it to blue/bold
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.ic_checkbox_blank_circle);
        }
        textView.setText(String.valueOf(calendar.get(Calendar.DATE)));

        return view;
    }


}
