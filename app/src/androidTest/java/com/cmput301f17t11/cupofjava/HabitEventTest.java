package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.robotium.solo.Solo;

/**
 * Created by Sajjad on 2017-12-04.
 */

public class HabitEventTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public HabitEventTest() {
        super(HabitEvent.class);
    }

    /**
     * Test for username
     */

    public void testGetName() {
        String name = "test name";
        Habit habit = new Habit("testingg","testingg");
        HabitEvent habitEvent = new HabitEvent(habit);
        habitEvent.setUserName(name);
        assertEquals(habitEvent.getUserName(),name);
    }

    /**
     * Test for Getting habit
     */
    public void testGetHabit() {
        Habit habit = new Habit("testingg","testingg");
        HabitEvent habitEvent = new HabitEvent(habit);
        assertEquals(habitEvent.getHabit(),habit);
    }
}
