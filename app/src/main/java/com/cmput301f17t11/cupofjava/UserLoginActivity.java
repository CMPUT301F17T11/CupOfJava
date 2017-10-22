package com.cmput301f17t11.cupofjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.name;

public class UserLoginActivity extends AppCompatActivity{

    private EditText username_editText;
    private EditText password_editText;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_editText = (EditText) findViewById(R.id.email);
        password_editText = (EditText) findViewById(R.id.password);

        signIn = (Button) findViewById(R.id.username_sign_in_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
            }
        });
    }

    public void addUsername(){
        String username = username_editText.getText().toString();

        if (username.isEmpty()) {
            username_editText.setError("Enter username!");
            return;
        }
    }

    public void addPassword(){
        String password = password_editText.getText().toString();

    }
}
