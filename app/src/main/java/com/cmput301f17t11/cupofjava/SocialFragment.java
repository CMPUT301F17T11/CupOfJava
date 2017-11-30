package com.cmput301f17t11.cupofjava;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SocialFragment extends Fragment implements FollowingTab.OnFragmentInteractionListener,
FollowersTab.OnFragmentInteractionListener, RequestsTab.OnFragmentInteractionListener {

    private String userName = "";
    private User user;
    private ArrayList<Habit> habitList;
    //private TabItem followingTab;
    //private TabItem followersTab;
    //private TabItem requestsTab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //obtain extra info from intent
        //final Intent intent = getActivity().getIntent();
        Bundle bundle = getArguments();
        if(bundle!=null){
            this.user = (User) bundle.getSerializable("user");
            this.userName = user.getUsername();
            this.habitList = (ArrayList<Habit>) bundle.getSerializable("habitList");
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

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation_today);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {

                    case R.id.action_timeline:
                        Bundle bundle = new Bundle();
                        //bundle.putString("userName", userName);
                        bundle.putSerializable("user", user);
                        bundle.putSerializable("habitList", habitList);
                        HomeFragment fragment = new HomeFragment();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(null);
                        fragmentTransaction.commit();
                        //intent2.putExtra("userIndex", userIndex);
                    case R.id.action_today:
                        Bundle bundle3 = new Bundle();
                        bundle3.putSerializable("user", user);
                        bundle3.putSerializable("habitList", habitList);
                        TodayViewActivity fragment3 = new TodayViewActivity();
                        fragment3.setArguments(bundle3);
                        FragmentManager fragmentManager3 = getFragmentManager();
                        FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                        fragmentTransaction3.replace(R.id.frame, fragment3).addToBackStack(null);
                        fragmentTransaction3.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.action_all_habits:
                        Bundle bundle4 = new Bundle();
                        bundle4.putSerializable("user", user);
                        bundle4.putSerializable("habitList", habitList);
                        //bundle4.putString("userName", userName);
                        AllHabitViewActivity fragment4 = new AllHabitViewActivity();
                        fragment4.setArguments(bundle4);
                        FragmentManager fragmentManager4 = getFragmentManager();
                        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                        fragmentTransaction4.replace(R.id.frame, fragment4).addToBackStack(null);
                        fragmentTransaction4.commit();
                        //intent2.putExtra("userIndex", userIndex);
                        break;
                    case R.id.add_habit_or_habit_event:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Add New")
                                .setNegativeButton("New Habit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent4 = new Intent(getActivity(), NewHabitActivity.class);
                                        intent4.putExtra("user", user);
                                        //intent4.putExtra("userIndex", userIndex);
                                        startActivity(intent4);
                                    }
                                })
                                .setPositiveButton("New Habit \n   Event", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent5 = new Intent(getActivity(), NewHabitEventActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("userName", userName);
                                        bundle.putSerializable("habitList", habitList);
                                        intent5.putExtras(bundle);

                                        //.putExtra("userIndex", userIndex);

                                        startActivity(intent5);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.action_friends:
                        break;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
