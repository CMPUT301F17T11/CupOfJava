package com.cmput301f17t11.cupofjava.Models;

import java.util.ArrayList;

/**
 * Created by naz_t on 12/3/2017.
 */

public class OfflineData {
    private String saveFile = "offline_save.sav";
    private String userName;
    private ArrayList<Habit> habits;
    private ArrayList<HabitEvent> events;

    private ArrayList<Habit> habitDeleteQueue;
    private ArrayList<HabitEvent> eventDeleteQueue;
    private ArrayList<Habit> habitAddQueue;
    private ArrayList<HabitEvent> eventAddQueue;

    public OfflineData(String userName){
        this.userName = userName;
        this.habits = new ArrayList<>();
        this.events = new ArrayList<>();
        this.habitDeleteQueue = new ArrayList<>();
        this.habitAddQueue = new ArrayList<>();
        this.eventAddQueue = new ArrayList<>();
        this.eventDeleteQueue = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<Habit> getHabits() {
        return habits;
    }

    public void setHabits(ArrayList<Habit> habits) {
        this.habits = habits;
    }

    public ArrayList<HabitEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<HabitEvent> events) {
        this.events = events;
    }

    public ArrayList<Habit> getHabitDeleteQueue() {
        return habitDeleteQueue;
    }

    public void setHabitDeleteQueue(ArrayList<Habit> habitDeleteQueue) {
        this.habitDeleteQueue = habitDeleteQueue;
    }

    public ArrayList<HabitEvent> getEventDeleteQueue() {
        return eventDeleteQueue;
    }

    public void setEventDeleteQueue(ArrayList<HabitEvent> eventDeleteQueue) {
        this.eventDeleteQueue = eventDeleteQueue;
    }

    public ArrayList<Habit> getHabitAddQueue() {
        return habitAddQueue;
    }

    public void setHabitAddQueue(ArrayList<Habit> habitAddQueue) {
        this.habitAddQueue = habitAddQueue;
    }

    public ArrayList<HabitEvent> getEventAddQueue() {
        return eventAddQueue;
    }

    public void setEventAddQueue(ArrayList<HabitEvent> eventAddQueue) {
        this.eventAddQueue = eventAddQueue;
    }
}
