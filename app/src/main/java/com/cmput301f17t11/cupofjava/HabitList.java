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

    public void deleteHabit(int index){
        this.habits.remove(index);
    }

    public int getHabitIneex(Habit habit){
        return this.habits.indexOf(habit);
    }

    public ArrayList<Habit> getHabitList(void){
        return this.habits;
    }
}
