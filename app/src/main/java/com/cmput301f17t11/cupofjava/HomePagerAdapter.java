package com.cmput301f17t11.cupofjava;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Moe on 2017-11-28.
 */

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
                return habitEventTimeLineActivity;
            case 1:
                NearbyTab nearbyTab = new NearbyTab();
                return nearbyTab;

            default: return null;
        }

    }

    @Override
    public int getCount(){
        return numOfTabs;
    }
}

