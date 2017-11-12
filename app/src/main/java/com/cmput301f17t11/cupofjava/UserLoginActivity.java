package com.cmput301f17t11.cupofjava;

import android.app.Activity;
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
 */
public class UserLoginActivity extends Activity {

    private EditText username_editText;
    private Button signIn;

    /**
     * Launches the screen to enter username and optional password.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_editText = (EditText) findViewById(R.id.username);

        signIn = (Button) findViewById(R.id.username_sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, TodayViewActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Stores username.
     */
    public void addUsername(){
        String username = username_editText.getText().toString();

        /**
         * Handler if no username is entered.
         */
        if (username.isEmpty()) {
            username_editText.setError("Enter username!");
            return;
        }
    }


}
