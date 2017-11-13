package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * This activity shows the event that the user has currently started but not ended.
 * User can see some details of the event such as the habit attached to it,
 * the reason for the attached habit, the start date of the associative habit,
 * the photo attached to the habit event, and progress stats.
 *
 * User can top on the habit to swtich to any other habits associated with
 * the event.
 */


//TODO: get the habit list in spinner dropdown
public class ViewHabitEventActivity extends Activity {

    private TextView headingTextView;
    private TextView habitTitleTextView;
    private TextView habitDateBoxTextView;
    private TextView habitCommentTextView;
    private String userName;
    private int userIndex;
    //private int habitIndex;
    private int habitEventIndex;
    private Habit habit;
    /**
     * Launches Interface displaying the habit events and their
     * basic details.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_event);

        final Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        this.userIndex = intent.getIntExtra("userIndex", 0);
        this.habitEventIndex = intent.getIntExtra("habitEventIndex", 0);


        SaveFileController saveFileController = new SaveFileController();
        ArrayList<HabitEvent> allEvents = saveFileController.getAllHabitEvents(getApplicationContext(),
                this.userIndex);
        HabitEvent habitEvent = allEvents.get(this.habitEventIndex);
        headingTextView = (TextView) findViewById(R.id.habitEventDetailHeadingTextView);
        habitTitleTextView = (TextView)
    }






    //public void deleteEventButton(View view){}
}