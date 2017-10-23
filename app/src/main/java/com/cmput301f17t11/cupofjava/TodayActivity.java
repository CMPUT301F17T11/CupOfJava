package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.os.Bundle;

/**
 * This activity shows the user that habits they are
 * performing based on the present date.
 */
public class TodayActivity extends Activity {

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
    }
}
