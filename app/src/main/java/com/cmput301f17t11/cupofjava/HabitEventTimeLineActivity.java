package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

public class HabitEventTimeLineActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_time_line);

        //handle the bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        break;
                    case R.id.action_today:
                        Intent intent2 = new Intent(HabitEventTimeLineActivity.this, TodayViewActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_all_habits:
                        Intent intent3 = new Intent(HabitEventTimeLineActivity.this, AllHabitViewActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.add_habit:
                        Intent intent4 = new Intent(HabitEventTimeLineActivity.this, NewHabitActivity.class);
                        startActivity(intent4);
                        break;

                }
                return false;
            }
        });

        //fill contents
    }
}
