/* HabitEventHistory
 *
 * Version 1.0
 *
 * November 13, 2017
 *
 * Copyright (c) 2017 Cup Of Java. All rights reserved.
 */

package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Handles the list of habit events being displayed.
 * It shows the habit events in reverse chronological order so
 * the user can see the most recent habit event at the top.
 *
 * @version 1.0
 */
public class HabitEventHistory {
    ArrayList<HabitEvent> habitEvents = new ArrayList<>();

    /**
     * Empty constructor for HabitEventHistory
     */
    public HabitEventHistory() {
    }

    /**
     * Adds a habit event to the list habitEvents.
     *
     * @param event instance of HabitEvent
     * @see HabitEvent
     */
    public void addHabitEvent(HabitEvent event) {
        habitEvents.add(event);
    }

    /**
     * Deletes a habit event.
     *
     * @param index index of element in habitEvents array list
     * @see HabitEvent
     */
    public void deleteHabitEvent(int index) {
        this.habitEvents.remove(index);
    }

    /**
     * Gets index of habit event from habitEvents array list.
     *
     * @param event instance of type HabitEvent
     * @return index of event
     * @see HabitEvent
     */
    public int getIndexOfEvent(HabitEvent event) {
        return this.habitEvents.indexOf(event);
    }

    /**
     * Checks if habitEvents contains a specified event.
     *
     * @param event instance of type HabitEvent
     * @return true if event is in habitEvents, false otherwise
     * @see HabitEvent
     */
    public boolean hasHabitEvent(HabitEvent event) {
        if (this.habitEvents.contains(event)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the habit event corresponding to the specified index.
     *
     * @param index index of habit event in habitEvents array list
     * @return the event that is at the index specified
     * @see HabitEvent
     */
    public HabitEvent getHabitEvent(int index) {
        return habitEvents.get(index);
    }

    /**
     * Returns the list of habit events that are filtered by a specified comment.
     * @param text the comment by which the habit events are filtered
     * @return list of habit events which contains only the habit events that have
     *          the comment specified
     * @see HabitEvent
     */
    public ArrayList<HabitEvent> filterByComment(String text) {
        ArrayList<HabitEvent> filteredList = new ArrayList<>();
        HabitEvent currentHabitEvent;
        for (int i = 0; i < this.habitEvents.size(); i++) {
            currentHabitEvent = this.habitEvents.get(i);
            if (currentHabitEvent.getComment().indexOf(text) != -1) {
                filteredList.add(currentHabitEvent);
            }
        }
        return filteredList;
    }

    /**
     * Gets the list of habit events.
     *
     * @return array list of habit events
     * @see HabitEvent
     */
    public ArrayList<HabitEvent> getHabitEvents() {
        return habitEvents;
    }

    /**
     * Sorts the list of habit events by a specified date.
     *
     * @return sorted habitEvents
     * @see HabitEvent
     */
    public ArrayList<HabitEvent> getListSortedByDate() {
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

    /**
     * Sorts the list of habit events by type.
     *
     * @param habit instance of Habit class
     * @return sorted habitEvents
     * @see Habit
     * @see HabitEvent
     */
    public ArrayList<HabitEvent> filterByType(Habit habit) {
        ArrayList<HabitEvent> filteredHabitEvents = new ArrayList<HabitEvent>();
        for (int i = 0; i < habitEvents.size(); i++) {
            if(habitEvents.get(i).getHabit()!= null)
            {
                if (habitEvents.get(i).getHabit().equals(habit)) {
                    filteredHabitEvents.add(habitEvents.get(i));
                }

            }
        }
        return filteredHabitEvents;
    }
}
