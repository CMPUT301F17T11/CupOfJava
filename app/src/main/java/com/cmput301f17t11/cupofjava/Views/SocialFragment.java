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

public class SocialFragment extends Fragment implements FollowingTab.OnFragmentInteractionListener,
FollowersTab.OnFragmentInteractionListener, RequestsTab.OnFragmentInteractionListener {

    private String userName = "";
    private User user;
    private ArrayList<Habit> habitList;
    //private TabItem followingTab;
    //private TabItem followersTab;
    //private TabItem requestsTab;

    public void SocialFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //obtain extra info from intent
        //final Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();
        if(bundle!=null){
            this.userName = bundle.getString("userName");

        }
        //this.userName = intent.getStringExtra("userName");
        //this.userIndex = intent.getIntExtra("userIndex", 0);

        View view = inflater.inflate(R.layout.activity_social, container, false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.addTab(tabLayout.newTab().setText("Followers"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));

        //followingTab = (TabItem) findViewById(R.id.following_tab_item);
        //followersTab = (TabItem) findViewById(R.id.followers_tab_item);
        //requestsTab = (TabItem) findViewById(R.id.requests_tab_item);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.friends_view_pager);
        final SocialPagerAdapter adapter = new SocialPagerAdapter(getChildFragmentManager(),tabLayout.getTabCount());
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
    public class SocialPagerAdapter extends FragmentStatePagerAdapter {

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
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    profileTab.setArguments(bundle);
                    return profileTab;
                case 1:
                    FollowingTab followingTab = new FollowingTab();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("userName", userName);
                    followingTab.setArguments(bundle2);
                    return followingTab;

                case 2:
                    FollowersTab followersTab = new FollowersTab();
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("userName", userName);
                    followersTab.setArguments(bundle3);
                    return followersTab;

                case 3:
                    RequestsTab requestsTab = new RequestsTab();
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("userName", userName);
                    requestsTab.setArguments(bundle4);
                    return requestsTab;

                default: return null;
            }

        }

        @Override
        public int getCount(){
            return numOfTabs;
        }
    }


}
