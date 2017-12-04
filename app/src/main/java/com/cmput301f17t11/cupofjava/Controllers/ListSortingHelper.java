package com.cmput301f17t11.cupofjava.Controllers;

import com.cmput301f17t11.cupofjava.Models.HabitEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This helper class has static methods that accepts arraylists, and returns
 * the array list sorted in different ways. An example is a reverse chronological
 * ordering of HabitEvents
 */

public class ListSortingHelper {

    public static ArrayList<HabitEvent> chronologicalSort(ArrayList<HabitEvent> habitEvents) {
        Collections.sort(habitEvents, new Comparator<HabitEvent>() {
            public int compare(HabitEvent o1, HabitEvent o2) {
                if (o1.getHabitEventDate() == null || o2.getHabitEventDate() == null) {
                    return 0;
                }
                return o2.getHabitEventDate().compareTo(o1.getHabitEventDate());
            }
        });
        return habitEvents;
    }

    public static ArrayList<HabitEvent> reverseChronological(ArrayList<HabitEvent> habitEvents) {
        Collections.sort(habitEvents, new Comparator<HabitEvent>() {
            public int compare(HabitEvent o1, HabitEvent o2) {
                if (o1.getHabitEventDate() == null || o2.getHabitEventDate() == null) {
                    return 0;
                }
                return o1.getHabitEventDate().compareTo(o2.getHabitEventDate());
            }
        });
        return habitEvents;
    }

    public static ArrayList<HabitEvent> alphabeticalTitleSort(ArrayList<HabitEvent> habitEvents) {
        Collections.sort(habitEvents, new Comparator<HabitEvent>() {
            @Override
            public int compare(HabitEvent o1, HabitEvent o2) {
                if (o1.getHabitTitle() == null || o2.getHabitTitle() == null) {
                    return 0;
                }
                return o1.getHabitTitle().compareToIgnoreCase(o2.getHabitTitle());
            }
        });
        return habitEvents;
    }
}
