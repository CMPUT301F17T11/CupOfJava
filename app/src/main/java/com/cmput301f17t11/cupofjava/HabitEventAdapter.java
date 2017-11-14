/* HabitEventAdapter
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
 * Adapter for HabitEvent class.
 *
 * @version 1.0
 */
public class HabitEventAdapter extends ArrayAdapter<HabitEvent> {

    /**
     * Constructor for HabitEventAdapter.
     *
     * @param context instance of Activity
     * @param counters array list of type HabitEvent
     * @see HabitEvent
     */
    public HabitEventAdapter(Activity context, ArrayList<HabitEvent> counters) {
        super(context, 0, counters);
    }

    /**
     * Checks if the existing view is being reused, otherwise inflates the view.
     *
     * @param position integer position
     * @param convertView instance of type View
     * @param parent instance of type ViewGroup
     * @return listItemView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.habit_event_list_item, parent, false);
        }

        final HabitEvent currentHabitEvent = getItem(position);

        return listItemView;


    }
}
