package com.hqdev.journaling;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventCardView extends RelativeLayout {

    TextView title,time,desc;
    RelativeLayout backgroundContainer;
    GestureDetector gestureDetector;
    boolean extended;

    public EventCardView(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.timeline_event_card_layout, this);
        backgroundContainer = findViewById(R.id.timeline_card);
        title = findViewById(R.id.timeline_card_title);
        time = findViewById(R.id.timeline_card_time);
        desc = findViewById(R.id.timeline_card_desc);
        gestureDetector = new GestureDetector(context, new GestureListener());
        extended = false;
    }


    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            backgroundContainer.getLayoutParams().height = extended ? 100 : 150;
            backgroundContainer.requestLayout();
            extended = !extended;
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return gestureDetector.onTouchEvent(e);
    }

    public void updateText(TimelineEventClass e){
        title.setText(e.Title);
        desc.setText(e.Description);
        time.setText(e.getEventTime());

    }
}
