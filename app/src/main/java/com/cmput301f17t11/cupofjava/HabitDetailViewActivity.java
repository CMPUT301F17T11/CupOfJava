package com.cmput301f17t11.cupofjava;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Calendar;

public class HabitDetailViewActivity extends AppCompatActivity {

    private TextView habitTitleTextView;
    private TextView habitReasonTextView;
    private String habitDateCalendar;
    private ListView habitEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail_view);

        habitTitleTextView = (TextView) findViewById(R.id.title_text_view);
        habitReasonTextView = (TextView) findViewById(R.id.reason_text_view);



    }

    public void addNewHabitEventButton(View view) {
        Intent intent = new Intent(HabitDetailViewActivity.this, NewHabitEventActivity.class);
        startActivity(intent);
    }

    public void deleteCurrentHabitButton(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(HabitDetailViewActivity.this);
        builder.setTitle("Edit record")
                .setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HabitDetailViewActivity.this, TodayViewActivity.class);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
