/* HabitDetailViewActivity
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.Models.HabitList;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Controllers.SaveFileController;
import com.cmput301f17t11.cupofjava.Models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Opens activity which lets user view the details of a certain habit.
 *
 * @see Habit
 * @version 1.0
 */
public class HabitDetailViewActivity extends AppCompatActivity {

    private String habitDateCalendar;
    private ListView listView;
    private String userName;
    private User user;
    private int userIndex;
    private int habitIndex;
    private ArrayList<Habit> habitList = new ArrayList<>();
    private Habit habit;

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
            //this.userIndex = intent.getIntExtra("userIndex", 0);
            this.habitIndex = bundle.getInt("habitIndex");
        }

        TextView habitTitleTextView = (TextView) findViewById(R.id.title_text_view);
        TextView habitReasonTextView = (TextView) findViewById(R.id.reason_text_view);
        TextView habitDateTextView = (TextView) findViewById(R.id.date_added_text_view);
        this.listView = (ListView) findViewById(R.id.habit_event_item);

        TextView textView = new TextView(getApplicationContext());

        textView.setText(" Habit Events ");
        listView.addHeaderView(textView, null, false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(HabitDetailViewActivity.this, ViewHabitEventActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userName", userName);
                bundle.putSerializable("eventClicked", getHabitEventsOfHabit()); //sending habitEventlist
                bundle.putInt("eventIndex", position - 1);

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

    }

    /**
     * Opens the new habit event activity page so that the user
     * can create new habit event.
     *
     * @param view instance of View
     * @see NewHabitEventActivity
     */
    public void addNewHabitEventButton(View view) {
        //todo: new habit event
        Intent intent2 = new Intent(HabitDetailViewActivity.this, NewHabitEventActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putSerializable("habitList", habitList);
        bundle.putSerializable("user", user);
        bundle.putInt("habitIndex", habitIndex);
        //intent2.putExtra("userName", userName);
        //intent2.putExtra("userIndex", userIndex);
        //intent2.putExtra("habitIndex", habitIndex);
        intent2.putExtras(bundle);
        startActivity(intent2);
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


                        //SaveFileController saveFileController = new SaveFileController();
                        //saveFileController.deleteHabit(getApplicationContext(), userIndex, habitIndex);
                        Intent intent3 = new Intent(HabitDetailViewActivity.this, MainActivity.class);
                        intent3.putExtra("userName", userName);
                        //intent3.putExtra("userIndex", userIndex);
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
        //updates the list view with Habit Events
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
