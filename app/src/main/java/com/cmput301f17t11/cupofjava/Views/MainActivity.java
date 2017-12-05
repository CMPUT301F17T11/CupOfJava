package com.cmput301f17t11.cupofjava.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.cmput301f17t11.cupofjava.Models.BottomNavigationViewHelper;
import com.cmput301f17t11.cupofjava.R;

public class MainActivity extends AppCompatActivity{

    private String userName;
    //private double currentLat;
    //private double currentLon;

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
                case R.id.navigation_add:
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Add New")
                            .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, NewHabitActivity.class);
                                    intent.putExtra("userName", userName);
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("New Habit Event", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, NewHabitEventActivity.class);
                                    intent.putExtra("userName", userName);
                                    startActivity(intent);
                                }
                            });


                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                case R.id.navigation_social:
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("userName", userName);
                    SocialFragment socialFragment = new SocialFragment();
                    socialFragment.setArguments(bundle4);
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.frame, socialFragment, "Today");
                    fragmentTransaction4.commit();
                    return true;
                case R.id.navigation_allhabit:
                    Bundle bundle5 = new Bundle();
                    bundle5.putString("userName", userName);
                    AllHabitViewActivity allHabitViewActivity = new AllHabitViewActivity();
                    allHabitViewActivity.setArguments(bundle5);
                    FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction5.replace(R.id.frame, allHabitViewActivity, "Today");
                    fragmentTransaction5.commit();
                    return true;
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
        navigation.setSelectedItemId(R.id.navigation_social);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }

    @Override
    public void onResume(){
        super.onResume();

        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        SocialFragment socialFragment = new SocialFragment();
        socialFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.frame, socialFragment, "Today");
        fragmentTransaction2.commit();
    }
}