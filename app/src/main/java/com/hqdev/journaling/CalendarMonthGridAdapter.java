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

public class CalendarMonthGridAdapter extends ArrayAdapter<Date> {

    LayoutInflater inflater;
    List<Date> days;
    int month,year;
    DateClass targetDate;

    public CalendarMonthGridAdapter(Context context, List<Date> days,int month,int year){
        super(context,R.layout.calendar_layout,days);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.days = days;
        this.month = month;
        this.year = year;
        this.targetDate = new DateClass(Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH),Calendar.getInstance().get(Calendar.YEAR));
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
        int mDay = calendar.get(Calendar.DATE);
        int mMonth = calendar.get(Calendar.MONTH);
        int mYear = calendar.get(Calendar.YEAR);
        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.calendar_day_layout, parent, false);
        TextView textView = (TextView)view.findViewById(R.id.day_date);
        // clear styling
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(0);
        if (month != mMonth || year != mYear) {
            // if this day is outside current month, grey it out
            textView.setTextColor(Color.LTGRAY);
        }
        if (mDay == Calendar.getInstance().get(Calendar.DATE) && mMonth == Calendar.getInstance().get(Calendar.MONTH) && mYear == Calendar.getInstance().get(Calendar.YEAR)  ) {
            // if it is today, set it to blue/bold
            textView.setTextColor(getContext().getResources().getColor(R.color.colorSecondaryLight));
        }
        if(mDay == targetDate.day && mMonth == targetDate.month && mYear == targetDate.year){
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.ic_checkbox_blank_circle);
        }
        textView.setText(String.valueOf(calendar.get(Calendar.DATE)));

        return view;
    }


}
