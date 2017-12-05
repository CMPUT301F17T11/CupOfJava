package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301f17t11.cupofjava.Models.Habit;

import java.util.Calendar;

/**
 * Created by Sajjad on 2017-12-04.
 */

public class HabitTest extends ActivityInstrumentationTestCase2 {

    public HabitTest(){
        super(Habit.class);
    }

    /**
     * Get habit title
     */
    public void testGetTitle() {
        String habitTitle = "bballtest";
        Habit habit = new Habit(habitTitle,"testing", Calendar.getInstance());
        assertEquals(habit.getHabitTitle(),habitTitle);
    }

    /**
     * Set habit title
     */
    public void testSetTitle() {
        String habitTitle = "bballtest";
        Habit habit = new Habit();
        habit.setHabitTitle(habitTitle);
        assertEquals(habit.getHabitTitle(),habitTitle);
    }

    /**
     * Testing for habit get reason
     */
    public void testGetReason() {
        String habitReason = "to improve";
        Habit habit = new Habit();
        habit.setHabitReason(habitReason);
        assertEquals(habit.getHabitReason(),habitReason);
    }

    /**
     * Testing for setting the habit reason
     */
    public void testSetReason () {
        String habitReason = "to improve";
        Habit habit = new Habit();
        habit.setHabitReason(habitReason);
        assertEquals(habit.getHabitReason(), habitReason);
    }
}
