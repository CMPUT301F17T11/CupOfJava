package com.cmput301f17t11.cupofjava.Controllers;

import com.cmput301f17t11.cupofjava.Models.Habit;

import java.util.Calendar;

/**
 * Created by naz_t on 12/1/2017.
 */

public class EditHabitDetailController {

    public static Habit modifyDate(Habit habit, Calendar date) {
        habit.setHabitStartDate(date);
        return habit;
    }

    public static Habit modifyHabitTitle(Habit habit, String title) {
        habit.setHabitTitle(title);
        return habit;
    }

    public static Habit modifyHabitReason(Habit habit, String reason) {
        habit.setHabitReason(reason);
        return habit;
    }

    public static void deleteHabit(Habit habit) {
        // TODO
    }

}
