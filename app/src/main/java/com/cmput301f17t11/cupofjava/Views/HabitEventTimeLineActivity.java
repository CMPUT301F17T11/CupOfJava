/* HabitEventTimeLineActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */


package com.cmput301f17t11.cupofjava.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Controllers.EventFilteringHelper;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitAdapter;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 * Opens the activity which shows the timeline of habit events.
 *
 * @version 1.0
 */
public class HabitEventTimeLineActivity extends Fragment {
    private String userName;
    private ListView listView;
    private TextView textView;
    private Button viewMap;

    ArrayList<HabitEvent> events = new ArrayList<>();


    private HabitEventTimeLineActivity.OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.userName = getArguments().getString("userName");
        }
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_habit_time_line, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.userName = bundle.getString("userName");

            Log.i("HabitEventTimelineFragment: Username received: ", userName);

        }

        this.textView = (TextView) view.findViewById(R.id.timelineHeadingTextView);
        this.listView = (ListView) view.findViewById(R.id.timeLineListView);
        this.viewMap = (Button) view.findViewById(R.id.viewMapButton);

        Button reverseChronoButton = (Button) view.findViewById(R.id.reverse_chronological_button);
        reverseChronoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                events = new ArrayList<>();
                ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
                getEventsTask.execute(userName);
                try {
                    ArrayList<HabitEvent> foundHabitEvents = getEventsTask.get();
                    if (!foundHabitEvents.isEmpty()) {

                        events.addAll(foundHabitEvents);
                        Log.i("HabitEventTimeline: found events :", events.toString());
                    } else {
                        Log.i("HabitEventTimeline", "Did Not find habit events" + events.toString());

                    }
                } catch (Exception e) {
                    Log.i("HabitEventTimeline", "Failed to get the Habit Events from the async object");

                }
                updateTextView(events.size());
                updateListView(events);
            }
        });

                Button commentButton = (Button) view.findViewById(R.id.filter_by_comment);


        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Search events by comment").setMessage("Enter comment:");

                final EditText input = new EditText(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(layoutParams);
                builder.setView(input);

                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String comment = input.getText().toString();
                        if (comment.length() == 0){
                            String text = "Error: Cannot search for empty string!";
                            int length = Toast.LENGTH_SHORT;
                            Context context = getContext();
                            Toast toast = Toast.makeText(context,text, length);
                            toast.show();
                            dialog.dismiss();
                        }

                        events = EventFilteringHelper.filterByComment(events, comment);
                        events = EventFilteringHelper.reverseChronological(events);
                        updateTextView(events.size());
                        updateListView(events);

                    }
                })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });


                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        ArrayList<Habit> habits;
        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();
        getHabitsTask.execute(userName);
        try {
            habits = getHabitsTask.get();
        } catch (Exception e) {
            Log.i("Error Getting Habits ", e.toString());
            habits = new ArrayList<>();
        }
        final Spinner spinner = (Spinner) view.findViewById(R.id.filter_by_habit);
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<Habit>(getActivity(),
                R.layout.string_only_list_item);

        final Habit emptyHabit = new Habit("Select", "to filter by habit");
        arrayAdapter.add(emptyHabit);
        arrayAdapter.addAll(habits);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Habit selectedHabit = (Habit) spinner.getSelectedItem();
                if (selectedHabit.getHabitTitle().equals(emptyHabit.getHabitTitle())){
                    return;
                }
                String habitTitle = selectedHabit.getHabitTitle();
                events = new ArrayList<>();
                ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
                getEventsTask.execute(userName);
                try {
                    ArrayList<HabitEvent> foundHabitEvents = getEventsTask.get();
                    if (!foundHabitEvents.isEmpty()) {

                        events.addAll(foundHabitEvents);
                        Log.i("HabitEventTimeline: found events :", events.toString());
                    } else {
                        Log.i("HabitEventTimeline", "Did Not find habit events" + events.toString());

                    }
                } catch (Exception e) {
                    Log.i("HabitEventTimeline", "Failed to get the Habit Events from the async object");

                }

                events = EventFilteringHelper.filterByType(events, habitTitle);
                events = EventFilteringHelper.reverseChronological(events);
                updateTextView(events.size());
                updateListView(events);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
        getEventsTask.execute(this.userName);
        try {
            ArrayList<HabitEvent> foundHabitEvents = getEventsTask.get();
            if (!foundHabitEvents.isEmpty()) {

                events.addAll(foundHabitEvents);
                Log.i("HabitEventTimeline: found events :", events.toString());
            } else {
                Log.i("HabitEventTimeline", "Did Not find habit events" + events.toString());

            }
        } catch (Exception e) {
            Log.i("HabitEventTimeline", "Failed to get the Habit Events from the async object");

        }

        updateTextView(events.size());
        events = EventFilteringHelper.reverseChronological(events);
        updateListView(events);




        viewMap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final CharSequence mapsOptions[] = new CharSequence[] {"ALL"};

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog
                        .Builder(getActivity());

                builder.setTitle("View Events Map For:")
                        .setItems(mapsOptions, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        mapsAll(0);
                                        break;
                                    /*case 1:
                                        mapsAll(1);
                                        break;
                                    case 2:
                                        dialog.dismiss();
                                        break;
                                    case 3:
                                        dialog.dismiss();
                                        break;*/
                                }
                            }
                        });
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(getActivity(),
                        ViewHabitEventActivity.class);
                String a = events.size()+" "+ position ;

                Log.i("HABitEventList", a);
                if(!events.isEmpty()) {
                    Log.i ("in here", "whats wrong?");
                    HabitEvent myEvent = events.get(position);


                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    bundle.putString("habitTitle", myEvent.getHabitTitle());
                    bundle.putString("eventDate", myEvent.getDateAsString());
                    bundle.putString("eventComment", myEvent.getComment());
                    if(myEvent.getIsLocationSet()){
                        String lat = String.format("%.2f", myEvent.getLocation().getLatitude());
                        String longitude = String.format("%.2f",myEvent.getLocation().getLongitude());
                        String format = " LAT: " + lat+ " LONG: " + longitude;
                        bundle.putString("eventLocation", format);
                    }
                    else {
                        bundle.putString("eventLocation", "Location not Set");
                    }

                    if (myEvent.hasImage()) {
                        bundle.putParcelable("eventImage", myEvent.getImage());
                    }
                    bundle.putBoolean("hasImage", myEvent.hasImage());
                    bundle.putString("eventId", myEvent.getId());

                    intent5.putExtras(bundle);
                    startActivity(intent5);
                }
            }
        });
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
        EventListAdapter adapter = new EventListAdapter(getActivity(), events);
        synchronized (listView){
            this.listView.setAdapter(adapter);
            this.listView.notify();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public void mapsAll(int type ){

        if(events.isEmpty()){
            Toast.makeText(getContext(), "No Events to view", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            //Bundle bundle = new Bundle();

            //double [] latititudes = new double [events.size()];
            //double [] longitudes = new double [events.size()];
            ArrayList<Double> latititudes = new ArrayList<>();
            ArrayList<Double> longitudes = new ArrayList<>();
            ArrayList<String> markerTitles = new ArrayList<>();
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getIsLocationSet()) {
                    latititudes.add(events.get(i).getLocation().getLatitude());
                    longitudes.add(events.get(i).getLocation().getLongitude());
                    markerTitles.add(events.get(i).getHabitTitle());
                }

            }



            //intent.putExtras(bundle);

            intent.putExtra("lat", latititudes);
            intent.putExtra("lon", longitudes);
            intent.putExtra("markerTitles", markerTitles);
            intent.putExtra("type", type);
            startActivity(intent);

            NearbyTab nearbyTab = new NearbyTab();
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("lat", latititudes);
            bundle2.putSerializable("lon", longitudes);
            bundle2.putInt("type", type );
            bundle2.putStringArrayList("markerTitles", markerTitles);
            nearbyTab.setArguments(bundle2);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}