package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Date;

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
    private Date habitStartDate;
    private ArrayList<Integer> repeatingDays; //0 = Sun, 1 = Mon... 6 = Sat
    private int habitStatus = 0; //how closely the habit is being followed on a scale of 1 to 10.
                                 //for each day folloed, +1 and missing days would result in -1
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
    public Habit(String title, String reason, Date date){
        this.habitTitle = title;
        this.habitReason = reason;
        //TODO handle date
        this.habitStartDate = date;
    }

    public Habit(){}

    public String getHabitTitle() {
        return habitTitle;
    }

    public void setHabitTitle(String habitTitle) {
        if (habitTitle.length() > 20){
            new Exception("Habit Title longer than 20 chars");
        }
        else {
            this.habitTitle = habitTitle;
        }
    }

    public HabitEventHistory getHabitEventHistory(){
        return this.habitEvents;
    }

    public String getHabitReason() {
        return habitReason;
    }

    public void setHabitReason(String habitReason) {
        if (habitReason.length() > 30){
            new Exception("Habit Reason longer than 30 chars");
        }
        else {
            this.habitReason = habitReason;
        }
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

    public Date getHabitStartDate() {
        return habitStartDate;
    }

    public void setHabitStartDate(Date habitStartDate) {
        this.habitStartDate = habitStartDate;
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
}
