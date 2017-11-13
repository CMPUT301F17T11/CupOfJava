package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by eshna on 2017-11-13.
 */

public class TodayViewActivityTest extends ActivityInstrumentationTestCase2<TodayViewActivity> {
    private Solo solo;

    public TodayViewActivityTest() {
        super(com.cmput301f17t11.cupofjava.TodayViewActivity.class);
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

    public void testAddHabit(){
        solo.assertCurrentActivity("new activity", TodayViewActivity.class);
        solo.clickOnView((solo.getView(R.id.add_habit)));
        solo.assertCurrentActivity("new activity", NewHabitActivity.class);
    }
}


