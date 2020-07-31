package com.hqdev.journaling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    Toolbar toolbar;
    TextView toolbar_title;
    RecyclerView recyclerView;
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
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

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("FAB","Clicked");
            }
        });
    }

}