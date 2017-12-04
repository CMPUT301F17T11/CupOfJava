package com.cmput301f17t11.cupofjava.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;

public class ViewFollowingProfileActivity extends AppCompatActivity {
    private String userName;
    private String followingName;

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

        //retrieving all habits of the user from elasticsearch
        ArrayList<Habit> habits = new ArrayList<>();
        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();
        getHabitsTask.execute(followingName);
        try {
            habits = getHabitsTask.get();


        } catch (Exception e) {
            Log.i("Error Getting Habits ", e.toString());
        }

        TextView textView = (TextView) findViewById(R.id.following_profile_textView);
        textView.setText(followingName);

        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<Habit>(getApplicationContext(),
                R.layout.habit_list_item, habits);
        listView.setAdapter(arrayAdapter);
    }
}
