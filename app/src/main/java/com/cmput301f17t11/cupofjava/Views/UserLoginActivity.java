/* UserLoginActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.Geolocation;
import com.cmput301f17t11.cupofjava.Models.User;
import com.cmput301f17t11.cupofjava.R;

/**
 * Activity handles required username and optional password entry.
 * User can register if they have not done so yet.
 *
 * @version 1.0
 */
public class UserLoginActivity extends Activity {

    private EditText username_editText;
    private Button signIn;
    private User newUser;
    private User user;

    double currentLat;
    double currentLong;
    Geolocation location;

    /**
     * Launches the screen to enter username and optional password.
     *
     * @param savedInstanceState saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        username_editText = (EditText) findViewById(R.id.username);
        //getting the current location of the user
        location = new Geolocation(this, this);
        signIn = (Button) findViewById(R.id.username_sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Location is ", location.getLocation().toString());
                currentLat = location.getLocation().getLatitude();
                currentLong = location.getLocation().getLongitude();
                Log.i("Latitude ", "" + currentLat + "");
                Log.i("Latitude ", "" + currentLong + "");


                String input = username_editText.getText().toString();
                if (input.isEmpty()){
                    username_editText.setError("Field cannot be left empty");
                }
                ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
                getUserTask.execute(input);
                if (!input.isEmpty()) {
                    try {
                        user = getUserTask.get();
                        if (user == null) {
                            newUser = new User(input);
                            ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
                            addUserTask.execute(newUser);
                            Log.i("LoginActivity", "username is null");
                        } else {
                            newUser = user;
                            Log.i("LoginActivity", "username not null");
                        }
                        //sending the user info to main activity
                        Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                        intent.putExtra("userName", newUser.getUsername());
                        intent.putExtra("currentLat", currentLat);
                        intent.putExtra("currentLon", currentLong);

                        startActivity(intent);


                    } catch (Exception e) {
                        Log.i("Login Error", e.toString());
                        finish();
                    }

                }
            }
        });
    }

}
