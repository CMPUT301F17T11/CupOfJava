package com.cmput301f17t11.cupofjava;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Moe on 2017-11-25.
 */

public class SocialPagerAdapter extends FragmentStatePagerAdapter{

    int numOfTabs;

    public SocialPagerAdapter(FragmentManager fm, int numberOfTabs) {

        super(fm);
        this.numOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProfileTab profileTab = new ProfileTab();
                return profileTab;
            case 1:
                FollowingTab followingTab = new FollowingTab();
                return followingTab;

            case 2:
                FollowersTab followersTab = new FollowersTab();
                return followersTab;

            case 3:
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
