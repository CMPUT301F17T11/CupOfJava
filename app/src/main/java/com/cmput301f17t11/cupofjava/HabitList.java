package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;

/**
 * Created by nazim on 21/10/17.
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
}
