/* EditHabitDetailController
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Controllers;

import com.cmput301f17t11.cupofjava.Models.Habit;

import java.util.Calendar;

/**
 * Created by naz_t on 12/1/2017.
 */

public class EditHabitDetailController {

    public static void modifyDate(Habit habit, Calendar newDate){
        ElasticsearchController.DeleteHabitsTask deleteHabitsTask = new ElasticsearchController.DeleteHabitsTask();
        deleteHabitsTask.execute(habit);
        habit.setHabitStartDate(newDate);

        ElasticsearchController.AddHabitTask addHabitTask = new ElasticsearchController.AddHabitTask();
        addHabitTask.execute(habit);
    }

    public static void modifyHabitReason(Habit habit, String newReason) {
        ElasticsearchController.DeleteHabitsTask deleteHabitsTask = new ElasticsearchController.DeleteHabitsTask();
        deleteHabitsTask.execute(habit);
        habit.setHabitReason(newReason);

        ElasticsearchController.AddHabitTask addHabitTask = new ElasticsearchController.AddHabitTask();
        addHabitTask.execute(habit);
    }
}