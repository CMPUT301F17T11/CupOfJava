package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class TodayViewActivity extends Activity {


    // ListView to be populated
    private ListView listView;

    // Custom Habit Adapter
    private HabitAdapter habitAdapter;

    private ArrayList<Habit> habitList = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_view);
        listView = (ListView) findViewById(R.id.selfProfileHabitListView);
        habitAdapter = new HabitAdapter(this, habitList);
        listView.setAdapter(habitAdapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_favorites:
                        Intent intent2 = new Intent(TodayViewActivity.this, HabitEventTimeLineActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_schedules:
                        break;
                    case R.id.action_music:
                        Intent intent3 = new Intent(TodayViewActivity.this, AllHabitViewActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.add_habit:
                        Intent intent4 = new Intent(TodayViewActivity.this, NewHabitActivity.class);
                        startActivity(intent4);
                        break;

                }
                return false;
            }
        });
    }




}
