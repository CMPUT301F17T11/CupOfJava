/* NewHabitActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitList;
import com.cmput301f17t11.cupofjava.R;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//import android.icu.util.Calendar;

/**
 * This activity handles adding and configuring git an existent habit.
 * User must add add a name and reason for the habit.
 * User must choose the frequency & start date of the habit they are creating.
 *
 * @version 1.0
 */
public class NewHabitActivity extends AppCompatActivity{

    private EditText habitTitleEditText;
    private EditText habitReasonEditText;
    private EditText habitStartDateEditText;
    private Button saveHabitButton;
    private Calendar habitStartDate;
    private String habitTitle;
    private String habitReason;
    private ArrayList<Integer> repeatingDays;

    private String userName;
    private User user;
    private int userIndex;

    /**
     * Launches the screen to add/edit a habit.
     *
     * @param savedInstanceState the current saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

        //obtain extra info from intent
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");

        this.repeatingDays = new ArrayList<>();

        habitTitleEditText = (EditText) findViewById(R.id.habit_title);
        habitReasonEditText = (EditText) findViewById(R.id.habit_reason);
        habitStartDateEditText = (EditText) findViewById(R.id.habit_start_date);

        saveHabitButton = (Button) findViewById(R.id.habit_save_button);

        habitStartDateEditText.setFocusable(false);

        habitStartDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitStartDate = Calendar.getInstance();
                new DatePickerDialog(NewHabitActivity.this, onDateSetListener,
                        habitStartDate.get(java.util.Calendar.YEAR),
                        habitStartDate.get(java.util.Calendar.MONTH),
                        habitStartDate.get(java.util.Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        /**
         *
         * @param view
         * @param year
         * @param month
         * @param day
         *
         * Implements the date widget when user is selecting date.
         */
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            habitStartDate.set(java.util.Calendar.YEAR, year);
            habitStartDate.set(java.util.Calendar.MONTH, month);
            habitStartDate.set(java.util.Calendar.DAY_OF_MONTH, day);
            habitStartDateEditText.setText(((month + 1) + " / " + day + " / " + year));
        }

    };

    /**
     * Detects when a checkbox is clicked and saves the values to the
     * repeatingDays array list.
     *
     * @param view instance of View
     */
    public void onCheckboxClicked(View view) {
        //TODO: prj5 what about when the user unchecks the checkbox?
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        switch(view.getId()) {
            case R.id.checkbox_sun:
                if (checked){
                    Integer sunday = 0;
                    this.repeatingDays.add(sunday);
                }
                else {
                    break;
                }

            case R.id.checkbox_mon:
                if (checked){
                    Integer monday = 1;
                    this.repeatingDays.add(monday);
                }
                else {
                    break;
                }


            case R.id.checkbox_tue:
                if (checked){
                    Integer tuesday = 2;
                    this.repeatingDays.add(tuesday);
                }
                else {
                    break;
                }

            case R.id.checkbox_wed:
                if (checked){
                    Integer wednesday = 3;
                    this.repeatingDays.add(wednesday);
                }
                else {
                    break;
                }

            case R.id.checkbox_thu:
                if (checked){
                    Integer thursday = 4;
                    this.repeatingDays.add(thursday);
                }
                else {
                    break;
                }

            case R.id.checkbox_fri:
                if (checked){
                    Integer friday = 5;
                    this.repeatingDays.add(friday);
                }
                else {
                    break;
                }

            case R.id.checkbox_sat:
                if (checked){
                    Integer saturday = 6;
                    this.repeatingDays.add(saturday);
                }
                else {
                    break;
                }
        }
    }

    /**
     * Saves a new habit created by the user.
     *
     * @param view instance of View
     */
    public void saveNewHabit(View view) {

        this.habitTitle = habitTitleEditText.getText().toString();
        this.habitReason = habitReasonEditText.getText().toString();

        boolean validInput = true;

        if (this.habitTitle.isEmpty()) {
            habitTitleEditText.setError("Enter habit title!");
            validInput = false;
        }
        if (this.habitTitle.length() > 20) {
            habitTitleEditText.setError("Max is 20 characters!");
            validInput = false;
        }
        if (habitReason.isEmpty()) {
            habitReasonEditText.setError("Enter a reason!");
            validInput = false;
        }
        if (habitReason.length() > 20) {
            habitReasonEditText.setError("Too Long");
            validInput = false;
        }

        if (habitStartDateEditText.getText().toString().isEmpty()) {
            habitStartDateEditText.setError("Enter Date");
            validInput = false;
        }

        if (!validInput){
            return;
        }

        Habit newHabit = new Habit(habitTitle, habitReason, habitStartDate, repeatingDays);
        newHabit.setUsername(userName);
        newHabit.setHabitStartDate(habitStartDate);

        ElasticsearchController.AddHabitTask addHabitTask = new ElasticsearchController.AddHabitTask();
        addHabitTask.execute(newHabit);

        Intent intent = new Intent(NewHabitActivity.this, MainActivity.class);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }
}
