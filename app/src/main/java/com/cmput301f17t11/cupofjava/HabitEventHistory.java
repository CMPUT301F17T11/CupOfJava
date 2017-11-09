package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * This class handles the list of habit events being displayed.
 * It shows the habit events in reverse chronological order so
 * the user can see the most recent at the top.
 */
public class HabitEventHistory {
    ArrayList<HabitEvent> habitEvents;

    /**
     * Constructor
     */
    public HabitEventHistory(){}

    public void addHabitEvent(HabitEvent event){
        habitEvents.add(event);
    }

    public void deleteHabitEvent(HabitEvent habitEvent){
        int index = this.habitEvents.indexOf(habitEvent);
        this.habitEvents.remove(index);
    }
    //this method was added by eshna
    public boolean hasHabitEvent(HabitEvent event){
        if (this.habitEvents.contains(event)){
            return true;
        }
        else{
            return false;
        }
    }

    HabitEvent getHabitEvent(int index) { return habitEvents.get(index);}


    public ArrayList<HabitEvent> getHabitEvents(){
        return habitEvents;
    }

    //TODO: DONE (need to test)
    public ArrayList<HabitEvent> getListSortedByDate(){
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

    //TODO: implement filter by habit type (need to finish)
    public ArrayList<HabitEvent> filterByType(Habit habit, String type){
        ArrayList<HabitEvent> filteredHabitEvents = new ArrayList<HabitEvent>();
        for (int i = 0; i < habitEvents.size(); i++) {
            if (habit.getHabitType() == type) {
                filteredHabitEvents.add(habitEvents[i].gethabit
            }
            return habitEvents;
        }

    }

    //TODO: implement filter by keyword in comment
    public ArrayList<HabitEvent> filterByComment(String string){return habitEvents;}

}
