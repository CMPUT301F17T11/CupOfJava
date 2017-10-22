package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;

/**
 * Created by nazim on 21/10/17.
 */

public class HabitEventHistory {
    ArrayList<HabitEvent> habitEvents;

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
    //this method was added by eshna
    HabitEvent getHabitEvent(int index) { return habitEvents.get(index);}

    public ArrayList<HabitEvent> getHabitEvents(){
        return habitEvents;
    }

    //TODO: implement reverse chronological ordering of events
    public ArrayList<HabitEvent> getListSortedByDate(){return habitEvents;}

    //TODO: implement filter by habit type
    public ArrayList<HabitEvent> filterByType(Habit habit){ return habitEvents;}

    //TODO: implement filter by keyword in comment
    public ArrayList<HabitEvent> filterByComment(String string){return habitEvents;}

}
