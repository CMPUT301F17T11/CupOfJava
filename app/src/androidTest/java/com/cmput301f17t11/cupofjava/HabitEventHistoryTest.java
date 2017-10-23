package com.cmput301f17t11.cupofjava;

import android.content.Context;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
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

    public void testHaslocation(){
        Geolocation geo = new Geolocation(getActivity());
        geo.setLocation(new Location("ServiceProvider"));


        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        event.setLocation(geo);
        HabitEventHistory eventHistory = new HabitEventHistory();
        eventHistory.addHabitEvent(event);

        assertTrue(eventHistory.getHabitEvent(0).hasLocation());

    }

    public void testListSortedByDate(){ //TODO implement this test method
        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEvent event2 = new HabitEvent(habit);

        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        eventHistory.addHabitEvent(event2);

        ArrayList<HabitEvent> returnedEventList = eventHistory.getListSortedByDate();

        /* do sorting of evenHistory out here*/

        assertEquals(returnedEventList, eventHistory.habitEvents);



    }

    public void testFilterByComment(){  //TODO implement this test method
        HabitEventHistory eventHistory = new HabitEventHistory();
        ArrayList<HabitEvent> returnedEventList = eventHistory.filterByComment("comment1");

        /*do filtering out here*/
        assertEquals(returnedEventList, eventHistory.habitEvents);
    }

    public void testFilterBType(){ //TODO implement this test method
        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEventHistory eventHistory = new HabitEventHistory();
        ArrayList<HabitEvent> returnedEventList = eventHistory.filterByType(habit);

        /*do filtering out here*/
        assertEquals(returnedEventList, eventHistory.habitEvents);
    }
}
