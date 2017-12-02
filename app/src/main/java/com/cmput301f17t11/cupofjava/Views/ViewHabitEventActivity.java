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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.R;
import com.google.android.gms.tasks.Task;

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
//TODO: get the habit list in spinner dropdown
public class ViewHabitEventActivity extends Activity {

    private TextView headingTextView;
    private TextView habitTitleTextView;
    private TextView habitDateBoxTextView;
    private TextView habitCommentTextView;
    private ImageView habitEventDetailPhoto;
    private String userName;
    private String comment;
    private ArrayList<HabitEvent> allEvents = new ArrayList<>();
    private int habitEventIndex;

    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_LOAD_IMG = 1;

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
            this.allEvents = (ArrayList<HabitEvent>) bundle.getSerializable("eventClicked");
            this.habitEventIndex = bundle.getInt("eventIndex");

        }

        final HabitEvent habitEvent = allEvents.get(this.habitEventIndex);
        habitTitleTextView = (TextView) findViewById(R.id.habit_event_detail_name);
        habitDateBoxTextView = (TextView) findViewById(R.id.habit_event_detail_date);
        habitCommentTextView = (TextView) findViewById(R.id.habit_event_detail_comment);

        habitTitleTextView.setText((habitEvent.getHabit().getHabitTitle()));
        habitDateBoxTextView.setText((habitEvent.getDateAsString()));
        habitCommentTextView.setText((habitEvent.getComment()));

        habitEventDetailPhoto = (ImageView) findViewById(R.id.habit_event_detail_photo);

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
                                //TODO ELASTICSEARCH FOR ESHNA
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

                        //TODO For Eshna to look at as to why it will not delete
                        ElasticsearchController.DeleteEventTask deleteEventTask = new ElasticsearchController.DeleteEventTask();
                        deleteEventTask.execute();


                        //TODO need to delete habit events associated with the habit as well
                        //finish();
                        //SaveFileController saveFileController = new SaveFileController();
                        //saveFileController.deleteHabit(getApplicationContext(), userIndex, habitIndex);
                        Intent intent3 = new Intent(ViewHabitEventActivity.this, MainActivity.class);
                        intent3.putExtra("userName", userName);
                        //intent3.putExtra("userIndex", userIndex);
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