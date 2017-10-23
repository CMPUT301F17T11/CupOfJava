package com.cmput301f17t11.cupofjava;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.sql.Time;
import java.util.Calendar;

/**
 * This activity allows user to add a new habit event, and
 * add habit that theyde like to track under this event.
 * User can incorporate date, location, photo.
 */

public class NewHabitEventActivity extends AppCompatActivity {

    private Calendar date;
    private EditText comment;
    private Bitmap photo;
    private Time time;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit_event);
    }
}