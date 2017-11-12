package com.cmput301f17t11.cupofjava;


import java.util.Date;

/**
 * This class handles all properties of a Habit Event
 * A habit event includes a date, a name.
 * It can include option location and photo.
 */
public class HabitEvent {
    private String comment;
    private Date habitEventDate;
    /** TODO: prj5
    private Geolocation location;
    private boolean locationSet = false;
    private Bitmap photo;
    public HabitEvent(Habit habit){
        this.habitObject = habit;
        this.habitEventDate = new Date();
    }

     */

    /**
     * Constructor for HabitEvent
     * @param comment
     */
    public HabitEvent(String comment){
        this.comment = comment;
    }

    /**
     * Constructor for HabitEvent without comment
     */
    public HabitEvent(){}


    //TODO: prj5 constructor for optional comment and optional photograph

    //TODO: prj5 constructor for optional photograph

    //TODO: prj5 Constructor for optional location


    public String getHabitReason(){
        return this.getHabitReason();
    }

    public Date getHabitEventDate(){
        return this.habitEventDate;
    }

    public void setHabitEventDateToCurrent(){
        this.habitEventDate = new Date();
    }

    public void setComment(String comment){
        if (comment.length() > 20){
            new Exception("Comment longer than 20 chars");
        }
        else {
            this.comment = comment;
        }
    }

    public String getComment(){
        return this.comment;
    }


}
