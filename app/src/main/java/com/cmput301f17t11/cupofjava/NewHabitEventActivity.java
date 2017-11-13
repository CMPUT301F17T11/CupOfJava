package com.cmput301f17t11.cupofjava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Time;
import java.util.ArrayList;
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
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private HabitAdapter habitAdapter;
    private String userName;
    private Spinner spinner;
    private int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit_event);

        ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this, R.layout.habit_list_item, habitList);

        //obtain extra info from intent
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        this.userIndex = intent.getIntExtra("userIndex", 0);

        spinner =  (Spinner) findViewById(R.id.choose_habit_spinner);
        habitAdapter = new HabitAdapter(this, habitList);
        spinner.setAdapter(habitAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SaveFileController saveFileController = new SaveFileController();
        ArrayList<Habit> habits = saveFileController
                .getHabitList(getApplicationContext(), userIndex).getTodaysHabitList();
        updateSpinner(habits);
    }
    private void updateSpinner(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(NewHabitEventActivity.this,
                R.layout.habit_list_item, habits);
        this.spinner.setAdapter(arrayAdapter);
    }
}