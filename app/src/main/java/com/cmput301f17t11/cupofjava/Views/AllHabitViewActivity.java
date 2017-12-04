/* AllHabitViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */
package com.cmput301f17t11.cupofjava.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.BottomNavigationViewHelper;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Controllers.SaveFileController;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;

/**
 * Opens the activity which displays all the habits the user has currently
 * created and saved. Also implements navigation bar functionality.
 *
 * @version 1.0
 */
public class AllHabitViewActivity extends Fragment {
    private ListView listView;
    private TextView textView;
    private String userName;
    private ArrayList<Habit> habits;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_all_habit_view, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            this.userName = bundle.getString("userName");
            Log.i("AllHabitViewActivity: Username received: ", userName);
        }

        //set up TextView and ListView
        this.textView = (TextView) view.findViewById(R.id.allHabitHeadingTextView);
        this.listView = (ListView) view.findViewById(R.id.allHabitListView);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent5 = new Intent(getActivity(), HabitDetailViewActivity.class);
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
            habits = getHabitsTask.get();
            updateTextView(habits.size());
            updateListView(habits);
            Log.i("AllHabitViewActivity: habit_list is : ", habits.toString());


        } catch (Exception e) {
            Log.i("Error Getting Habits ", e.toString());
        }
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
        todayViewAdapter adapter = new todayViewAdapter(getActivity(), habits);
        synchronized (listView){
            this.listView.setAdapter(adapter);
            this.listView.notify();
        }

    }
}
