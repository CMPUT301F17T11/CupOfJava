package com.cmput301f17t11.cupofjava.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cmput301f17t11.cupofjava.Models.BottomNavigationViewHelper;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.User;
import com.cmput301f17t11.cupofjava.R;

import java.util.ArrayList;

/**
 * Created by Moe on 2017-11-28.
 */

public class TimeLineFragment extends Fragment implements NearbyTab.OnFragmentInteractionListener,
         HabitEventTimeLineActivity.OnFragmentInteractionListener{
    private String userName;
    private double currentLat;
    private double currentLon;

    /**
     * The system calls this when it's time for the fragment to draw its user interface for the first time.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle = getArguments();
        this.userName = bundle.getString("userName");
        this.currentLat = bundle.getDouble("currentLat",0.0);
        this.currentLon = bundle.getDouble("currentLon",0.0);



        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout2);

        tabLayout.addTab(tabLayout.newTab().setText("TimeLine"));
        tabLayout.addTab(tabLayout.newTab().setText("Nearby"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_view_pager);
        final HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public class HomePagerAdapter extends FragmentStatePagerAdapter {

        int numOfTabs;

        public HomePagerAdapter(FragmentManager fm, int numberOfTabs) {

            super(fm);
            this.numOfTabs = numberOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    HabitEventTimeLineActivity habitEventTimeLineActivity = new HabitEventTimeLineActivity();
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    bundle.putDouble("currentLat", currentLat);
                    bundle.putDouble("currentLon", currentLon);
                    habitEventTimeLineActivity.setArguments(bundle);
                    return habitEventTimeLineActivity;
                case 1:
                    NearbyTab nearbyTab = new NearbyTab();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("userName", userName);
                    nearbyTab.setArguments(bundle2);
                    return nearbyTab;

                default: return null;
            }

        }

        @Override
        public int getCount(){
            return numOfTabs;
        }
    }
}
