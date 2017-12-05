package com.cmput301f17t11.cupofjava;

import android.app.Activity;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Views.AllHabitViewActivity;
import com.cmput301f17t11.cupofjava.Views.HabitDetailViewActivity;
import com.cmput301f17t11.cupofjava.Views.HabitEventTimeLineActivity;
import com.cmput301f17t11.cupofjava.Views.MainActivity;
import com.cmput301f17t11.cupofjava.Views.NewHabitActivity;
import com.cmput301f17t11.cupofjava.Views.TodayViewActivity;
import com.cmput301f17t11.cupofjava.Views.UserLoginActivity;
import com.robotium.solo.Solo;

/**
 * Created by eshna on 2017-11-13.
 */

public class UserLoginActivityTest extends ActivityInstrumentationTestCase2<UserLoginActivity> {
    private Solo solo;

    public UserLoginActivityTest() {
        super(UserLoginActivity.class);
    }

    /**
     * Runs at the beginning of the test
     * @throws Exception
     */
    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }
    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testLogin(){
        solo.assertCurrentActivity("Wrong Activity", com.cmput301f17t11.cupofjava.Views.UserLoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.username), "test");
        solo.clickOnButton("Sign in | Register");

        solo.assertCurrentActivity("new activity", com.cmput301f17t11.cupofjava.Views.MainActivity.class);
        solo.clickOnView((solo.getView(R.id.navigation_add)));
        solo.clickOnText("New Habit");
        solo.assertCurrentActivity("new activity",com.cmput301f17t11.cupofjava.Views.NewHabitActivity.class);
        solo.enterText((EditText) solo.getView(R.id.habit_title),"hello");
        assertTrue(solo.waitForText("hello"));
        solo.enterText((EditText)solo.getView(R.id.habit_reason),"just");
        assertTrue(solo.waitForText("just"));
        solo.clickOnView((solo.getView(R.id.habit_start_date)));
        solo.setDatePicker(0,2017,11,4);
        solo.clickOnText("OK");
        assertTrue(solo.waitForText("12 / 4 / 2017"));
        solo.clickOnView(solo.getView(R.id.checkbox_sun));
        solo.clickOnView(solo.getView(R.id.checkbox_mon));
        solo.clickOnView(solo.getView(R.id.checkbox_tue));
        solo.clickOnView(solo.getView(R.id.checkbox_wed));
        solo.clickOnView(solo.getView(R.id.checkbox_thu));
        solo.clickOnView(solo.getView(R.id.checkbox_fri));
        solo.clickOnView(solo.getView(R.id.checkbox_sat));

        solo.clickOnButton("SAVE");

        solo.sleep(500);
        solo.assertCurrentActivity("Parent Activity",com.cmput301f17t11.cupofjava.Views.MainActivity.class);
        solo.clickOnView(solo.getView(R.id.navigation_today));
        ListView listView = (ListView)solo.getView(R.id.selfProfileHabitListView);
        View view= listView.getChildAt(1);
        solo.clickOnView(view);
        solo.assertCurrentActivity("new activity",com.cmput301f17t11.cupofjava.Views.HabitDetailViewActivity.class);
        solo.clickOnView(solo.getView(R.id.new_habit_event));
        solo.assertCurrentActivity("new activity",com.cmput301f17t11.cupofjava.Views.NewHabitEventActivity.class);

        solo.enterText((EditText) solo.getView(R.id.edit_comment),"noo");
        solo.waitForText("noo");
        solo.clickOnView(solo.getView(R.id.habit_event_location));
        solo.clickOnText("ADD");
        solo.waitForDialogToClose();
        solo.clickOnText("SAVE");

        solo.assertCurrentActivity("Parent Activity",com.cmput301f17t11.cupofjava.Views.MainActivity.class);
        solo.clickOnView(solo.getView(R.id.navigation_allhabit));
        ListView listView1 = (ListView)solo.getView(R.id.allHabitListView);
        View view1 = listView1.getChildAt(0);
        solo.clickOnView(view1);
        solo.assertCurrentActivity("new activity",com.cmput301f17t11.cupofjava.Views.HabitDetailViewActivity.class);
        ListView listView2 = (ListView)solo.getView(R.id.habit_event_item);
        View view2 = listView2.getChildAt(1);
        solo.clickOnView(view2);

        solo.sleep(500);
        solo.assertCurrentActivity("Parent Activity",com.cmput301f17t11.cupofjava.Views.ViewHabitEventActivity.class);
        solo.clickOnView(solo.getView(R.id.delete_event));

        solo.clickOnText("CANCEL");
        //solo.waitForDialogToClose();
        solo.goBack();
        solo.assertCurrentActivity("new activity",com.cmput301f17t11.cupofjava.Views.HabitDetailViewActivity.class);
        solo.clickOnText("EXIT");
        solo.assertCurrentActivity("Parent Activity",com.cmput301f17t11.cupofjava.Views.MainActivity.class);
        solo.clickOnView(solo.getView(R.id.navigation_social));
        solo.assertCurrentActivity("Parent Activity",com.cmput301f17t11.cupofjava.Views.MainActivity.class);


        solo.clickOnText("Following");
        solo.clickOnText("Followers");
        solo.clickOnText("Requests");
        solo.clickOnText("Profile");

        solo.assertCurrentActivity("Parent Activity", com.cmput301f17t11.cupofjava.Views.MainActivity.class);


    }

}
