package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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

    ArrayList<HabitEvent> habitEvents;

    /**
     * Launches Interface displaying the habit events and their
     * basic details.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_event);

        FloatingActionButton newActivity = (FloatingActionButton) findViewById(R.id.eventAdder);
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(ViewHabitEventActivity.this).inflate(R.layout.activity_new_habit_event, null);

                final EditText editComment = (EditText) view.findViewById(R.id.edit_comment);

                // Create dialog to add counter
                AlertDialog.Builder builder = new AlertDialog.Builder(ViewHabitEventActivity.this);
                builder.setMessage("Add Habit Event");
                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Check if counter Title and Reason are valid entires
                        if (!(editComment.getText().toString().equals("")) ) {
                            String comment = editComment.getText().toString();
                            Habit habit = new Habit("ok","ok",new Date()); //remove this; testing


                            HabitEvent myObject = new HabitEvent(habit, comment);
                            habitEvents.add(myObject);
                            dialog.dismiss();
                        }

                        // Show error toast on invalid entry
                        else {
                            Toast.makeText(getApplicationContext(), "Make sure Habit and Comment are not blank", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.setCancelable(false);

                AlertDialog alert = builder.create();

                alert.show();
            }
        });

    }


}