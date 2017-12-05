package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitList;
import com.cmput301f17t11.cupofjava.Views.UserLoginActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class HabitListTest extends ActivityInstrumentationTestCase2 {
    public HabitListTest(){
        super(UserLoginActivity.class);
    }

    public void testAddHabit(){
        HabitList habits = new HabitList();
        Habit habit = new Habit("adding habit", "for test",Calendar.getInstance());
        habits.addHabit(habit);
        assertTrue(habits.habitExists(habit));
    }

   public void testDelete(){
        HabitList list = new HabitList();
        Habit habit = new Habit("test","for test",Calendar.getInstance());
        list.addHabit(habit);
        list.deleteHabit(habit);
        assertFalse(list.habitExists(habit));
    }

   public void testGetHabit(){
        HabitList habits = new HabitList(); //
        Habit habit = new Habit("test", "for test", Calendar.getInstance());
        habits.addHabit(habit);
        Habit returnedHabit = habits.getHabit(0);
        assertEquals(returnedHabit.getHabitTitle(), habit.getHabitTitle());
        assertEquals(returnedHabit.getHabitReason(), habit.getHabitReason());
        assertEquals(returnedHabit.getHabitStartDate(), habit.getHabitStartDate());

    }

    public void testHasHabit(){
        HabitList list = new HabitList();
        Habit habit = new Habit("test","for test", Calendar.getInstance());
        list.addHabit(habit);
        assertTrue(list.habitExists(habit));
    }
    public void testGetHabitList(){

        HabitList list = new HabitList();
        Habit habit = new Habit("test", "for test", Calendar.getInstance());
        Habit habit2 = new Habit("test2", "for test",Calendar.getInstance());
        ArrayList<Habit> myhabits = new ArrayList<>();
        myhabits.add(habit);
        myhabits.add(habit2);

        list.addHabit(habit);
        list.addHabit(habit2);

        ArrayList<Habit> returnedUserList = list.getHabitListAsArray();

       assertEquals(returnedUserList, myhabits);

    }

    public void testTodaysHabitList(){
        HabitList list = new HabitList();
        Habit habit = new Habit("test","for test",Calendar.getInstance());
        list.addHabit(habit);

        ArrayList<Habit> returnedHabitList = list.getTodaysHabitList();
        for(int i = 0 ; i < returnedHabitList.size(); i++) {
            assertEquals(returnedHabitList.get(i).getHabitStartDate(), Calendar.getInstance());

        }
    }
}
