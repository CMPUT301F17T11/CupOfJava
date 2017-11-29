package com.cmput301f17t11.cupofjava;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity /*implements RequestsTab.OnFragmentInteractionListener,
        FollowingTab.OnFragmentInteractionListener, FollowersTab.OnFragmentInteractionListener*/{

    private String userName;

    /*@Override
    public void onFragmentInteraction(Uri uri){

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");

        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        TodayViewActivity fragment = new TodayViewActivity();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(null);
        fragmentTransaction.commit();

        //handle the bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_timeline:
                        Bundle bundle = new Bundle();
                        bundle.putString("userName", userName);
                        HabitEventTimeLineActivity fragment = new HabitEventTimeLineActivity();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(null);
                        fragmentTransaction.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        return true;
                    case R.id.action_today:
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("userName", userName);
                        TodayViewActivity fragment3 = new TodayViewActivity();
                        fragment3.setArguments(bundle3);
                        FragmentManager fragmentManager3 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3).addToBackStack(null);
                        fragmentTransaction3.commit();
                        return true;
                    case R.id.action_all_habits:
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("userName", userName);
                        AllHabitViewActivity fragment2 = new AllHabitViewActivity();
                        fragment2.setArguments(bundle2);
                        FragmentManager fragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, fragment2).addToBackStack(null);
                        fragmentTransaction2.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        return true;
                    case R.id.add_habit_or_habit_event:
                        Intent intent4 = new Intent(MainActivity.this, NewHabitActivity.class);
                        intent4.putExtra("userName", userName);
                        //intent4.putExtra("userIndex", userIndex);
                        startActivity(intent4);
                        break;
                    case R.id.action_friends:
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("userName", userName);
                        FriendsActivity fragment4 = new FriendsActivity();
                        fragment4.setArguments(bundle4);
                        FragmentManager fragmentManager4 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4).addToBackStack(null);
                        fragmentTransaction4.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                }
                return false;
            }
        });
    }
}