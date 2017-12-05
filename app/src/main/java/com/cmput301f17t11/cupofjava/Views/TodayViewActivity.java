/* TodayViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Views;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Controllers.SaveFileController;
import com.cmput301f17t11.cupofjava.Models.BottomNavigationViewHelper;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitAdapter;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Opens to a view of the list of habits that the user has to complete today.
 *
 * @version 1.0
 */
public class TodayViewActivity extends Fragment {
    private ListView listView;
    private ArrayList<Habit> habitList = new ArrayList<>();
    private ArrayList<Habit> habits;
    private String userName = "";
    private TextView textView;
    private todayViewAdapter habitAdapter;

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
            this.userName = bundle.getString("userName");
            Log.i("TodayViewActivity: Username received: ", userName);

        }

        this.textView = (TextView) view.findViewById(R.id.TodaysHabitsHeadingTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(getActivity(),
                        HabitDetailViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putSerializable("habitClicked", habits);
                bundle.putInt("habitIndex", position);
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

        //retrieving all habits of the user from elasticsearch
        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();
        getHabitsTask.execute(userName);
        try {
            habits = getTodaysHabitList(getHabitsTask.get());
            Log.i("TodayViewActivity: habit_list is : ", habits.toString());


        } catch (Exception e) {
            Log.i("Error Getting Habits ", e.toString());
        }

        updateTextView(habits.size());
        updateListView(habits);
    }

    /**
     * Updates the text view which shows the habitCount.
     *
     * @param habitCount integer value which shows how many habits the user has
     *                   lined up for the day
     */
    private void updateTextView(int habitCount){
        if (habitCount == 0){
            this.textView.setText((getResources().getString(R.string.no_habits_today) + " " + this.userName));
        }
        else{
            this.textView.setText(getResources().getString(R.string.habits_today));
        }
    }

    /**
     * Updates the list view which displays the habits.
     *
     * @param habits array list of type Habit
     * @see Habit
     */
    private void updateListView(ArrayList<Habit> habits){
        habitAdapter = new todayViewAdapter(getActivity(), habits);

        synchronized (listView) {
            this.listView.setAdapter(habitAdapter);
            this.listView.notify();
        }
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

    public ListView getListView(){
        return listView;
    }
}