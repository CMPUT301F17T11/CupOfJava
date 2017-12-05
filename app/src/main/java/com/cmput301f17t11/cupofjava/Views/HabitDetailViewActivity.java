/* HabitDetailViewActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cmput301f17t11.cupofjava.Controllers.EditHabitDetailController;
import com.cmput301f17t11.cupofjava.Controllers.EditHabitEventDetailController;
import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Controllers.ProgressUpdate;
import com.cmput301f17t11.cupofjava.Controllers.SocialRequestHandler;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.Models.HabitList;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Controllers.SaveFileController;
import com.cmput301f17t11.cupofjava.Models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Opens activity which lets user view the details of a certain habit.
 *
 * @see Habit
 * @version 1.0
 */
public class HabitDetailViewActivity extends Activity {

    private String habitDateCalendar;
    private ListView listView;
    private String userName;
    private User user;
    private int userIndex;
    private int habitIndex;
    private ArrayList<Habit> habitList = new ArrayList<>();
    private Habit habit;
    private ProgressBar progressBar;
    private int progress = 0;
    private Calendar newDate;
    private TextView habitDateTextView;

    /**
     * This method is called when HabitDetailViewActivity is instantiated.
     *
     * @param savedInstanceState the current saved state of the activity
     * @see SaveFileController
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail_view);

        //final Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.user = (User)bundle.getSerializable("user");
            this.userName = bundle.getString("userName");

            this.habitList = (ArrayList<Habit>) bundle.getSerializable("habitClicked");
            this.habitIndex = bundle.getInt("habitIndex");
        }

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        TextView habitTitleTextView = (TextView) findViewById(R.id.title_text_view);
        final TextView habitReasonTextView = (TextView) findViewById(R.id.reason_text_view);
        habitDateTextView = (TextView) findViewById(R.id.date_added_text_view);
        this.listView = (ListView) findViewById(R.id.habit_event_item);

        TextView textView = new TextView(getApplicationContext());

        textView.setText(" Habit Events ");
        listView.addHeaderView(textView, null, false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(HabitDetailViewActivity.this, ViewHabitEventActivity.class);

                ArrayList<HabitEvent> events = getHabitEventsOfHabit();
                HabitEvent myEvent = events.get(position - 1); //position -1 because of list header
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putString("habitTitle", myEvent.getHabitTitle());
                bundle.putString("eventDate", myEvent.getDateAsString());
                bundle.putString("eventComment", myEvent.getComment());
                if (myEvent.getIsLocationSet()) {
                    String lat = String.format("%.2f", myEvent.getLocation().getLatitude());
                    String longitude = String.format("%.2f", myEvent.getLocation().getLongitude());
                    String format = " LAT: " + lat + " LONG: " + longitude;
                    bundle.putString("eventLocation", format);
                } else {
                    bundle.putString("eventLocation", "Location not Set");
                }
                if (myEvent.hasImage()) {
                    bundle.putParcelable("eventImage", myEvent.getImage());
                }
                bundle.putBoolean("hasImage", myEvent.hasImage());


                bundle.putString("eventId", myEvent.getId());
                //bundle.putSerializable("eventClicked", getHabitEventsOfHabit()); //sending habitEventlist
                //bundle.putInt("eventIndex", position - 1);

                intent5.putExtras(bundle);
                startActivity(intent5);
            }
        });



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

        //SaveFileController saveFileController = new SaveFileController();
        this.habit = this.habitList.get(habitIndex);

        habitTitleTextView.setText(("What: "+ habit.getHabitTitle()));
        habitReasonTextView.setText(("Why: "+ habit.getHabitReason()));
        habitDateTextView.setText(("Start date: " + sdf.format(habit.getHabitStartDate().getTime())));

        progress = ProgressUpdate.getProgress(habit, getHabitEventsOfHabit());
        progressBar.setProgress(progress);

        habitReasonTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HabitDetailViewActivity.this);
                builder.setTitle("Edit Reason").setMessage("Enter a new reason");

                final EditText input = new EditText(HabitDetailViewActivity.this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(layoutParams);
                builder.setView(input);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (input.getText().toString().length() == 0){
                            Toast toast = Toast.makeText(HabitDetailViewActivity.this, "You cannot set the" +
                                    " reason as empty!", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                        }
                        else if(input.getText().toString().length() > 30){
                            Toast toast = Toast.makeText(HabitDetailViewActivity.this,
                                    "Reason too long!", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                        }
                        else {
                            habitReasonTextView.setText(input.getText().toString());
                            EditHabitDetailController.modifyHabitReason(habit,
                                    input.getText().toString());
                            Toast toast = Toast.makeText(HabitDetailViewActivity.this,
                                    "Reason modified!", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                        }
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


        habitDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newDate = Calendar.getInstance();
                new DatePickerDialog(HabitDetailViewActivity.this, onDateSetListener,
                        newDate.get(java.util.Calendar.YEAR),
                        newDate.get(java.util.Calendar.MONTH),
                        newDate.get(java.util.Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        /**
         *
         * @param view
         * @param year
         * @param month
         * @param day
         *
         * Implements the date widget when user is selecting date.
         */
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            newDate.set(java.util.Calendar.YEAR, year);
            newDate.set(java.util.Calendar.MONTH, month);
            newDate.set(java.util.Calendar.DAY_OF_MONTH, day);
            habitDateTextView.setText(((month + 1) + " / " + day + " / " + year));

            EditHabitDetailController.modifyDate(habit, newDate);

            Toast toast = Toast.makeText(HabitDetailViewActivity.this,
                    "Start date modified!", Toast.LENGTH_SHORT);
            toast.show();
        }

    };

    /**
     * Opens the new habit event activity page so that the user
     * can create new habit event.
     *
     * @param view instance of View
     * @see NewHabitEventActivity
     */
    public void addNewHabitEventButton(View view) {
        Intent intent2 = new Intent(HabitDetailViewActivity.this, NewHabitEventActivity.class);
        intent2.putExtra("userName", userName);
        startActivity(intent2);
    }

    /**
     * Returns user to MainActivity so user can
     * do something else.
     *
     * @param view
     */
    public void exitToMain(View view) {
        Intent intent = new Intent(HabitDetailViewActivity.this, MainActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

    /**
     * Button implementation to delete current habit.
     *
     * @param view instance of View
     * @see TodayViewActivity
     */
    public void deleteCurrentHabitButton(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(HabitDetailViewActivity.this);
        builder.setTitle("Edit record").setMessage("HELLO")
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ElasticsearchController.DeleteHabitsTask deleteHabitsTask = new ElasticsearchController.DeleteHabitsTask();
                        deleteHabitsTask.execute(habit);

                        ArrayList<HabitEvent> habitEvents = getHabitEventsOfHabit();
                        if (!habitEvents.isEmpty()) {
                            ElasticsearchController.DeleteEventsTask deleteEventsTask = new ElasticsearchController.DeleteEventsTask();
                            deleteEventsTask.execute(habitEvents);
                        }

                        Intent intent3 = new Intent(HabitDetailViewActivity.this, MainActivity.class);
                        intent3.putExtra("userName", userName);
                        startActivity(intent3);
                    }
                })
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListView(getHabitEventsOfHabit());
    }

    /**
     * Updates list view.
     *
     * @param events Arraylist of type HabitEvent
     */
    private void updateListView(ArrayList<HabitEvent> events) {
        ArrayAdapter<HabitEvent> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.habit_event_list_item, events);
        synchronized (listView) {

            this.listView.setAdapter(arrayAdapter);
            this.listView.notify();
        }
    }


    public ArrayList<HabitEvent> getHabitEventsOfHabit() {
        ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
        getEventsTask.execute(userName);
        ArrayList<HabitEvent> myHabitEvents = new ArrayList<>();

        try {
            //all habit events of the user
            ArrayList<HabitEvent> allHabitEvents = getEventsTask.get();

            if (!allHabitEvents.isEmpty()) {
                //filter it for habit events specific to the habit
                for (int i = 0; i < allHabitEvents.size(); i++) {
                    if (allHabitEvents.get(i).getHabitTitle().equals(habit.getHabitTitle())) {
                        myHabitEvents.add(allHabitEvents.get(i));
                    }
                }
                Log.i("HabitDetailView: habitEvents of habit: ", "found some" + myHabitEvents.toString());

            } else {
                Log.i("HabitDetailView: habitEvents of habit: ", "found none");

            }
        } catch (Exception e) {
            Log.i("HabitDetailView: ", e.toString());

        }
        return myHabitEvents;

    }
}
