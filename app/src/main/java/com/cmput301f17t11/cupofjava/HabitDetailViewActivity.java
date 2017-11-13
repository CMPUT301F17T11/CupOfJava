package com.cmput301f17t11.cupofjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class HabitDetailViewActivity extends AppCompatActivity {

    private TextView habitTitleTextView;
    private TextView habitReasonTextView;
    private Calendar habitdateCalendar;
    private String habitDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail_view);


    }
}
