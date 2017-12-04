package com.cmput301f17t11.cupofjava.Controllers;

import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by naz_t on 12/3/2017.
 */

public class EventFilteringHelper {

    public static ArrayList<HabitEvent> reverseChronological(ArrayList<HabitEvent> events){

        Collections.sort(events, new Comparator<HabitEvent>() {
            @Override
            public int compare(HabitEvent o1, HabitEvent o2) {
                if (o1.getHabitEventDate().getTime() < o2.getHabitEventDate().getTime()){
                    return 1;
                }
                else if (o1.getHabitEventDate().getTime() == o2.getHabitEventDate().getTime()){
                    return 0;
                }
                else return -1;

            }
        });
        return events;
    }

    public static ArrayList<HabitEvent> filterByComment(ArrayList<HabitEvent> events, String str){
        ArrayList<HabitEvent> filteredEvents = new ArrayList<>();
        for(int i = 0; i < events.size(); i++){
            if (events.get(i).getComment().contains(str)){
                filteredEvents.add(events.get(i));
            }
        }
        return filteredEvents;
    }

    public static ArrayList<HabitEvent> filterByType(ArrayList<HabitEvent> events, String habitTitle){
        ArrayList<HabitEvent> filteredEvents = new ArrayList<>();

        for (int i = 0; i < events.size(); i++){
            if (events.get(i).getHabitTitle().equals(habitTitle)){
                filteredEvents.add(events.get(i));
            }
        }

        return filteredEvents;
    }
}
