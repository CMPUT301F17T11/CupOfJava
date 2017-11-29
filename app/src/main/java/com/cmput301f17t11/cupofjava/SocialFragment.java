package com.cmput301f17t11.cupofjava;


import android.support.v4.app.Fragment;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SocialFragment extends Fragment implements FollowingTab.OnFragmentInteractionListener,
FollowersTab.OnFragmentInteractionListener, RequestsTab.OnFragmentInteractionListener {

    //private TabItem followingTab;
    //private TabItem followersTab;
    //private TabItem requestsTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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


}
