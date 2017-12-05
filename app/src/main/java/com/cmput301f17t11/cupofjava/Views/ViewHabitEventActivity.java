/* ViewHabitEventActivity
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * This activity shows the event that the user has currently started but not ended.
 * User can see some details of the event such as the habit attached to it,
 * the reason for the attached habit, the start date of the associative habit,
 * the photo attached to the habit event, and progress stats.
 * <p>
 * User can top on the habit to swtich to any other habits associated with
 * the event.
 *
 * @version 1.0
 */

public class ViewHabitEventActivity extends AppCompatActivity{

    private TextView habitTitleTextView;
    private TextView habitDateBoxTextView;
    private TextView habitLocationTextView;
    private TextView habitCommentTextView;
    private ImageView habitEventDetailPhoto;
    private String userName;
    private String comment;
    private ArrayList<HabitEvent> allEvents = new ArrayList<>();
    private int habitEventIndex;
    private HabitEvent habitEvent;
    private Bitmap image;

    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_LOAD_IMG = 1;
    private String habitTitle;
    private String eventDate;
    private String eventComment;
    private String eventLocation;
    private String eventId;
    private boolean hasImage;

    /**
     * Launches Interface displaying the habit events and their basic details.
     *
     * @param savedInstanceState saved state of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_event);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.userName = bundle.getString("userName");
            this.habitTitle = bundle.getString("habitTitle");
            this.eventDate = bundle.getString("eventDate");
            this.eventComment = bundle.getString("eventComment");
            this.eventLocation = bundle.getString("eventLocation");
            this.eventId = bundle.getString("eventId");
            this.hasImage = bundle.getBoolean("hasImage");
            if (hasImage) {
                this.image = bundle.getParcelable("eventImage");
            }
            //this.allEvents = (ArrayList<HabitEvent>) bundle.getSerializable("eventClicked");
            //this.habitEventIndex = bundle.getInt("eventIndex");

        }

        //habitEvent = allEvents.get(this.habitEventIndex);
        habitTitleTextView = (TextView) findViewById(R.id.habit_event_detail_name);
        habitDateBoxTextView = (TextView) findViewById(R.id.habit_event_detail_date);
        habitCommentTextView = (TextView) findViewById(R.id.habit_event_detail_comment);
        habitLocationTextView = (TextView) findViewById(R.id.habit_event_detail_location);

        String title = "HABIT TITLE: " + habitTitle;
        String date = "EVENT DATE: " + eventDate;
        String thisComment = "COMMENT: " + eventComment;
        habitTitleTextView.setText(title);
        habitDateBoxTextView.setText(date);
        habitCommentTextView.setText(thisComment);

        //String format = " LAT: " + habitEvent.getLocation().getLatitude() +" LONG: "+ habitEvent.getLocation().getLongitude()
        habitLocationTextView.setText(eventLocation);

        //photo handling
        habitEventDetailPhoto = (ImageView) findViewById(R.id.habit_event_detail_photo);
        if (hasImage) {
            habitEventDetailPhoto.setImageBitmap(this.image);
        }

        habitEventDetailPhoto.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog
                        .Builder(ViewHabitEventActivity.this);
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

        habitCommentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog
                        .Builder(ViewHabitEventActivity.this);

                final EditText input = new EditText(ViewHabitEventActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);

                builder.setTitle("Edit Comment")
                        .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                comment = input.getText().toString();
                                habitCommentTextView.setText(comment);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                android.support.v7.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            habitEventDetailPhoto.setImageBitmap(photo);
        } else if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                habitEventDetailPhoto.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(ViewHabitEventActivity.this, "Something went wrong", Toast.LENGTH_LONG)
                        .show();
            }

        } else {
            Toast.makeText(ViewHabitEventActivity.this, "You haven't picked Image", Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void deleteCurrentHabitEvent(View view) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ViewHabitEventActivity.this);
        builder.setTitle("Delete Event")
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ElasticsearchController.DeleteEventTask2 deleteEventTask = new ElasticsearchController.DeleteEventTask2();
                        deleteEventTask.execute(eventId);
                        Intent intent3 = new Intent(ViewHabitEventActivity.this, MainActivity.class);
                        intent3.putExtra("userName", userName);
                        startActivity(intent3);
                    }
                })
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
    }
}