package com.cmput301f17t11.cupofjava;


import android.support.design.widget.TabItem;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

public class FriendsActivity extends AppCompatActivity implements FollowingTab.OnFragmentInteractionListener,
FollowersTab.OnFragmentInteractionListener, RequestsTab.OnFragmentInteractionListener {

    //private TabItem followingTab;
    //private TabItem followersTab;
    //private TabItem requestsTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Following"));
        tabLayout.addTab(tabLayout.newTab().setText("Followers"));
        tabLayout.addTab(tabLayout.newTab().setText("Requests"));

        //followingTab = (TabItem) findViewById(R.id.following_tab_item);
        //followersTab = (TabItem) findViewById(R.id.followers_tab_item);
        //requestsTab = (TabItem) findViewById(R.id.requests_tab_item);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.friends_view_pager);
        final FriendPagerAdapter adapter = new FriendPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
