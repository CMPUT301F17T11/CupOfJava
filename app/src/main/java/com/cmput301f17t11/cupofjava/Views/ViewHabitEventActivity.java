/* ViewHabitEventActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;


/**
 * This activity shows the event that the user has currently started but not ended.
 * User can see some details of the event such as the habit attached to it,
 * the reason for the attached habit, the start date of the associative habit,
 * the photo attached to the habit event, and progress stats.
 *
 * User can top on the habit to swtich to any other habits associated with
 * the event.
 *
 * @version 1.0
 */
//TODO: get the habit list in spinner dropdown
public class ViewHabitEventActivity extends Activity {

    private TextView headingTextView;
    private TextView habitTitleTextView;
    private TextView habitDateBoxTextView;
    private TextView habitCommentTextView;
    private String userName;
    private ArrayList<HabitEvent> allEvents = new ArrayList<>();
    private int habitEventIndex;

    /**
     * Launches Interface displaying the habit events and their basic details.
     *
     * @param savedInstanceState saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_event);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.userName = bundle.getString("userName");
            this.allEvents = (ArrayList<HabitEvent>) bundle.getSerializable("eventClicked");
            this.habitEventIndex = bundle.getInt("eventIndex");

        }

        HabitEvent habitEvent = allEvents.get(this.habitEventIndex);
        headingTextView = (TextView) findViewById(R.id.habit_event_detail_username);
        habitTitleTextView = (TextView) findViewById(R.id.habit_event_detail_name);
        habitDateBoxTextView = (TextView) findViewById(R.id.habit_event_detail_date);
        habitCommentTextView = (TextView) findViewById(R.id.habit_event_detail_comment);

        headingTextView.setText((this.userName));
        habitTitleTextView.setText((habitEvent.getHabit().getHabitTitle()));
        habitDateBoxTextView.setText((habitEvent.getDateAsString()));
        habitCommentTextView.setText((habitEvent.getComment()));

    }

}






    //public void deleteEventButton(View view){}