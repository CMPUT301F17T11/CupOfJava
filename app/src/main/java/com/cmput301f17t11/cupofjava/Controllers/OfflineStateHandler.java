package com.cmput301f17t11.cupofjava.Controllers;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;

/**
 * Created by naz_t on 12/1/2017.
 */

public class OfflineStateHandler {
    private String userSaveFile = "user_save_file.sav";
    private String habitSaveFile = "habit_save_file.sav";
    private String eventSaveFile = "event_save_file.sav";
    //private String imageSaveFile = "image_save_file.sav";
    private String userName;

    private ArrayList<User> userList;
    private ArrayList<Habit> habitList;
    private ArrayList<HabitEvent> habitEventList;

    public OfflineStateHandler(String userName){
        this.userName = userName;
        this.userList = new ArrayList<>();
        this.habitList = new ArrayList<>();
        this.habitEventList = new ArrayList<>();
        sync();
    }

    private boolean isConnected(){
        return false;
    }

    private void sync(){

    }

    public void saveUsers(ArrayList<User> users){

    }

    public void saveHabits(ArrayList<Habit> habits){

    }

    public void saveEvents(ArrayList<HabitEvent> events){

    }

    public void pushUsers(){

    }

    public void pushHabits(){

    }

    public void pushEvents(){

    }

    public void saveAll(ArrayList<User> users, ArrayList<Habit> habits,
                        ArrayList<HabitEvent> events){
        saveUsers(users);
        saveHabits(habits);
        saveEvents(events);
    }

    public void pushAll(){
        pushUsers();
        pushEvents();
        pushHabits();
    }
}
