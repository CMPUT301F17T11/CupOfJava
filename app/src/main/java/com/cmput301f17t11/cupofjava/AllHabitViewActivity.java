package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllHabitViewActivity extends Activity {
    private ListView listView;
    private TextView textView;
    private String userName;
    private int userIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habit_view);

        //obtain extra info from intent
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        this.userIndex = intent.getIntExtra("userIndex", 0);

        //handle bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.action_timeline:
                        Intent intent2 = new Intent(AllHabitViewActivity.this, HabitEventTimeLineActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_today:
                        Intent intent3 = new Intent(AllHabitViewActivity.this, TodayViewActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.action_all_habits:
                        break;
                    case R.id.add_habit:
                        Intent intent4 = new Intent(AllHabitViewActivity.this, NewHabitActivity.class);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        //set up TextView and ListView
        this.textView = (TextView) findViewById(R.id.allHabitHeadingTextView);
        this.listView = (ListView) findViewById(R.id.allHabitListView);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO handleOnItemClick
                Intent intent = new Intent(AllHabitViewActivity.this, HabitDetailViewActivity.class);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        SaveFileController saveFileController = new SaveFileController();
        ArrayList<Habit> habitList = saveFileController
                .getHabitListAsArray(getApplicationContext(), this.userIndex);
        updateTextView(habitList.size());
        updateListView(habitList);
    }

    private void updateTextView(int habitCount){
        if (habitCount == 0){
            this.textView.setText(("You do have not have any habits to track. Perhaps it's time to start a new habit?"));
        }
        else{
            this.textView.setText(("Here are all of your habits:"));
        }
    }

    private void updateListView(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(AllHabitViewActivity.this,
                R.layout.habit_list_item, habits);
        this.listView.setAdapter(arrayAdapter);
    }
}
