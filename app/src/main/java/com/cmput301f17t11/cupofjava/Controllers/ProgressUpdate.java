package com.cmput301f17t11.cupofjava.Controllers;


import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by naz_t on 12/4/2017.
 */

public class ProgressUpdate {

    public static int getProgress(Habit habit,ArrayList<HabitEvent> events){
        if (events.isEmpty()){
            return 20;
        }

        Date startDate = habit.getHabitStartDate().getTime();
        events = EventFilteringHelper.reverseChronological(events);
        HabitEvent event;
        int status = 20;
        for (int i = 0; i < events.size(); i++) {
            event = events.get(i);
            if (event.getHabitEventDate().getTime() > startDate.getTime()) {
                if (habit.getRepeatingDays()
                        .contains(Integer.valueOf(event.getHabitEventDate().getDay()))) {
                    status += 10;
                }
            }
        }

        return status;

    }
}
