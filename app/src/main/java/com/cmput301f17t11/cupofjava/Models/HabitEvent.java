/* HabitEvent
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Models;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;

import com.cmput301f17t11.cupofjava.Controllers.ImageHelper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.searchbox.annotations.JestId;

/**
 * Handles all properties of a Habit Event
 * A habit event includes a date, a name.
 * It can include option location and photo.
 *
 * @version 1.0
 */
public class HabitEvent implements Serializable {


    private String userName;
    private Habit habit;
    private String comment;
    private Date habitEventDate;
    private String habitTitle;
    private String imageString;

    @JestId
    String id; //for elasticsearch


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
     *
     * @param comment brief description of habit event
     */
    public HabitEvent(String comment){
        this.comment = comment;
        this.habitEventDate = new Date();
    }

    /**
     * Constructor for HabitEvent
     *
     * @param habit instance of Habit
     * @param comment brief description of habit event
     * @see Habit
     */
    public HabitEvent(Habit habit, String comment) {
        setHabit(habit);
        setComment(comment);
        this.habitEventDate = new Date();
        this.habit = habit;
        this.habitTitle = habit.getHabitTitle();
    }

    /**
     * Constructor for HabitEvent without comment
     */
    public HabitEvent(Habit habit){
        this.habitEventDate = new Date();
        this.habitTitle = habit.getHabitTitle();
        this.comment = "";
        this.habit = habit;
    }


    //TODO: prj5 constructor for optional comment and optional photograph

    //TODO: prj5 constructor for optional photograph

    //TODO: prj5 Constructor for optional location

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * Gets habit reason
     *
     * @return habit reason
     */
    public String getHabitReason(){
        return this.getHabitReason();
    }

    /**
     * Gets habit event date
     *
     * @return instance of Date
     */
    public Date getHabitEventDate(){
        return this.habitEventDate;
    }

    /**
     * Sets habit
     *
     * @param habit instance of Habit
     * @see Habit
     */
    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    /**
     * Gets habit
     *
     * @return instance of Habit
     * @see Habit
     */
    public Habit getHabit() {
        return habit;
    }

    public void setComment(String comment){
        if (comment.length() > 20){
            new Exception("Comment longer than 20 chars");
        }
        else {
            this.comment = comment;
        }
    }

    public String getDateAsString(){
        String dateString = new SimpleDateFormat("d MMM yyy")
                .format(this.habitEventDate);
        return dateString;
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public String getComment(){
        return this.comment;
    }

    public void setImage(Bitmap bitmap){
        this.imageString = ImageHelper.getStringFromImage(bitmap);
    }

    public void setImage(String path){
        this.imageString = ImageHelper.getStringFromImage(path);
    }

    public boolean hasImage(){
        if (this.imageString == null){
            return false;
        }
        else if (this.imageString.length() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public Bitmap getImage(){
        if (hasImage()){
            return ImageHelper.getImageFromString(this.imageString);
        }
        return null;
    }

    @Override
    public String toString(){ //this gets called by array adapter
        return ("What: " + this.habitTitle + "\n"
                + "When: " + getDateAsString());
    }
}