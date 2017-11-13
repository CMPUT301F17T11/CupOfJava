package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import io.searchbox.annotations.JestId;

/**
 * This class handles all properties related to a habit.
 * A habit contains a title, reason, date, frequency.
 * User can find out if habit is followed by another
 * user or not.
 */
public class Habit {

    private String habitTitle;
    private String habitReason;
    private Calendar habitDate;
    private ArrayList<Integer> repeatingDays; //0 = Sun, 1 = Mon... 6 = Sat
    private int habitStatus = 0; //how closely the habit is being followed on a scale of 1 to 10.
                                 //for each day folloed, +1 and missing days would result in -1
                                // todo prj5
    private HabitEventHistory habitEvents;
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
     * @param title
     * @param reason
     * @param date
     */
    public Habit(String title, String reason, Calendar date){
        this.habitTitle = title;
        this.habitReason = reason;
        this.habitDate = date;
        this.repeatingDays = new ArrayList<>();
    }

    public Habit(String title, String reason, Calendar date, ArrayList<Integer> days){
        this.habitTitle = title;
        this.habitReason = reason;
        this.habitDate = date;
        this.repeatingDays = days;
    }

    public Habit(){}



    public String getHabitTitle() {
        return habitTitle;
    }

    public void setHabitTitle(String habitTitle) {
            this.habitTitle = habitTitle;
    }

    public HabitEventHistory getHabitEventHistory(){
        return this.habitEvents;
    }

    public String getHabitReason() {
        return habitReason;
    }

    public void setHabitReason(String habitReason) {

        this.habitReason = habitReason;
    }

    /**
     * checks if a certain day is a repeating day for the habit
     * @param day
     * @return
     */
    public boolean onDay(int day){
        for (int i = 0; i < repeatingDays.size(); i++){
            if (repeatingDays.get(i) == day){
                return true;
            }
        }
        return false;
    }

    public void addRepeatingDay(int day){
        Integer i = new Integer(day);
        this.repeatingDays.add(i);
    }

    /* TODO remove repeating days when the checkbox is unselected
    public void removeRepeatingDay(int day){
        for (int i)
    }
    */

    public Calendar getHabitStartDate() {
        return habitDate;
    }

    public void setHabitStartDate(Calendar habitDate) {
        this.habitDate = habitDate;
    }

    public void addHabitEvent(HabitEvent habitEvent){}

    public void deleteHabitEvent(HabitEvent habitEvent){}


    /*
     * A controller class will use these methods to keep track of how closely the habits are followed
     */

    public void habitFollowed(){
        this.habitStatus++;
        if (this.habitStatus > 10){
            this.habitStatus = 10;
        }
    }

    public void habitNotFollowed(){
        this.habitStatus--;
        if (this.habitStatus < 0){
            this.habitStatus = 0;
        }
    }

    public int getHabitStatus(){
        return habitStatus;
    }

    @Override
    public String toString(){ //this gets called by array adapter
        return ("What: " + this.habitTitle + "\n"
        + "Why: " + this.habitReason);
    }
}