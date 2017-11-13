package com.cmput301f17t11.cupofjava;

import android.app.Activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

/**
 * Created by eshna on 2017-11-13.
 */

public class UserLoginActivityTest extends ActivityInstrumentationTestCase2<UserLoginActivity> {
    private Solo solo;

    public UserLoginActivityTest() {
        super(com.cmput301f17t11.cupofjava.UserLoginActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", UserLoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.username), "Mary Poppins");
        solo.clickOnButton("Sign in | Register");
        solo.assertCurrentActivity("new activity", TodayViewActivity.class);
        //solo.assertCurrentActivity("new activity", TodayViewActivity.class);

        //*Add habit button
        solo.clickOnView((solo.getView(R.id.add_habit)));
        solo.assertCurrentActivity("new activity", NewHabitActivity.class);
        //NewHabit Activity
        solo.enterText((EditText) solo.getView(R.id.habit_title),"Disciplining Kids");
        assertTrue(solo.waitForText("Disciplining Kids"));
        solo.enterText((EditText)solo.getView(R.id.habit_reason),"Good Nanny");
        assertTrue(solo.waitForText("Good Nanny"));
        solo.clickOnView((solo.getView(R.id.habit_start_date)));
        solo.setDatePicker(0,2017,10,13);
        solo.clickOnText("OK");
        assertTrue(solo.waitForText("11 / 13 / 2017"));
        solo.clickOnView(solo.getView(R.id.checkbox_sun));
        solo.clickOnView(solo.getView(R.id.checkbox_mon));
        solo.clickOnView(solo.getView(R.id.checkbox_tue));
        solo.clickOnView(solo.getView(R.id.checkbox_wed));
        solo.clickOnView(solo.getView(R.id.checkbox_thu));
        solo.clickOnView(solo.getView(R.id.checkbox_fri));
        solo.clickOnView(solo.getView(R.id.checkbox_sat));

        solo.clickOnButton("SAVE");
        solo.assertCurrentActivity("Parent Activity",TodayViewActivity.class);


    //testTodayHabitsButton
        solo.assertCurrentActivity("Parent Activity",TodayViewActivity.class);
        solo.clickOnView(solo.getView(R.id.action_today));
        solo.assertCurrentActivity("Parent Activity",TodayViewActivity.class);
    //testClickTodayHabitList
        TodayViewActivity activity = (TodayViewActivity) solo.getCurrentActivity();

        //solo.assertCurrentActivity("parent activity", TodayViewActivity.class);


        final ListView habitList = activity.getListView();

        Habit habit = (Habit) habitList.getItemAtPosition(habitList.getCount()-1);
        assertEquals("Disciplining Kids", habit.getHabitTitle());
        assertEquals("Good Nanny",habit.getHabitReason());

        solo.clickInList(habitList.getCount()-1);
        solo.assertCurrentActivity("wrong activity", HabitDetailViewActivity.class);
        assertTrue(solo.waitForText("Disciplining Kids", 1, 1000));
        assertTrue(solo.waitForText("Good Nanny", 1, 1000));
        assertTrue(solo.waitForText("11 / 13 / 2017", 1, 1000));
        solo.goBack();


    //testALlHabitsButton(){
        solo.assertCurrentActivity("Parent Activity",TodayViewActivity.class);
        solo.clickOnView(solo.getView(R.id.action_all_habits));
        solo.assertCurrentActivity("child activity", AllHabitViewActivity.class);
        //click on habit in All Habits View
        solo.sleep(5000);
        AllHabitViewActivity activity2 = (AllHabitViewActivity)solo.getCurrentActivity();



        final ListView habitList2 = activity2.getListView();

        Habit myhabit = (Habit) habitList.getItemAtPosition(habitList.getCount()-1);
        assertEquals("Disciplining Kids", myhabit.getHabitTitle());
        assertEquals("Good Nanny",myhabit.getHabitReason());

        solo.clickInList(habitList.getCount()-1);
        solo.assertCurrentActivity("wrong activity", HabitDetailViewActivity.class);
        assertTrue(solo.waitForText("Disciplining Kids", 1, 1000));
        assertTrue(solo.waitForText("Good Nanny", 1, 1000));
        assertTrue(solo.waitForText("11 / 13 / 2017", 1, 1000));
        solo.clickOnButton("Delete");
        solo.clickOnText("DELETE");

        solo.sleep(2000);
        //test timeline button
        solo.assertCurrentActivity("parent activity", TodayViewActivity.class);
        solo.clickOnView(solo.getView(R.id.action_timeline));
        solo.assertCurrentActivity("child activity", HabitEventTimeLineActivity.class);

        solo.sleep(2000);

        solo.goBack();


    }

}
