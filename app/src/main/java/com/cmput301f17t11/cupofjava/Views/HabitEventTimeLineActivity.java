/* HabitEventTimeLineActivity
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
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.Geolocation;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.Models.User;
import com.cmput301f17t11.cupofjava.R;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;

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
    private Geolocation location;
    private double currentLat; //latitude of current loc
    private double currentLong; //Longitude of current loc


    ArrayList<HabitEvent> events = new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HabitEventTimeLineActivity.OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowersTab.
     */
    // TODO: Rename and change types and number of parameters
    public static HabitEventTimeLineActivity newInstance(String param1, String param2) {
        HabitEventTimeLineActivity fragment = new HabitEventTimeLineActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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



        //set up the TextView and ListView
        this.textView = (TextView) view.findViewById(R.id.timelineHeadingTextView);
        this.listView = (ListView) view.findViewById(R.id.timeLineListView);
        this.viewMap = (Button) view.findViewById(R.id.viewMapButton);

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
        updateListView(events);




        viewMap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog
                        .Builder(getActivity());

                builder.setTitle("View Habits Map For:")
                        .setPositiveButton("ALL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mapsAll(0);
                            }
                        })
                        .setNegativeButton("5K RADIUS", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mapsAll(1);
                                dialog.dismiss();
                            }
                        })
                        .setNeutralButton("Recent Friends", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
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
                    //HabitEvent myEvent = events.get(0);

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
                    //bundle.putSerializable("eventClicked", events); //sending habitEventlist
                    //bundle.putInt("eventIndex", position);

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
        ArrayAdapter<HabitEvent> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.habit_event_list_item, events);
        synchronized (listView){
            this.listView.setAdapter(arrayAdapter);
            this.listView.notify();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public void mapsAll(int type ){
        Log.i("List of event Loc", events.get(0).getLocation().toString());
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        Bundle bundle = new Bundle();

        double [] latititudes = new double [events.size()];
        double [] longitudes = new double [events.size()];
        for (int i = 0; i < events.size(); i++)
        {
            if(events.get(i).getIsLocationSet()) {
                latititudes[i] = events.get(i).getLocation().getLatitude();
                longitudes[i] = events.get(i).getLocation().getLongitude();
            }

        }
        int size = 0;
        for(int i = 0; i< events.size(); i++)
        {
            if(latititudes[i]!= 0.0 && longitudes[i]!= 0.0){
                size++;
            }
        }


        bundle.putDoubleArray("lat", latititudes);
        bundle.putDoubleArray("lon", longitudes);
        bundle.putInt("type", type );

        intent.putExtras(bundle);
        startActivity(intent);
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