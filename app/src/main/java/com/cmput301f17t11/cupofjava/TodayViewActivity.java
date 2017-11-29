/* TodayViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Opens to a view of the list of habits that the user has to complete today.
 *
 * @version 1.0
 */
public class TodayViewActivity extends Fragment {


    // ListView to be populated
    private ListView listView;

    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private ArrayList<Habit> habits;
    private String userName = "";
    private User user;
    //private int userIndex;
    private TextView textView;

    public ListView getListView(){
        return listView;
    }

    /**
     * This method is called when TodayViewActivity is instantiated.
     * Implements bottom navigation menu to record which button is clicked on and
     * navigates to the appropriate activity.
     *
     * @param savedInstanceState the current saved state of the activity
     * @see AllHabitViewActivity
     * @see HabitEventTimeLineActivity
     * @see NewHabitActivity
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_today_view, container, false);
        listView = (ListView) view.findViewById(R.id.selfProfileHabitListView);
        HabitAdapter habitAdapter = new HabitAdapter(getActivity(), habitList);
        listView.setAdapter(habitAdapter);

        //obtain extra info from intent
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.user = (User) bundle.getSerializable("user");
            this.userName = user.getUsername();
        }
        //this.userName = bundle.getString("userName");
        Log.i("username in TodayView", userName);
        //this.userIndex = intent.getIntExtra("userIndex", 0);

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user", user);
                        //bundle.putString("userName", userName);
                        HabitEventTimeLineActivity fragment = new HabitEventTimeLineActivity();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(null);
                        fragmentTransaction.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.action_today:
                        break;
                    case R.id.action_all_habits:
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("userName", userName);
                        AllHabitViewActivity fragment4 = new AllHabitViewActivity();
                        fragment4.setArguments(bundle4);
                        FragmentManager fragmentManager4 = getFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4).addToBackStack(null);
                        fragmentTransaction4.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.add_habit_or_habit_event:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(getActivity(), NewHabitActivity.class);
                                        intent4.putExtra("userName", userName);
                                        //intent4.putExtra("userIndex", userIndex);
                                        startActivity(intent4);
                                    }
                                })
                                .setPositiveButton("New Habit \n   Event", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent5 = new Intent(getActivity(), NewHabitEventActivity.class);
                                        intent5.putExtra("userName", userName);
                                        //.putExtra("userIndex", userIndex);

                                        startActivity(intent5);
                                    }
                                });


                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.action_friends:
                        Intent intent5 = new Intent(getActivity(), FriendsActivity.class);
                        intent5.putExtra("userName", userName);
                        //intent4.putExtra("userIndex", userIndex);
                        startActivity(intent5);
                        break;
                }
                return false;
            }
        });

        //set up the TextView and ListView
        this.textView = (TextView) view.findViewById(R.id.SelfProfileHeadingTextView);
        //this.listView = (ListView) findViewById(R.id.selfProfileHabitListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(getActivity(),
                        HabitDetailViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putSerializable("habitClicked", habits); //sending today's habitlist
                bundle.putInt("habitIndex", position);

                //intent5.putExtra("userIndex", userIndex);
                intent5.putExtras(bundle);

                startActivity(intent5);
            }
        });
        return view;

    }

    /**
     * This method is called when the activity is to be continued.
     *
     * @see SaveFileController
     */
    @Override
    public void onResume(){
        super.onResume();


        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();

        //String query = "{\"query\":{\"term\":{\"username\":"+userName+"}}}";

        getHabitsTask.execute(userName);
        try {
            habits = getTodaysHabitList(getHabitsTask.get());

            updateTextView(habits.size());
            updateListView(habits);
            Log.i("today habits", habits.toString());


        } catch (Exception e) {
            Log.i("Error", "Failed to get the Habits from the async object");
        }

        //SaveFileController saveFileController = new SaveFileController();
        //ArrayList<Habit> habits = saveFileController
        //.getHabitList(getApplicationContext(), userIndex).getTodaysHabitList();
        // updateTextView(habits.size());
        //updateListView(habits);



    }

    /**
     * Updates the text view which shows the habitCount.
     *
     * @param habitCount integer value which shows how many habits the user has
     *                   lined up for the day
     */
    private void updateTextView(int habitCount){
        if (habitCount == 0){
            this.textView.setText(("You do have not not have anything for today." + this.userName));
        }
        else{
            this.textView.setText(("Here are the habits you should carry out today:"));
        }
    }

    /**
     * Updates the list view which displays the habits.
     *
     * @param habits array list of type Habit
     * @see Habit
     */
    private void updateListView(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.habit_list_item, habits);
        this.listView.setAdapter(arrayAdapter);
        this.listView.notify();
    }

    public ArrayList<Habit> getTodaysHabitList(ArrayList<Habit> habits) {
        Calendar calendar = Calendar.getInstance();
        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDay;
        switch (dayOfTheWeek) {
            case Calendar.SUNDAY:
                currentDay = 0;
                break;
            case Calendar.MONDAY:
                currentDay = 1;
                break;
            case Calendar.TUESDAY:
                currentDay = 2;
                break;
            case Calendar.WEDNESDAY:
                currentDay = 3;
                break;
            case Calendar.THURSDAY:
                currentDay = 4;
                break;
            case Calendar.FRIDAY:
                currentDay = 5;
                break;
            case Calendar.SATURDAY:
                currentDay = 6;
                break;
            default:
                currentDay = 0;
                break;
        }

        ArrayList<Habit> todaysHabits = new ArrayList<>();
        Habit currentHabit;

        for (int i = 0; i < habits.size(); i++) {
            currentHabit = habits.get(i);
            if (currentHabit.onDay(currentDay)) {
                todaysHabits.add(currentHabit);
            }
        }

        return todaysHabits;
    }
}