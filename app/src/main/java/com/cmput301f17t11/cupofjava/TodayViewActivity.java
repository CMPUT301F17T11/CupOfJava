package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class TodayViewActivity extends Activity {

    private ArrayList<Habit> habitList = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_view);

        FloatingActionButton newActivity = (FloatingActionButton) findViewById(R.id.selfProfileViewNewHabit);
        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(TodayViewActivity.this).inflate(R.layout.activity_add_new_habit, null);

                final EditText editTitle = (EditText) view.findViewById(R.id.habit_title);
                final EditText editReason = (EditText) view.findViewById(R.id.habit_reason);

                // Create dialog to add counter
                AlertDialog.Builder builder = new AlertDialog.Builder(TodayViewActivity.this);
                builder.setMessage("Add Habit");
                builder.setView(view);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Check if counter Title and Reason are valid entries
                        if ( !(editTitle.getText().toString().equals("")) && !(editReason.getText().toString().equals("")) ) {
                            String title = editTitle.getText().toString();
                            String reason = editReason.getText().toString();
                            Date currentDate = new Date();


                            Habit newHabit = new Habit(title, reason, currentDate);




                            habitList.add(newHabit);
                            dialog.dismiss();
                        }

                        // Show error toast on invalid entry
                        else {
                            Toast.makeText(getApplicationContext(), "Make sure Title and Reason are not blank", Toast.LENGTH_SHORT).show();
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

    BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);



}
