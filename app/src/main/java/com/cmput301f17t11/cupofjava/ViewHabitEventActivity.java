package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.os.Bundle;

/**
 * This activity shows the event that the user has currently started but not ended.
 * User can see some details of the event such as the habit attached to it,
 * the reason for the attached habit, the start date of the associative habit,
 * the photo attached to the habit event, and progress stats.
 *
 * User can top on the habit to swtich to any other habits associated with
 * the event.
 */
public class ViewHabitEventActivity extends Activity {

    /**
     * Launches Interface displaying the habit events and their
     * basic details.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_event);
    }
}