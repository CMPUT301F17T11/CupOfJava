package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Controllers.EventFilteringHelper;
import com.cmput301f17t11.cupofjava.Controllers.ProgressUpdate;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;

/**
 * Created by Gregory on 2017-12-03.
 */

public class todayViewAdapter extends BaseAdapter {

    LayoutInflater todayInflator = null;
    Activity context;
    ArrayList<Habit> habits;

    public todayViewAdapter(Activity context, ArrayList<Habit> habits) {
       this.context = context;
       this.habits = habits;
       todayInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return habits.size();
    }

    @Override
    public Habit getItem(int position) {
        return habits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        itemView = (itemView == null) ? todayInflator.inflate(R.layout.habit_list_item, null): itemView;

        // Cast Textviews
        TextView textViewHabitWhat = (TextView) itemView.findViewById(R.id.habit_what);
        TextView textViewHabitReason = (TextView) itemView.findViewById(R.id.habit_why);
        ProgressBar progressBar = (ProgressBar) itemView.findViewById(R.id.habit_list_progress_bar);

        Habit selectedHabit = habits.get(position);
        ArrayList<HabitEvent> events = EventFilteringHelper.getHabitEventsOfHabit(selectedHabit.getUsername(),
                selectedHabit);

        progressBar.setProgress(ProgressUpdate.getProgress(selectedHabit, events));


        textViewHabitWhat.setText(selectedHabit.getHabitTitle());
        textViewHabitReason.setText(selectedHabit.getHabitReason());
        return itemView;
    }
}
