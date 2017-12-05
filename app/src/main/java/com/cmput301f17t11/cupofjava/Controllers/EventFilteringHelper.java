/* EventFilteringHelper
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava.Controllers;

import android.util.Log;

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

    public static ArrayList<HabitEvent> getHabitEventsOfHabit(String userName, Habit habit) {
        ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
        getEventsTask.execute(userName);
        ArrayList<HabitEvent> myHabitEvents = new ArrayList<>();

        try {
            //all habit events of the user
            ArrayList<HabitEvent> allHabitEvents = getEventsTask.get();

            if (!allHabitEvents.isEmpty()) {
                //filter it for habit events specific to the habit
                for (int i = 0; i < allHabitEvents.size(); i++) {
                    if (allHabitEvents.get(i).getHabitTitle().equals(habit.getHabitTitle())) {
                        myHabitEvents.add(allHabitEvents.get(i));
                    }
                }
                Log.i("HabitDetailView: habitEvents of habit: ", "found some" + myHabitEvents.toString());

            } else {
                Log.i("HabitDetailView: habitEvents of habit: ", "found none");

            }
        } catch (Exception e) {
            Log.i("HabitDetailView: ", e.toString());

        }
        return myHabitEvents;

    }
}
