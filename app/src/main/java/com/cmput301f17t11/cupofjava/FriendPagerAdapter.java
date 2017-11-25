package com.cmput301f17t11.cupofjava;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cmput301f17t11.cupofjava.FollowersTab;
import com.cmput301f17t11.cupofjava.FollowingTab;
import com.cmput301f17t11.cupofjava.RequestsTab;

/**
 * Created by Moe on 2017-11-25.
 */

public class FriendPagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public FriendPagerAdapter(FragmentManager fm, int numberOfTabs) {

        super(fm);
        this.numOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FollowingTab followingTab = new FollowingTab();
                return followingTab;

            case 1:
                FollowersTab followersTab = new FollowersTab();
                return followersTab;

            case 2:
                RequestsTab requestsTab = new RequestsTab();
                return requestsTab;

            default: return null;
        }

    }

    @Override
    public int getCount(){
        return numOfTabs;
    }
}
