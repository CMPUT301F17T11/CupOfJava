package com.cmput301f17t11.cupofjava.Controllers;

import android.location.Location;

import com.cmput301f17t11.cupofjava.Models.HabitEvent;

/**
 * Created by naz_t on 12/1/2017.
 */
public class EditHabitEventDetailController {
    private static void deleteEventFromES(String id){
        ElasticsearchController.DeleteEventTask2 deleteEventTask
                = new ElasticsearchController.DeleteEventTask2();
        deleteEventTask.execute(id);
    }

    private static void addEventToES(HabitEvent event){
        ElasticsearchController.AddEventTask addEventTask
                = new ElasticsearchController.AddEventTask();

        addEventTask.execute(event);
    }

    public static void modifyComment(HabitEvent habitEvent, String comment){
        deleteEventFromES(habitEvent.getId());
        habitEvent.setComment(comment);
        addEventToES(habitEvent);
    }

    public static void modifyLocation(HabitEvent habitEvent, Location location){
        deleteEventFromES(habitEvent.getId());
        habitEvent.setLocation(location);

    }

    public static void modifyPicture(HabitEvent habitEvent, String path){
        habitEvent.setImage(path);

    }
}
