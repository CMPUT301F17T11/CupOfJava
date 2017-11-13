package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class handles the list of habits and the previews
 * in the list.
 * Also handles appending and deleting from the list.
 * Displays habits in reverse chronological order.
 */

public class HabitList {
    ArrayList<Habit> habits;


    public HabitList(){
        this.habits = new ArrayList<Habit>();
    }

    public void addHabit(Habit habit){
        this.habits.add(habit);
    }

    public boolean habitExists(Habit habit){
        if (this.habits.contains(habit)){
            return true;
        }
        else{
            return false;
        }
    }

    public Habit getHabit(int index) {
        return habits.get(index);
    }

    public void deleteHabit(int index){
        this.habits.remove(index);
    }

    public ArrayList<Habit> getHabitListAsArray(){
        return this.habits;
    }

    //TODO
    /*public int getIndexByName(String title){
        for
    }*/

    public ArrayList<Habit> getTodaysHabitList(){
        Calendar calendar = Calendar.getInstance();
        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int currentDay;
        switch (dayOfTheWeek) {
            case Calendar.SUNDAY:
                currentDay = 0;
                break;
            case Calendar.MONDAY:
                currentDay = 1;
                break;
            case Calendar.TUESDAY:
                currentDay = 2;
                break;
            case Calendar.WEDNESDAY:
                currentDay = 3;
                break;
            case Calendar.THURSDAY:
                currentDay = 4;
                break;
            case Calendar.FRIDAY:
                currentDay = 5;
                break;
            case Calendar.SATURDAY:
                currentDay = 6;
                break;
            default:
                currentDay = 0;
                break;
        }

        ArrayList<Habit> todaysHabits = new ArrayList<>();
        Habit currentHabit;

        for (int i = 0; i < habits.size(); i++){
            currentHabit = habits.get(i);
            if (currentHabit.onDay(currentDay)){
                todaysHabits.add(currentHabit);
            }
        }

        return todaysHabits;
    }
}