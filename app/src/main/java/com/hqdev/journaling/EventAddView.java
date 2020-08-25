package com.hqdev.journaling;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class EventAddView extends RelativeLayout {
    EditText title;
    TextView startTime,endTime;
    Dialog myDialog;
    private int mHour, mMinute,AM;
    public EventAddView(Context context, AttributeSet attrs){
        super(context,attrs);
        myDialog = new Dialog(getContext());
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timeline_addevent_layout, this);
        findViewById(R.id.newevent_layout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return true;
            }
        });
        startTime = findViewById(R.id.newevent_time_start);
        startTime.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if(hourOfDay > 12){
                                    hourOfDay -= 12;
                                    AM = 1;
                                }else{
                                    if(hourOfDay == 0){
                                        hourOfDay = 12;
                                    }
                                    AM = 0;
                                }
                                startTime.setText(hourOfDay + ":" + ((minute > 9) ? minute : "0"+minute) + " " + ((AM == 1) ? "PM" : "AM"));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        endTime = findViewById(R.id.newevent_time_end);
        endTime.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if(hourOfDay > 12){
                                    hourOfDay -= 12;
                                    AM = 1;
                                }else{
                                    if(hourOfDay == 0){
                                        hourOfDay = 12;
                                    }
                                    AM = 0;
                                }
                                endTime.setText(hourOfDay + ":" + ((minute > 9) ? minute : "0"+minute) + " " + ((AM == 1) ? "PM" : "AM"));
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        clearFocus();
    }

}
