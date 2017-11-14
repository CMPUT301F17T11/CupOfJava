/* HabitAdapter
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Adapter implemented for habits.
 *
 * @version 1.0
 */
public class HabitAdapter extends ArrayAdapter<Habit> {

    /**
     * Constructor for HabitAdapter class.
     *
     * @param context instance of Activity
     * @param counters instance of ArrayList of type Habit
     * @see Habit
     */
    public HabitAdapter(Activity context, ArrayList<Habit> counters) {
        super(context, 0, counters);
    }

    /**
     * Checks if the existing view is being reused, otherwise inflates the view.
     *
     * @param position takes integer position of view
     * @param convertView instance of View
     * @param parent instance of ViewGroup
     * @return listItemView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.habit_list_item, parent, false);
        }

        final Habit currentHabit = getItem(position);

        return listItemView;


    }
}
