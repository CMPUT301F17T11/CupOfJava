package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

public class AllHabitViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_habit_view);

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

    }

}
