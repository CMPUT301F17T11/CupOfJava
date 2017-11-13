package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void deleteHabitEvent(int index){
        this.habitEvents.remove(index);
    }

    public int getIndexOfEvent(HabitEvent event){
        return this.habitEvents.indexOf(event);
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

    public HabitEvent getHabitEvent(int index) { return habitEvents.get(index);}

    public ArrayList<HabitEvent> filterByComment(String text){
        ArrayList<HabitEvent> filteredList = new ArrayList<>();
        HabitEvent currentHabitEvent;
        for (int i = 0; i < this.habitEvents.size(); i++){
            currentHabitEvent = this.habitEvents.get(i);
            if (currentHabitEvent.getComment().indexOf(text) != -1){
                filteredList.add(currentHabitEvent);
            }
        }

        return filteredList;
    }

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

    /*
    public ArrayList<HabitEvent> filterByType(Habit habit){
        ArrayList<HabitEvent> filteredHabitEvents = new ArrayList<HabitEvent>();
        for (int i = 0; i < habitEvents.size(); i++) {
            if (habitEvents.get(i).getHabitObject().getHabitType().equals(habit.getHabitType())) {
                filteredHabitEvents.add(habitEvents.get(i));
            }
        }
        return filteredHabitEvents;

    /*
    public ArrayList<HabitEvent> filterByComment(String comment){
        ArrayList<HabitEvent> filteredHabitEvents = new ArrayList<HabitEvent>();
        for (int i = 0; i < habitEvents.size(); i++) {
            if (habitEvents.get(i).getComment().equals(comment)) {
                filteredHabitEvents.add(habitEvents.get(i));
            }
        }
        return filteredHabitEvents;
    }*/

}
