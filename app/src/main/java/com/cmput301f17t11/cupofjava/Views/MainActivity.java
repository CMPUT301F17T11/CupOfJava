package com.cmput301f17t11.cupofjava.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cmput301f17t11.cupofjava.Models.BottomNavigationViewHelper;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements RequestsTab.OnFragmentInteractionListener,
        FollowingTab.OnFragmentInteractionListener, FollowersTab.OnFragmentInteractionListener*/{

    private String userName;
    private User user;
    private ArrayList<Habit> habits;

    /*@Override
    public void onFragmentInteraction(Uri uri){

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Bundle receiveBundle = getIntent().getExtras();
        if (receiveBundle != null) {
            this.user = (User)receiveBundle.getSerializable("userObject");
            this.userName = user.getUsername();
            //this.userName = bundle.getString("userName");

            //this.habitList = (ArrayList<Habit>) bundle.getSerializable("habitClicked");
            //this.userIndex = intent.getIntExtra("userIndex", 0);
            //this.habitIndex = bundle.getInt("habitIndex");
        }*/
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        Log.i("MainActivity: username received:", userName);

        //this.habits = user.getHabitListAsArray();

        Bundle bundle = new Bundle();
        //bundle.putSerializable("user", user);
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
                    // Left most choice
                    case R.id.action_timeline:
                        // Bundles are passing package information
                        Bundle bundle = new Bundle();
                        //bundle.putSerializable("user", user);
                        //bundle.putSerializable("habitList", habits);
                        // Put username in Bundle
                        bundle.putString("userName", userName);
                        // HomeFragment is class of Fragments
                        HomeFragment fragment = new HomeFragment();
                        // Give fragment packet of information
                        fragment.setArguments(bundle);
                        // Get certain functionality from fragment to ?
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        // Do something
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        // Do transaction (
                        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(null);
                        fragmentTransaction.commit();


                        //intent2.putExtra("userIndex", userIndex);
                        return true;
                    case R.id.action_today:
                        Bundle bundle3 = new Bundle();
                        //bundle3.putSerializable("user", user);
                        //bundle3.putSerializable("habitList", habits);
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
                        //bundle2.putSerializable("user", user);
                        //bundle2.putSerializable("habitList", habits);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(MainActivity.this, NewHabitActivity.class);
                                        intent4.putExtra("userName", userName);
                                        //intent4.putExtra("userIndex", userIndex);
                                        startActivity(intent4);
                                    }
                                })
                                .setPositiveButton("New Habit Event", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent5 = new Intent(MainActivity.this, NewHabitEventActivity.class);
                                        intent5.putExtra("userName", userName);
                                        //bundle.putSerializable("habitList", habits);
                                        //.putExtra("userIndex", userIndex);

                                        startActivity(intent5);
                                    }
                                });


                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.action_friends:
                        Bundle bundle4 = new Bundle();
                        //bundle4.putSerializable("user", user);
                        //bundle4.putSerializable("habitList", habits);
                        bundle4.putString("userName", userName);
                        SocialFragment fragment4 = new SocialFragment();
                        fragment4.setArguments(bundle4);
                        FragmentManager fragmentManager4 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4).addToBackStack(null);
                        fragmentTransaction4.commit();

                        break;
                }
                return false;
            }
        });
    }
}