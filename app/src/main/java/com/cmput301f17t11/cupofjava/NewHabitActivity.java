package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * This activity handles adding and configuring an existent habit.
 * User must add add a name and reason for the habit.
 * User must choose the frequency & start date of the habit they are creating.
 */
public class NewHabitActivity extends Activity {

    private EditText habitTitle;
    private EditText habitReason;
    private Calendar Date;
    private Button sunday;
    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;
    private Button saturday;

    /**
     * Launches the screen to add/edit a habit.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

    }
}
