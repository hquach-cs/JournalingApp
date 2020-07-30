package com.hqdev.journaling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        toolbar = findViewById(R.id.toolbar);
        ((TextView)findViewById(R.id.toolbar_title)).setText(calendarView.getTargetDate());
    }
}