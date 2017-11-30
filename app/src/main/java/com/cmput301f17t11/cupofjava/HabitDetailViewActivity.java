/* HabitDetailViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Opens activity which lets user view the details of a certain habit.
 *
 * @see Habit
 * @version 1.0
 */
public class HabitDetailViewActivity extends AppCompatActivity {

    private String habitDateCalendar;
    private ListView habitEventList;
    private String userName;
    private User user;
    private int userIndex;
    private int habitIndex;
    private ArrayList<Habit> habitList = new ArrayList<>();
    private Habit habit;

    /**
     * This method is called when HabitDetailViewActivity is instantiated.
     *
     * @param savedInstanceState the current saved state of the activity
     * @see SaveFileController
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail_view);

        //final Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.user = (User)bundle.getSerializable("user");
            this.userName = bundle.getString("userName");

            this.habitList = (ArrayList<Habit>) bundle.getSerializable("habitClicked");
            //this.userIndex = intent.getIntExtra("userIndex", 0);
            this.habitIndex = bundle.getInt("habitIndex");
        }

        TextView habitTitleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView habitReasonTextView = (TextView) findViewById(R.id.reason_text_view);
        TextView habitDateTextView = (TextView) findViewById(R.id.date_added_text_view);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

        //SaveFileController saveFileController = new SaveFileController();
        this.habit = this.habitList.get(habitIndex);

        habitTitleTextView.setText(("What: "+ habit.getHabitTitle()));
        habitReasonTextView.setText(("Why: "+ habit.getHabitReason()));
        habitDateTextView.setText(("Start date: " + sdf.format(habit.getHabitStartDate().getTime())));

    }

    /**
     * Opens the new habit event activity page so that the user
     * can create new habit event.
     *
     * @param view instance of View
     * @see NewHabitEventActivity
     */
    public void addNewHabitEventButton(View view) {
        //todo: new habit event
        Intent intent2 = new Intent(HabitDetailViewActivity.this, NewHabitEventActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putSerializable("habitList", habitList);
        bundle.putInt("habitIndex", habitIndex);
        //intent2.putExtra("userName", userName);
        //intent2.putExtra("userIndex", userIndex);
        //intent2.putExtra("habitIndex", habitIndex);
        intent2.putExtras(bundle);
        startActivity(intent2);
    }

    /**
     * Button implementation to delete current habit.
     *
     * @param view instance of View
     * @see TodayViewActivity
     */
    public void deleteCurrentHabitButton(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(HabitDetailViewActivity.this);
        builder.setTitle("Edit record")
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HabitList retrieveHabitList = user.getHabitList();
                        if(retrieveHabitList.habitExists(habit)){
                            retrieveHabitList.deleteHabit(habit);
                        }
                        user.setHabitList(retrieveHabitList);  //setting the new updated habit list
                        ElasticsearchController.DeleteUsersTask deleteUsersTask = new ElasticsearchController.DeleteUsersTask();
                        deleteUsersTask.execute(user);

                        ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
                        getUserTask.execute(user.getUsername());
                        try {
                            User deletedUser = getUserTask.get();
                            if (deletedUser == null)
                            {
                                Log.i("GetUserTask ", "user does not exist ");

                            }
                            else {
                                Log.i("GetUserTask ", deletedUser.getUsername()+"user still exists ");

                            }
                        }catch (Exception e){

                        }
                        ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
                        addUserTask.execute(user);
                        //TODO wont be needing this
                        ElasticsearchController.DeleteHabitsTask deleteHabitsTask = new ElasticsearchController.DeleteHabitsTask();
                        deleteHabitsTask.execute(habit);
                        //TODO need to delete habit events associated with the habit as well
                        //finish();
                        //SaveFileController saveFileController = new SaveFileController();
                        //saveFileController.deleteHabit(getApplicationContext(), userIndex, habitIndex);
                        Intent intent3 = new Intent(HabitDetailViewActivity.this, MainActivity.class);
                        intent3.putExtra("user", user);
                        //intent3.putExtra("userIndex", userIndex);
                        startActivity(intent3);
                    }
                })
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
