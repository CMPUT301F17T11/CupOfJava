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

    private EditText habitTitle;
    private EditText habitReason;
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

    /**
     * Launches the screen to add/edit a habit.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_habit);

        habitTitle = (EditText) findViewById(R.id.habit_title);
        habitReason = (EditText) findViewById(R.id.habit_reason);
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
            case R.id.checkbox_sun:
                if (checked){
                    String sunChecked = (String) sunCheckBox.getText();
                }
                else{break;}
            case R.id.checkbox_mon:
                if (checked){
                    String monChecked = (String) monCheckBox.getText();
                }
                else{break;}
            case R.id.checkbox_tue:
                if (checked){
                    String tueChecked = (String) tueCheckBox.getText();
                }
                else {break;}
            case R.id.checkbox_wed:
                if (checked){
                    String wedChecked = (String) wedCheckBox.getText();
                }
                else{break;}
            case R.id.checkbox_thu:
                if (checked){
                    String thuChecked = (String) thuCheckBox.getText();
                }
                else{break;}
            case R.id.checkbox_fri:
                if (checked){
                    String friChecked = (String) friCheckBox.getText();
                }
                else{break;}
            case R.id.checkbox_sat:
                if (checked){
                    String satChecked = (String) satCheckBox.getText();
                }
                else{break;}
        }
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
            habitStartDateEditText.setText(year + "-" + (month + 1) + "-" + day);
        }

    };

    public void saveNewHabit(View view) {
        Intent intent = new Intent(NewHabitActivity.this, TodayViewActivity.class);
        startActivity(intent);
    }
}
