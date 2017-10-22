package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

public class HabitEventHistoryTest extends ActivityInstrumentationTestCase2 {
    public HabitEventHistoryTest(){
        super(com.cmput301f17t11.cupofjava.MainActivity.class);
    }

    public void testAddHabitEvent(){
        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        assertTrue(eventHistory.hasHabitEvent(event));
    }

    public void testDelete(){
        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        eventHistory.deleteHabitEvent(event);
        assertFalse(eventHistory.hasHabitEvent(event));
    }

    public void testGetHabitEvent(){
        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        HabitEvent returnedHabitEvent = eventHistory.getHabitEvent(0);
        assertEquals(returnedHabitEvent.getHabitObject(), event.getHabitObject());
        assertEquals(returnedHabitEvent.getComment(), event.getComment());

    }

    public void testHasHabit(){
        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        assertTrue(eventHistory.hasHabitEvent(event));
    }
}
