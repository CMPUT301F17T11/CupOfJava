package com.cmput301f17t11.cupofjava.Controllers;

import android.util.Log;

import com.cmput301f17t11.cupofjava.Models.User;

/**
 * Created by naz_t on 11/30/2017.
 */

public class UserSearchController {
    private String searcherName;
    private String searcheeName;
    /**
     * Constructor, accepts the username of the current user
     * @param searcherName
     */
    public UserSearchController(String searcherName){
        this.searcherName = searcherName;
    }

    /**
     * Method to use to ensure a valid user is being search for
     * @param searcheeName
     * @return
     */
    public boolean isValidSearch(String searcheeName){
        if (this.searcherName.equals(searcheeName)){
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
                this.searcherName = searcheeName;
                return true;
            }
        }
        catch (Exception e){
            Log.i("isValidSearch failed", e.toString());
            return false;
        }

    }

    public User getUser(){
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
