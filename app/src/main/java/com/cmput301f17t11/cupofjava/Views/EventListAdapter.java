package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Controllers.EventFilteringHelper;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;

/**
 * Created by naz_t on 12/4/2017.
 */

public class EventListAdapter extends BaseAdapter {
    LayoutInflater inflater = null;
    Activity context;
    ArrayList<HabitEvent> habitEvents;

    public EventListAdapter(Activity context, ArrayList<HabitEvent> events){
        this.context = context;
        habitEvents = events;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return habitEvents.size();
    }

    @Override
    public HabitEvent getItem(int position){
        return habitEvents.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        view = (view == null) ? inflater.inflate(R.layout.fancy_event_list_view, null): view;

        TextView textViewHabitWhat = (TextView)view.findViewById(R.id.event_list_which_habit);
        TextView textViewHabitWhen = (TextView)view.findViewById(R.id.event_list_date);
        TextView textViewHabitComment = (TextView) view.findViewById(R.id.event_list_comment);
        ImageView imageView = (ImageView) view.findViewById(R.id.event_list_image_view);

        HabitEvent selectedHabit = habitEvents.get(position);

        textViewHabitWhat.setText(selectedHabit.getHabitTitle());
        textViewHabitWhen.setText(selectedHabit.getDateAsString());
        textViewHabitComment.setText(selectedHabit.getComment());

        if (selectedHabit.hasImage()){
            imageView.setImageBitmap(selectedHabit.getImage());
        }

        return view;
    }
}
