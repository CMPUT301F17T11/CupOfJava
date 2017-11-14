/* Habit
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import io.searchbox.annotations.JestId;

/**
 * Handles all properties related to a habit.
 * A habit contains a title, reason, date, frequency.
 * User can find out if habit is followed by another user or not.
 *
 * @version 1.0
 */
public class Habit {

    private String habitTitle;
    private String habitReason;
    private Calendar habitDate;
    private ArrayList<Integer> repeatingDays; //0 = Sun, 1 = Mon... 6 = Sat
    private int habitStatus = 0; //how closely the habit is being followed on a scale of 1 to 10.
                                //for each day followed, +1 and missing days would result in -1
    // todo prj5
    private HabitEventHistory habitEvents = new HabitEventHistory();
    //TODO Setters and getters for habit events

    /* TODO prj5
    @JestId
    private String id;
    public String getId() {
        return id;
    }
    public String setId(String id) {
        return this.id = id;
    }
    */

    /**
     * Constructor for Habit class.
     *
     * @param title title of the habit
     * @param reason reason for creating the habit
     * @param date  date habit was created
     */
    public Habit(String title, String reason, Calendar date){
        this.habitTitle = title;
        this.habitReason = reason;
        this.habitDate = date;
        this.repeatingDays = new ArrayList<>();
    }

    /**
     * Second constructor for Habit class.
     *
     * @param title title of the habit
     * @param reason reason for creating the habit
     * @param date date habit was created
     * @param days days the habit will be repeated
     */
    public Habit(String title, String reason, Calendar date, ArrayList<Integer> days){
        this.habitTitle = title;
        this.habitReason = reason;
        this.habitDate = date;
        this.repeatingDays = days;
    }

    /**
     * Empty constructor for Habit class.
     */
    public Habit(){}

    /**
     * Gets the habit title.
     *
     * @return the title of the habit
     */
    public String getHabitTitle() {
        return habitTitle;
    }

    /**
     * Sets the habit title.
     *
     * @param habitTitle sets the habit title to the parameter passed in
     */
    public void setHabitTitle(String habitTitle) {
        this.habitTitle = habitTitle;
    }

    /**
     * Gets the habit event history for a habit.
     *
     * @return an instance of HabitEventHistory
     */
    public HabitEventHistory getHabitEventHistory(){
        return this.habitEvents;
    }

    /**
     * Gets the reason for creating the habit.
     *
     * @return habit reason
     */
    public String getHabitReason() {
        return habitReason;
    }

    /**
     * Sets the reason for creating the habit.
     *
     * @param habitReason reason for creating habit
     */
    public void setHabitReason(String habitReason) {

        this.habitReason = habitReason;
    }

    /**
     * Checks if a certain day is a repeating day for the habit.
     *
     * @param day integer value corresponding to days of the week
     * @return true if the day is a repeating day, false otherwise
     */
    public boolean onDay(int day){
        for (int i = 0; i < repeatingDays.size(); i++){
            if (repeatingDays.get(i) == day){
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a certain day to the list of repeating days.
     *
     * @param day integer value corresponding to the days of the week
     */
    public void addRepeatingDay(int day){
        Integer i = new Integer(day);
        this.repeatingDays.add(i);
    }

    /* TODO remove repeating days when the checkbox is unselected
    public void removeRepeatingDay(int day){
        for (int i)
    }
    */

    /**
     * Gets the habit start date.
     *
     * @return habit date
     */
    public Calendar getHabitStartDate() {
        return habitDate;
    }

    /**
     * Sets the habit start date.
     *
     * @param habitDate habit start date
     */
    public void setHabitStartDate(Calendar habitDate) {
        this.habitDate = habitDate;
    }

    /**
     * Adds a habit event.
     *
     * @param habitEvent instance of HabitEvent
     * @see HabitEvent
     */
    public void addHabitEvent(HabitEvent habitEvent){
        this.habitEvents.addHabitEvent(habitEvent);
    }

    public void deleteHabitEvent(HabitEvent habitEvent){
        this.habitEvents.getHabitEvents().remove(habitEvent);
    }

    /**
     * Deletes habit event by index.
     *
     * @param index integer
     */
    public void deleteHabitEvent(int index){
        this.habitEvents.getHabitEvents().remove(index);
    }

    /*
     * A controller class will use these methods to keep track of how closely the habits are followed
     */

    /**
     * Tracks the habit progress to see if it is being followed.
     */
    public void habitFollowed(){
        this.habitStatus++;
        if (this.habitStatus > 10){
            this.habitStatus = 10;
        }
    }

    /**
     * Tracks the habit progress to see if it is being followed.
     */
    public void habitNotFollowed(){
        this.habitStatus--;
        if (this.habitStatus < 0){
            this.habitStatus = 0;
        }
    }

    /**
     * Gets habit status.
     *
     * @return integer value of habit status
     */
    public int getHabitStatus(){
        return habitStatus;
    }

    @Override
    public String toString(){ //this gets called by array adapter
        return ("What: " + this.habitTitle + "\n"
                + "Why: " + this.habitReason);
    }
}