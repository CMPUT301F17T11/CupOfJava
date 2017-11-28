/* HabitEventTimeLineActivity
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Opens the activity which shows the timeline of habit events.
 *
 * @version 1.0
 */
public class HabitEventTimeLineActivity extends Activity {
    private String userName;
    private int userIndex;
    private ListView listView;
    private TextView textView;
    ArrayList<HabitEvent> events = new ArrayList<>();
    //private HabitEventAdapter habitEventAdapter;
    //private ArrayList<HabitEvent> eventArrayList = new ArrayList<>();

    /**
     * This method is called when HabitEventTimeLineActivity is instantiated.
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
        setContentView(R.layout.activity_habit_time_line);
        //habitEventAdapter = new HabitEventAdapter(this, eventArrayList);
        //listView.setAdapter(habitEventAdapter);

        //obtain extra info from intent
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        //this.userIndex = intent.getIntExtra("userIndex", 0);

        //handle the bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        break;
                    case R.id.action_today:
                        Intent intent2 = new Intent(HabitEventTimeLineActivity.this, TodayViewActivity.class);
                        intent2.putExtra("userName", userName);
                        //intent2.putExtra("userIndex", userIndex);
                        startActivity(intent2);
                        break;
                    case R.id.action_all_habits:
                        Intent intent3 = new Intent(HabitEventTimeLineActivity.this, AllHabitViewActivity.class);
                        intent3.putExtra("userName", userName);
                        //intent3.putExtra("userIndex", userIndex);
                        startActivity(intent3);
                        break;
                    case R.id.add_habit_or_habit_event:
                        AlertDialog.Builder builder = new AlertDialog.Builder(HabitEventTimeLineActivity.this);
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(HabitEventTimeLineActivity.this, NewHabitActivity.class);
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
                        Intent intent5 = new Intent(HabitEventTimeLineActivity.this, FriendsActivity.class);
                        intent5.putExtra("userName", userName);
                        //intent4.putExtra("userIndex", userIndex);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });

        //set up the TextView and ListView
        this.textView = (TextView) findViewById(R.id.timelineHeadingTextView);
        this.listView = (ListView) findViewById(R.id.timeLineListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(HabitEventTimeLineActivity.this,
                        ViewHabitEventActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putSerializable("eventClicked", events); //sending habitEventlist
                bundle.putInt("eventIndex", position);

                intent5.putExtras(bundle);
                //intent5.putExtra("userName", userName);
                //intent5.putExtra("userIndex", userIndex);
                //intent5.putExtra("habitEventIndex", position);
                startActivity(intent5);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        //SaveFileController saveFileController = new SaveFileController();
        //ArrayList<HabitEvent> events = saveFileController.getAllHabitEvents(getApplicationContext(), this.userIndex);

        ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
        getEventsTask.execute(userName);
        try {
            events = getEventsTask.get();

            updateTextView(events.size());
            updateListView(events);
            Log.i("Events", events.toString());


        } catch (Exception e) {
            Log.i("Error", "Failed to get the Habit Events from the async object");
        }


        //updateTextView(events.size());
        //updateListView(events);

    }

    /**
     * Updates text view.
     *
     * @param eventCount integer
     */
    private void updateTextView(int eventCount){
        if (eventCount == 0){
            this.textView.setText(("You did not do any habits yet!"));
        }
        else{
            this.textView.setText(("Your habit event timeline:"));
        }
    }

    /**
     * Updates list view.
     *
     * @param events Arraylist of type HabitEvent
     */
    private void updateListView(ArrayList<HabitEvent> events){
        ArrayAdapter<HabitEvent> arrayAdapter = new ArrayAdapter<>(HabitEventTimeLineActivity.this,
                R.layout.habit_event_list_item, events);
        this.listView.setAdapter(arrayAdapter);
        this.listView.notify();
    }
}
