package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.cmput301f17t11.cupofjava.Controllers.ElasticsearchController;
import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitEvent;
import com.cmput301f17t11.cupofjava.Models.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sajjad on 2017-12-04.
 */

public class ElasticSearchControllerTest extends ActivityInstrumentationTestCase2 {

    Date date = Calendar.getInstance().getTime();
    public ElasticSearchControllerTest(){
        super(ElasticsearchController.class);
    }

    /**
     * Add User Task
     */
    public void testAddUserTask(){
        try{
            User user = new User("ElasticTest");
            ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
            getUserTask.execute("ElasticTest");
            User testGet = getUserTask.get();
            if (testGet != null){
                Log.i("Add User Test","User exists on Elastic Search");
            }else{
                ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
                addUserTask.execute(user);
            }
        }catch (Exception e){
        }
    }

    /**
     * Get User task
     */
    public void testGetUserTask(){
        try{
            User user = new User("Joe");
            ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
            getUserTask.execute("ElasticTest");
            user = getUserTask.get();
            Log.i("Get User Test",user.getUsername().toString());
        }catch (Exception e){
        }
    }

    /**
     * Test add Habits
     */
    public void testAddHabitsTask(){
        try{
            Habit habit = new Habit("test", "test");
            habit.setUsername("ElasticTest");
            ElasticsearchController.AddHabitTask addHabitsTask = new ElasticsearchController.AddHabitTask();
            addHabitsTask.execute(habit);
        }catch (Exception e){
        }

    }

    /**
     * Get Habits
     */
    public void testGetHabitsTask(){
        ElasticsearchController.GetHabitsTask getHabitsTask = new ElasticsearchController.GetHabitsTask();
        getHabitsTask.execute("ElasticTest");
        ArrayList<Habit> habitList = new ArrayList<>();
        try{
            habitList = getHabitsTask.get();
        } catch (Exception e){
        }
        Log.i("Get Habits Task",habitList.toString());

    }


    /**
     * Add Habit Event
     */
    public void testAddHabitEventTask(){
        try{
            Habit habit = new Habit("testing","testing");
            HabitEvent habitEvent = new HabitEvent(habit, "testing");
            habitEvent.setUserName("ElasticTest");
            ElasticsearchController.AddEventTask addEventTask = new ElasticsearchController.AddEventTask();
            addEventTask.execute(habitEvent);
        }catch (Exception e){
        }

    }

    /**
     * Get Events
     */
    public void testGetEventsTask(){
        try{
            ElasticsearchController.GetEventsTask getEventsTask = new ElasticsearchController.GetEventsTask();
            getEventsTask.execute("ElasticTest");
            ArrayList<HabitEvent> habitEventList = new ArrayList<>();
            habitEventList = getEventsTask.get();
            Log.i("Get Events",habitEventList.toString());
        }catch (Exception e){
        }

    }
}
