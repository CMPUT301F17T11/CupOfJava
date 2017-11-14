/* UserLoginActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//TODO: Check if username exists
//TODO: Register page

/**
 * Activity handles required username and optional password entry.
 * User can register if they have not done so yet.
 *
 * @version 1.0
 */
public class UserLoginActivity extends Activity {

    private EditText username_editText;
    private Button signIn;

    /**
     * Launches the screen to enter username and optional password.
     *
     * @param savedInstanceState saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Context context = getApplicationContext();

        username_editText = (EditText) findViewById(R.id.username);

        signIn = (Button) findViewById(R.id.username_sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = username_editText.getText().toString();
                if (input.isEmpty()){
                    username_editText.setError("Field cannot be left empty");
                }
                else{
                    SaveFileController saveFileController = new SaveFileController();
                    int userIndex = saveFileController.getUserIndex(context, input);

                    if (userIndex == -1){
                        User newUser = new User(input);
                        saveFileController.addNewUser(context, newUser);
                    }

                    Intent intent = new Intent(UserLoginActivity.this, TodayViewActivity.class);
                    intent.putExtra("userName", input);
                    intent.putExtra("userIndex", saveFileController.getUserIndex(context, input));
                    startActivity(intent);
                }
            }
        });
    }
}
