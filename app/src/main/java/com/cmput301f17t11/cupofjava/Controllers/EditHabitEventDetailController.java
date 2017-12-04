package com.cmput301f17t11.cupofjava.Controllers;

import android.location.Location;

import com.cmput301f17t11.cupofjava.Models.HabitEvent;

/**
 * Created by naz_t on 12/1/2017.
 */

//TODO supposed to update in ES

public class EditHabitEventDetailController {

    public static HabitEvent modifyComment(HabitEvent habitEvent, String comment){
        habitEvent.setComment(comment);
        return habitEvent;
    }

    public static HabitEvent modifyLocation(HabitEvent habitEvent, Location location){
        habitEvent.setLocation(location);
        return habitEvent;
    }

    public static HabitEvent modifyPicture(HabitEvent habitEvent, String path){
        habitEvent.setImage(path);
        return habitEvent;
    }

    public static void deleteHabitEvent(HabitEvent habitEvent){
        //TODO
    }
}
