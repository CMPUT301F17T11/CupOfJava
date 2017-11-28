/* AllHabitViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */
package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Opens the activity which displays all the habits the user has currently
 * created and saved. Also implements navigation bar functionality.
 *
 * @version 1.0
 */
public class AllHabitViewActivity extends Activity {
    private ListView listView;
    private TextView textView;
    private String userName;
    private ArrayList<Habit> habits;
    private int userIndex;

    public ListView getListView(){
        return listView;
    }

    /**
     * This method is called when AllHabitViewActivity is instantiated.
     * Implements bottom navigation menu to record which button is clicked on and
     * navigates to the appropriate activity.
     *
     * @param savedInstanceState the current saved state of the activity
     * @see TodayViewActivity
     * @see HabitEventTimeLineActivity
     * @see NewHabitActivity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habit_view);

        //obtain extra info from intent
        final Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        //this.userIndex = intent.getIntExtra("userIndex", 0);

        //handle bottom navigation bar
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_timeline:
                        Intent intent2 = new Intent(AllHabitViewActivity.this, HabitEventTimeLineActivity.class);
                        intent2.putExtra("userName", userName);
                        //intent2.putExtra("userIndex", userIndex);
                        startActivity(intent2);
                        break;
                    case R.id.action_today:
                        Intent intent3 = new Intent(AllHabitViewActivity.this, TodayViewActivity.class);
                        intent3.putExtra("userName", userName);
                        //intent3.putExtra("userIndex", userIndex);
                        startActivity(intent3);
                        break;
                    case R.id.action_all_habits:
                        break;
                    case R.id.add_habit_or_habit_event:

                        AlertDialog.Builder builder = new AlertDialog.Builder(AllHabitViewActivity.this);
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(AllHabitViewActivity.this, NewHabitActivity.class);
                                        intent4.putExtra("userName", userName);
                                        //intent4.putExtra("userIndex", userIndex);
                                        startActivity(intent4);
                                    }
                                })
                                .setPositiveButton("New Habit \n   Event", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Intent intent5 = new Intent(AllHabitViewActivity.this, NewHabitEventActivity.class);
                                        //intent5.putExtra("userName", userName);
                                        //intent5.putExtra("userIndex", userIndex);

                                        //startActivity(intent5);
                                    }
                                });


                        AlertDialog dialog = builder.create();
                        dialog.show();

                        break;
                    case R.id.action_friends:
                        Intent intent6 = new Intent(AllHabitViewActivity.this, FriendsActivity.class);
                        intent6.putExtra("userName", userName);
                        //intent6.putExtra("userIndex", userIndex);
                        startActivity(intent6);
                        break;
                }
                return false;
            }
        });

        //set up TextView and ListView
        this.textView = (TextView) findViewById(R.id.allHabitHeadingTextView);
        this.listView = (ListView) findViewById(R.id.allHabitListView);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*SaveFileController saveFileController = new SaveFileController();
                saveFileController.getHabit(getApplicationContext(), userIndex, position);*/

                Intent intent5 = new Intent(AllHabitViewActivity.this, HabitDetailViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putSerializable("habitClicked", habits); //sending all habits list
                bundle.putInt("habitIndex", position);
                //intent5.putExtra("userIndex", userIndex);
                intent5.putExtras(bundle);
                startActivity(intent5);
            }
        });
    }

    /**
     * This method is called when the activity is to be continued.
     *
     * @see SaveFileController
     */
    @Override
    protected void onResume(){
        super.onResume();
        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();
        getHabitsTask.execute(userName);
        try {
            habits = getHabitsTask.get();

            updateTextView(habits.size());
            updateListView(habits);
            Log.i("habits", habits.toString());


        } catch (Exception e) {
            Log.i("Error", "Failed to get the tweets from the async object");
        }
        /*SaveFileController saveFileController = new SaveFileController();
        ArrayList<Habit> habitList = saveFileController
                .getHabitListAsArray(getApplicationContext(), this.userIndex);*/
        //updateTextView(habitList.size());
        //updateListView(habitList);
    }

    /**
     * Updates the text view according to the number of habits a user currently has.
     *
     * @param habitCount the number of habits in the text view
     */
    private void updateTextView(int habitCount){
        if (habitCount == 0){
            this.textView.setText(("You do have not have any habits to track. Perhaps it's time to start a new habit?"));
        }
        else{
            this.textView.setText(("Here are all of your habits:"));
        }
    }

    /**
     * Updates the list view to display the list of habits.
     *
     * @param habits array list of habits to be displayed.
     * @see Habit
     */
    private void updateListView(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(AllHabitViewActivity.this,
                R.layout.habit_list_item, habits);
        this.listView.setAdapter(arrayAdapter);
    }
}
