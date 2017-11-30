/* NewHabitEventActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This activity allows user to add a new habit event, and
 * add habit that they would like to track under this event.
 * User can incorporate date, location, photo.
 *
 * @version 1.0
 */

public class NewHabitEventActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_LOAD_IMG = 1;
    private static final int MY_REQUEST_CODE = 1;

    private Calendar date;
    private EditText comment;
    private Bitmap habitEventPhotoBitmap;
    private ImageView habitEventPhoto;
    private Time time;
    private Location location;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private HabitAdapter habitAdapter;
    private String userName;
    private Spinner spinner;
    private int userIndex;
    private int habitIndex;
    private User user;

    private Habit habit;
    private String habitEventComment;

    private EditText habitEventCommentEditText;

    /**
     * This method is called when NewHabitEventActivity is instantiated.
     *
     * @param savedInstanceState the current saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit_event);

        ArrayAdapter<Habit> adapter = new ArrayAdapter<Habit>(this, R.layout.habit_list_item, habitList);

        //obtain extra info from intent
        //Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.user = (User) bundle.getSerializable("user");
            this.userName = bundle.getString("userName");

            this.habitList = (ArrayList<Habit>) bundle.getSerializable("habitList");
            //this.userIndex = intent.getIntExtra("userIndex", 0);
            this.habitIndex = bundle.getInt("habitIndex");
        }
        //this.userName = intent.getStringExtra("userName");
        //this.userIndex = intent.getIntExtra("userIndex", 0);
        //this.habitIndex = intent.getIntExtra("habitIndex", 0);

        habitEventCommentEditText = (EditText) findViewById(R.id.edit_comment);

        this.habitEventPhoto = (ImageView) findViewById(R.id.habit_event_photo);

        habitEventPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog
                        .Builder(NewHabitEventActivity.this);
                builder.setTitle("Add Photo")
                        .setNegativeButton("Open Camera", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                            }
                        })
                        .setPositiveButton("Open Gallery", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                                photoPickerIntent.setType("image/*");
                                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                            }
                        });
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        spinner =  (Spinner) findViewById(R.id.choose_habit_spinner);
        habitAdapter = new HabitAdapter(this, habitList);
        spinner.setAdapter(habitAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
                habitEventPhoto.setImageBitmap(photo);
        }
        else if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                habitEventPhoto.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(NewHabitEventActivity.this, "Something went wrong", Toast.LENGTH_LONG)
                        .show();
            }

        }else {
            Toast.makeText(NewHabitEventActivity.this, "You haven't picked Image",Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * This method is called when the activity is to be continued.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //SaveFileController saveFileController = new SaveFileController();
        //ArrayList<Habit> habits = saveFileController.getHabitList(getApplicationContext(), userIndex).getTodaysHabitList();

        ArrayList<Habit> todayHabits = getTodaysHabitList(habitList);
        updateSpinner(todayHabits);
    }
    private void updateSpinner(ArrayList<Habit> habits){
        ArrayAdapter<Habit> arrayAdapter = new ArrayAdapter<>(NewHabitEventActivity.this,
                R.layout.habit_list_item, habits);
        this.spinner.setAdapter(arrayAdapter);
    }

    /**
     * Saves the new habit event created by the user.
     *
     * @param view instance of View
     */
    public void saveNewHabitEvent(View view) {

        Habit spinnerHabit = (Habit) spinner.getSelectedItem();
        this.habit = spinnerHabit;
        this.habitEventComment = habitEventCommentEditText.getText().toString();

        HabitEvent newHabitEvent = new HabitEvent(habit, habitEventComment);
        newHabitEvent.setUserName(this.userName);
        ElasticsearchController.AddEventTask addEventTask = new ElasticsearchController.AddEventTask();
        addEventTask.execute(newHabitEvent);
        //SaveFileController saveFileController = new SaveFileController();
        //saveFileController.addHabitEvent(getApplicationContext(), this.userIndex, this.habitIndex, newHabitEvent);
        Intent intent = new Intent(NewHabitEventActivity.this, MainActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("user", user);
        //intent.putExtra("userIndex", userIndex);
        startActivity(intent);
    }

    public ArrayList<Habit> getTodaysHabitList(ArrayList<Habit> habits) {
        Calendar calendar = Calendar.getInstance();
        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDay;
        switch (dayOfTheWeek) {
            case Calendar.SUNDAY:
                currentDay = 0;
                break;
            case Calendar.MONDAY:
                currentDay = 1;
                break;
            case Calendar.TUESDAY:
                currentDay = 2;
                break;
            case Calendar.WEDNESDAY:
                currentDay = 3;
                break;
            case Calendar.THURSDAY:
                currentDay = 4;
                break;
            case Calendar.FRIDAY:
                currentDay = 5;
                break;
            case Calendar.SATURDAY:
                currentDay = 6;
                break;
            default:
                currentDay = 0;
                break;
        }

        ArrayList<Habit> todaysHabits = new ArrayList<>();
        Habit currentHabit;

        for (int i = 0; i < habits.size(); i++) {
            currentHabit = habits.get(i);
            if (currentHabit.onDay(currentDay)) {
                todaysHabits.add(currentHabit);
            }
        }

        return todaysHabits;
    }
}
