package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
//import android.icu.util.Calendar;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This activity handles adding and configuring an existent habit.
 * User must add add a name and reason for the habit.
 * User must choose the frequency & start date of the habit they are creating.
 */
public class NewHabitActivity extends Activity {

    private EditText habitTitleEditText;
    private EditText habitReasonEditText;
    private EditText habitStartDateEditText;
    private Button saveHabitButton;
    private Date date;
    private Calendar habitStartDate;

    /*
    private CheckBox sunCheckBox;
    private CheckBox monCheckBox;
    private CheckBox tueCheckBox;
    private CheckBox wedCheckBox;
    private CheckBox thuCheckBox;
    private CheckBox friCheckBox;
    private CheckBox satCheckBox;
    */

    private String habitTitle;
    private String habitReason;
    private ArrayList<Integer> repeatingDays;

    private String userName;
    private int userIndex;

    /**
     * Launches the screen to add/edit a habit.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

        //obtain extra info from intent
        Intent intent = getIntent();
        this.userName = intent.getStringExtra("userName");
        this.userIndex = intent.getIntExtra("userIndex", 0);

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
            habitStartDateEditText.setText((year + "-" + (month + 1) + "-" + day));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

    };

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

        SaveFileController saveFileController = new SaveFileController();
        saveFileController.addHabit(getApplicationContext(), this.userIndex, newHabit);
        Intent intent = new Intent(NewHabitActivity.this, TodayViewActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userIndex", userIndex);
        startActivity(intent);
    }

}
