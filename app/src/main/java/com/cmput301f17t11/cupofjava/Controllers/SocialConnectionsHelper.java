package com.cmput301f17t11.cupofjava.Controllers;

import android.util.Log;

import com.cmput301f17t11.cupofjava.Models.User;

/**
 * Created by naz_t on 12/1/2017.
 */

public class SocialConnectionsHelper {

    /**
     * Method to use to ensure a valid user is being search for
     * @param searcheeName
     * @return
     */
    public static boolean isValidSearch(String searcherName, String searcheeName){
        if (searcherName.equals(searcheeName)){
            return false;
        }
        User user;
        ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
        getUserTask.execute(searcheeName);
        try{
            user = getUserTask.get();
            if (user == null){
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e){
            Log.i("isValidSearch failed", e.toString());
            return false;
        }

    }

    public static User getUser(String searcheeName){
        User user;
        ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
        getUserTask.execute(searcheeName);
        try{
            user = getUserTask.get();
            return user;
        }
        catch (Exception e){
            Log.i("getUser failed", e.toString());
            return null;
        }
    }
}
