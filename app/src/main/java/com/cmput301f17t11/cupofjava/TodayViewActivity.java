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


public class TodayViewActivity extends Activity {


    // ListView to be populated
    private ListView listView;

    // Custom Habit Adapter
    private HabitAdapter habitAdapter;

    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private String userName;
    private int userIndex;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_view);
        listView = (ListView) findViewById(R.id.selfProfileHabitListView);
        habitAdapter = new HabitAdapter(this, habitList);
        listView.setAdapter(habitAdapter);

        //obtain extra info from intent
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        this.userIndex = intent.getIntExtra("userIndex", 0);

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        Intent intent2 = new Intent(TodayViewActivity.this, HabitEventTimeLineActivity.class);
                        intent2.putExtra("userName", userName);
                        intent2.putExtra("userIndex", userIndex);
                        startActivity(intent2);
                        break;
                    case R.id.action_today:
                        break;
                    case R.id.action_all_habits:
                        Intent intent3 = new Intent(TodayViewActivity.this, AllHabitViewActivity.class);
                        intent3.putExtra("userName", userName);
                        intent3.putExtra("userIndex", userIndex);
                        startActivity(intent3);
                        break;
                    case R.id.add_habit:
                        Intent intent4 = new Intent(TodayViewActivity.this, NewHabitActivity.class);
                        intent4.putExtra("userName", userName);
                        intent4.putExtra("userIndex", userIndex);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

        //set up the TextView and ListView
        this.textView = (TextView) findViewById(R.id.SelfProfileHeadingTextView);
        this.listView = (ListView) findViewById(R.id.selfProfileHabitListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(TodayViewActivity.this,
                        HabitDetailViewActivity.class);
                intent5.putExtra("userName", userName);
                intent5.putExtra("userIndex", userIndex);
                intent5.putExtra("habitIndex", position);
                startActivity(intent5);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        SaveFileController saveFileController = new SaveFileController();
        ArrayList<Habit> habits = saveFileController
                .getHabitList(getApplicationContext(), userIndex).getTodaysHabitList();
        updateTextView(habits.size());
        updateListView(habits);
    }

    private void updateTextView(int habitCount){
        if (habitCount == 0){
            this.textView.setText(("You do have not not have anything for today."));
        }
        else{
            this.textView.setText(("Here are the habits you should carry out today:"));
        }
    }

    private void updateListView(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(TodayViewActivity.this,
                R.layout.habit_list_item, habits);
        this.listView.setAdapter(arrayAdapter);
    }
}