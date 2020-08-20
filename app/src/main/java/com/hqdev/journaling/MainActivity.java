package com.hqdev.journaling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TimelineView timelineView;
    Toolbar toolbar;
    TextView toolbar_title,toolbar_today;
    RecyclerView recyclerView;
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    ImageButton notification_bell;
    TextView notification_number;
    CardView notification_number_container;
    int notification_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        timelineView = findViewById(R.id.timelineView);
        notification_bell = findViewById(R.id.toolbar_bell_icon);
        notification_number = findViewById(R.id.toolbar_bell_number_text);
        notification_num = 0;
        notification_number_container = findViewById(R.id.toolbar_bell_number_container);
        if(notification_num == 0)
            notification_number_container.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText(monthNames[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR));
        recyclerView = calendarView.getRecyclerView();
        recyclerView.addOnScrollListener ( new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollHorizontally(1)) {
                    calendarView.getNextYearMonth();
                }else if (!recyclerView.canScrollHorizontally(-1)) {
                    calendarView.getPrevYearMonth();
                }
                int position = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    //Dragging
                    toolbar_title.setText(calendarView.getMonth(position));
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    toolbar_title.setText(calendarView.getMonth(position));
                }
            }
        });
        toolbar_today = findViewById(R.id.toolbar_today);
        toolbar_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.returnToday();
                toolbar_title.setText(monthNames[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR));
                timelineView.getCurrentTimelinePos();
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timelineView.addEvent(new TimelineEventClass("Food Shopping","Kroger on ....",new DateClass("12:00",false),new DateClass("1:00",false)));
            }
        });
        notification_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification_num++;
                notification_number_container.setVisibility(View.VISIBLE);
                notification_number.setText(""+notification_num);
            }});
    }

}