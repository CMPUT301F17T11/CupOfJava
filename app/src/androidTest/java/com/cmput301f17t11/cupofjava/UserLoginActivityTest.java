package com.cmput301f17t11.cupofjava;

import android.app.Activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

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
    }
}
