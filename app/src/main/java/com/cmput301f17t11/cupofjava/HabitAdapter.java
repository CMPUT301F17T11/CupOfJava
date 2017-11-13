package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Sajjad on 2017-11-12.
 */

public class HabitAdapter extends ArrayAdapter<Habit> {

    public HabitAdapter(Activity context, ArrayList<Habit> counters) {
        super(context, 0, counters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.habit_list_item, parent, false);
        }

        final Habit currentHabit = getItem(position);

        return listItemView;


    }
}
