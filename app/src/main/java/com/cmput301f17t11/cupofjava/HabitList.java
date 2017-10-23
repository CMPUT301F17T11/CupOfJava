package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;

/**
 * This class handles the list of habits and the previews
 * in the list.
 * Also handles appending and deleting from the list.
 * Displays habits in reverse chronological order.
 */
public class HabitList {
    String username;
    ArrayList<Habit> habits;

    public HabitList(String username){
        this.username = username;
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
    //this method was added by eshna
    Habit getHabit(int index) { return habits.get(index);}

    public void deleteHabit(Habit habit){
        this.habits.remove(habit);
    }

    public int getHabitIndex(Habit habit){
        return this.habits.indexOf(habit);
    }

    public ArrayList<Habit> getHabitList(){
        return this.habits;
    }

    public ArrayList<Habit> getTodaysHabitList(){
        ArrayList<Habit> todaysHabits = new ArrayList<>();
        //TODO: Implement find habits by day
        return todaysHabits;
    }
}
