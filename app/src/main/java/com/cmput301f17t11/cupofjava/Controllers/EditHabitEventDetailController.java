package com.cmput301f17t11.cupofjava.Controllers;

import com.cmput301f17t11.cupofjava.Models.HabitEvent;

/**
 * Created by naz_t on 12/1/2017.
 */

public class EditHabitEventDetailController {

    public static HabitEvent modifyComment(HabitEvent habitEvent, String comment){
        habitEvent.setComment(comment);
        return habitEvent;
    }

    public static HabitEvent modifyLocation(HabitEvent habitEvent){
        //TODO
        return habitEvent;
    }

    public static HabitEvent modifyPicture(HabitEvent habitEvent){
        //TODO
        return habitEvent;
    }

    public static void deleteHabitEvent(HabitEvent habitEvent){
        //TODO
    }
}
