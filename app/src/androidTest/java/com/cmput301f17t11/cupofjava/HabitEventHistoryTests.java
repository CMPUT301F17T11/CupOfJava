package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.Models.HabitEventHistory;
import com.cmput301f17t11.cupofjava.Views.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class HabitEventHistoryTests extends ActivityInstrumentationTestCase2 {
    public HabitEventHistoryTests(){
        super(MainActivity.class);
    }

    public void testAddHabitEvent(){
        //Calendar cal = Calendar.getInstance();
        //Date date = cal.getTime();

        Habit habit = new Habit("adding habit", "for test", Calendar.getInstance());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        assertTrue(eventHistory.hasHabitEvent(event));
    }

    public void testDelete(){
        Habit habit = new Habit("adding habit", "for test",Calendar.getInstance());
        HabitEvent event = new HabitEvent(habit, "comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        eventHistory.deleteHabitEvent(0);
        assertFalse(eventHistory.hasHabitEvent(event));
    }

    public void testGetHabitEvent(){
        //Habit habit = new Habit("adding habit", "for test",Calendar.getInstance());
        HabitEvent event = new HabitEvent("comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        HabitEvent returnedHabitEvent = eventHistory.getHabitEvent(0);
        //assertEquals(returnedHabitEvent., event.getHabitObject());
        assertEquals(returnedHabitEvent.getComment(),"comment1");

    }

    public void testHasHabitEvent(){
        //Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent("comment1");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        assertTrue(eventHistory.hasHabitEvent(event));
    }

    /*public void testHaslocation(){
        Geolocation geo = new Geolocation(getActivity());
        geo.setLocation(new Location("ServiceProvider"));


        Habit habit = new Habit("adding habit", "for test",new Date());
        HabitEvent event = new HabitEvent(habit, "comment1");
        //event.setLocation(geo);
        HabitEventHistory eventHistory = new HabitEventHistory();
        eventHistory.addHabitEvent(event);

        assertTrue(eventHistory.getHabitEvent(0).hasLocation());

    }*/

    public void testListSortedByDate(){ //TODO implement this test method
        //Habit habit = new Habit("adding habit", "for test",Calendar.getInstance());
        HabitEvent event = new HabitEvent("comment1");
        HabitEvent event2 = new HabitEvent("comment2");

        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        eventHistory.addHabitEvent(event2);

        ArrayList<HabitEvent> habitEvents = new ArrayList<>();
        habitEvents.add(event);
        habitEvents.add(event2);

        ArrayList<HabitEvent> returnedEventList = eventHistory.getListSortedByDate();

        //do sorting of evenHistory out here
        Collections.sort(habitEvents, new Comparator<HabitEvent>() {
            public int compare(HabitEvent o1, HabitEvent o2) {
                if (o1.getHabitEventDate() == null || o2.getHabitEventDate() == null) {
                    return 0;
                }
                return o1.getHabitEventDate().compareTo(o2.getHabitEventDate());
            }
        });


        assertEquals(returnedEventList, habitEvents);



    }

    public void testFilterByComment(){

        HabitEvent event = new HabitEvent("comment1");
        HabitEvent event2 = new HabitEvent("comment2");
        HabitEventHistory eventHistory = new HabitEventHistory();
        eventHistory.addHabitEvent(event);
        eventHistory.addHabitEvent(event2);
        ArrayList<HabitEvent> returnedEventList = eventHistory.filterByComment("comment1");


        assertEquals(returnedEventList.get(0), event);
    }

    public void testFilterBType(){ //TODO implement this test method
        Habit habit = new Habit("adding habit", "for test",Calendar.getInstance());
        Habit habit2 = new Habit("adding habit2", "for test2",Calendar.getInstance());
        HabitEvent event = new HabitEvent(habit,"comment1");
        HabitEvent event2 = new HabitEvent(habit2,"comment2");
        HabitEventHistory eventHistory = new HabitEventHistory();

        eventHistory.addHabitEvent(event);
        eventHistory.addHabitEvent(event2);

        ArrayList<HabitEvent> returnedEventList = eventHistory.filterByType(habit);

        //do filtering out here
        assertEquals(returnedEventList.get(0), event);
    }
}
