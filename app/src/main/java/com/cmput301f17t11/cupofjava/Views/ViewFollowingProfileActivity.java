package com.cmput301f17t11.cupofjava.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Controllers.EventFilteringHelper;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;

public class ViewFollowingProfileActivity extends AppCompatActivity {
    private String userName;
    private String followingName;
    private ArrayList<Habit> habits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_following_profile);

        this.userName = getIntent().getStringExtra("userName");
        this.followingName = getIntent().getStringExtra("followingName");


        Button exitButton = (Button) findViewById(R.id.button_exit_following);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.following_profile_list_view);

        this.habits = new ArrayList<>();
        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();
        getHabitsTask.execute(followingName);
        try {
            habits = getHabitsTask.get();


        } catch (Exception e) {
            Log.i("Error Getting Habits ", e.toString());
        }

        final TextView textView = (TextView) findViewById(R.id.following_profile_textView);
        textView.setText(followingName);

        todayViewAdapter todayViewAdapter = new todayViewAdapter(ViewFollowingProfileActivity.this,
                habits);
        listView.setAdapter(todayViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewFollowingProfileActivity.this);
                dialog.setTitle("Latest Habit Event:").setMessage("");
                final TextView textView1 = new TextView(ViewFollowingProfileActivity.this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                textView1.setLayoutParams(layoutParams);
                dialog.setView(textView1);
                Habit selectedHabit = habits.get(position);
                try{
                    HabitEvent latestHabit = EventFilteringHelper.reverseChronological(EventFilteringHelper
                            .getHabitEventsOfHabit(followingName, selectedHabit))
                            .get(0);

                    textView1.setText(latestHabit.toString());
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(ViewFollowingProfileActivity.this, "No events yet!", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
    }
}
