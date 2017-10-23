package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.os.Bundle;


/**
 * This activity handles adding and configuring an existent habit.
 * User must add add a name and reason for the habit.
 * User must choose the frequency & start date of the habit they are creating.
 */
public class NewHabitActivity extends Activity {

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
