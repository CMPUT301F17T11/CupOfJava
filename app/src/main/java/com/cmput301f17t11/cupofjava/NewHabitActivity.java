package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
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
    private CheckBox sunCheckBox;
    private CheckBox monCheckBox;
    private CheckBox tueCheckBox;
    private CheckBox wedCheckBox;
    private CheckBox thuCheckBox;
    private CheckBox friCheckBox;
    private CheckBox satCheckBox;

    private String habitTitle;
    private String habitReason;
    private Habit newHabit = new Habit();

    /**
     * Launches the screen to add/edit a habit.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

        habitTitleEditText = (EditText) findViewById(R.id.habit_title);
        habitReasonEditText = (EditText) findViewById(R.id.habit_reason);
        habitStartDateEditText = (EditText) findViewById(R.id.habit_start_date);

        sunCheckBox = (CheckBox) findViewById(R.id.checkbox_sun);
        monCheckBox = (CheckBox) findViewById(R.id.checkbox_mon);
        tueCheckBox = (CheckBox) findViewById(R.id.checkbox_tue);
        wedCheckBox = (CheckBox) findViewById(R.id.checkbox_wed);
        thuCheckBox = (CheckBox) findViewById(R.id.checkbox_thu);
        friCheckBox = (CheckBox) findViewById(R.id.checkbox_fri);
        satCheckBox = (CheckBox) findViewById(R.id.checkbox_sat);

        saveHabitButton = (Button) findViewById(R.id.habit_save_button);

        habitStartDateEditText.setFocusable(false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");

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

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked

        switch(view.getId()) {
            case R.id.checkbox_sun: if (checked){ newHabit.addRepeatingDay(0);} else {break;}

            case R.id.checkbox_mon: if (checked){ newHabit.addRepeatingDay(1);} else {break;}

            case R.id.checkbox_tue: if (checked){ newHabit.addRepeatingDay(2);} else {break;}

            case R.id.checkbox_wed: if (checked){ newHabit.addRepeatingDay(3);} else {break;}

            case R.id.checkbox_thu: if (checked){ newHabit.addRepeatingDay(4);} else {break;}

            case R.id.checkbox_fri: if (checked){ newHabit.addRepeatingDay(5);} else {break;}

            case R.id.checkbox_sat: if (checked){ newHabit.addRepeatingDay(6);} else {break;}
        }
    }

    /*public void addIndexForDay(int dayIndex) {
        switch(dayIndex) {
            case 0: newHabit.addRepeatingDay(dayIndex);
            case 1: newHabit.addRepeatingDay(dayIndex);
            case 2: newHabit.addRepeatingDay(dayIndex);
            case 3: newHabit.addRepeatingDay(dayIndex);
            case 4: newHabit.addRepeatingDay(dayIndex);
            case 5: newHabit.addRepeatingDay(dayIndex);
            case 6: newHabit.addRepeatingDay(dayIndex);
        }
    }*/

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

    };

    public void saveNewHabit(View view) {

        addNewHabit();

        Intent intent = new Intent(NewHabitActivity.this, TodayViewActivity.class);
        startActivity(intent);
    }

    protected void addNewHabit() {

        habitTitleEditText.getText().toString();
        if (habitTitle.isEmpty()) {
            habitTitleEditText.setError("Enter name");
            return;
        } else if (habitTitle.length() > 20) {
            habitTitleEditText.setError("Too Long");
        } else {
            newHabit.setHabitTitle(habitTitle);
        }

        habitReasonEditText.getText().toString();
        if (habitReason.isEmpty()) {
            habitReasonEditText.setError("Enter name");
            return;
        } else if (habitReason.length() > 20) {
            habitReasonEditText.setError("Too Long");
        } else {
            newHabit.setHabitReason(habitReason);
        }

        if (habitStartDateEditText.getText().toString().isEmpty()) {
            habitStartDateEditText.setError("Enter Date");
        } else {
            newHabit.setHabitStartDate(habitStartDate);
        }

    }
}
