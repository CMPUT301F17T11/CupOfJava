package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

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

    public void testAddHabitButton(){
        solo.assertCurrentActivity("new activity", TodayViewActivity.class);
        solo.clickOnView((solo.getView(R.id.add_habit)));
        solo.assertCurrentActivity("new activity", NewHabitActivity.class);
        solo.enterText((EditText) solo.getView(R.id.habit_title),"Disciplining Kids");
        assertTrue(solo.waitForText("Disciplining Kids"));
        solo.enterText((EditText)solo.getView(R.id.habit_reason),"good Nanny");
        assertTrue(solo.waitForText("good Nanny"));
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
        solo.clickOnView(solo.getView(R.id.action_today));
        solo.assertCurrentActivity("Parent Activity",TodayViewActivity.class);
    }
}


