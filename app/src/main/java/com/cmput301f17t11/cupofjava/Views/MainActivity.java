package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private String userName;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_timeline:
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("userName", userName);
                    TimeLineFragment timeLineFragment = new TimeLineFragment();
                    timeLineFragment.setArguments(bundle1);
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame, timeLineFragment, "TimeLine");
                    fragmentTransaction.commit();
                    return true;


                case R.id.navigation_today:
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("userName", userName);
                    TodayViewActivity todayViewActivity = new TodayViewActivity();
                    todayViewActivity.setArguments(bundle2);
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.frame, todayViewActivity, "Today");
                    fragmentTransaction2.commit();
                    return true;
                    /*
                    case R.id.navigation_add:
                        FragmentThree fragment3 = new FragmentThree();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.fram, fragment3, "FragmentThree");  //create first framelayout with id fram in the activity where fragments will be displayed
                        fragmentTransaction3.commit();
                        return true;
                    case R.id.navigation_social:
                        FragmentThree fragment3 = new FragmentThree();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.fram, fragment3, "FragmentThree");  //create first framelayout with id fram in the activity where fragments will be displayed
                        fragmentTransaction3.commit();
                        return true;
                    case R.id.navigation_allhabit:
                        FragmentThree fragment3 = new FragmentThree();
                        FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.fram, fragment3, "FragmentThree");  //create first framelayout with id fram in the activity where fragments will be displayed
                        fragmentTransaction3.commit();
                        return true;*/
                default:
                    return false;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userName = getIntent().getStringExtra("userName");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.menu);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        TodayViewActivity todayViewActivity = new TodayViewActivity();
        todayViewActivity.setArguments(bundle);
        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.frame, todayViewActivity, "Today");
        fragmentTransaction2.commit();

    }
}