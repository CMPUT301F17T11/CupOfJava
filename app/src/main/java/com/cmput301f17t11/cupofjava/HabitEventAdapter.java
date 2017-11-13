package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Sajjad on 2017-11-13.
 */

public class HabitEventAdapter extends ArrayAdapter<HabitEvent> {

    public HabitEventAdapter(Activity context, ArrayList<HabitEvent> habitEvents) {
        super(context, 0, habitEvents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.habit_event_list_item, parent, false);
        }

        final HabitEvent currentHabitEvent = getItem(position);

        return listItemView;


    }
}
