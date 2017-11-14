/* HabitList
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Handles the list of habits and the previews in the list.
 * Also handles appending and deleting from the list.
 * Displays habits in reverse chronological order.
 *
 * @version 1.0
 * @see Habit
 */

public class HabitList {
    private ArrayList<Habit> habits;

    /**
     * Constructor for HabitList class.
     * Instantiates habits to an array list of type Habit.
     */
    public HabitList(){
        this.habits = new ArrayList<Habit>();
    }

    /**
     * Adds a habit to habits array list.
     *
     * @param habit instance of type Habit
     */
    public void addHabit(Habit habit){
        this.habits.add(habit);
    }

    /**
     * Checks if a habit exists in habits or not.
     *
     * @param habit instance of type Habit
     * @return true if habit exists in habits, false otherwise
     */
    public boolean habitExists(Habit habit){
        if (this.habits.contains(habit)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Gets habit from array list of habits corresponding to given index.
     *
     * @param index integer
     * @return habit in habits at the given index
     */
    public Habit getHabit(int index) {
        return habits.get(index);
    }

    /**
     * Deletes habit from array list of habits corresponding to given index.
     *
     * @param index integer
     */
    public void deleteHabit(int index){
        this.habits.remove(index);
    }

    /**
     * Returns habits as an array list.
     *
     * @return array list of type Habit
     */
    public ArrayList<Habit> getHabitListAsArray(){
        return this.habits;
    }

    //TODO
    /*public int getIndexByName(String title){
        for
    }*/

    /**
     * Gets the habits that the user needs to complete today.
     *
     * @return array list of type Habit
     */
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