package com.cmput301f17t11.cupofjava;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class handles all properties of a Habit Event
 * A habit event includes a date, a name.
 * It can include option location and photo.
 */
public class HabitEvent {
    private Habit habit;
    private String habitTitle;
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
     * Constructor for HabitEvent. Only used for unit testing.
     * @param comment
     */
    public HabitEvent(String comment){
        this.comment = comment;
        this.habitEventDate = new Date();
    }

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


    public String getHabitReason(){
        return this.getHabitReason();
    }

    public Date getHabitEventDate(){
        return this.habitEventDate;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

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

    public String getComment(){
        return this.comment;
    }

    @Override
    public String toString(){ //this gets called by array adapter
        return ("What: " + this.habitTitle + "\n"
                + "When: " + getDateAsString());
    }
}
