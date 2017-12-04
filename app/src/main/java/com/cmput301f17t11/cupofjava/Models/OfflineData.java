package com.cmput301f17t11.cupofjava.Models;

import java.util.ArrayList;

/**
 * Created by naz_t on 12/3/2017.
 */

public class OfflineData {
    private String userName;
    private ArrayList<Habit> habits;
    private ArrayList<HabitEvent> events;

    private ArrayList<Habit> habitDeleteQueue;
    private ArrayList<HabitEvent> eventDeleteQueue;

    public OfflineData(){}


}
