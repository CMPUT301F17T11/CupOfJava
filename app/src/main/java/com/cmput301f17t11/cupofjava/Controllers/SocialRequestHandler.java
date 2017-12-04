package com.cmput301f17t11.cupofjava.Controllers;

import android.util.Log;

import com.cmput301f17t11.cupofjava.Models.User;

/**
 * Created by naz_t on 12/1/2017.
 */

public class SocialRequestHandler {

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

    public static User getUser(String userName){
        User user;
        ElasticsearchController.GetUserTask getUserTask = new ElasticsearchController.GetUserTask();
        getUserTask.execute(userName);
        try{
            user = getUserTask.get();
            return user;
        }
        catch (Exception e){
            Log.i("getUser failed", e.toString());
            return null;
        }
    }

    public static void sendFollowRequest(String requestFrom, String requestTo){
        User user = getUser(requestTo);
        if (user == null){
            return;
        }
        if (user.getFollowRequests().contains(requestFrom)){
            return;
        }
        ElasticsearchController.DeleteUsersTask deleteUsersTask
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask.execute(user);

        user.sendFollowRequest(requestFrom);

        ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
        addUserTask.execute(user);
    }


    public static void unFollow(String requestFrom, String requestTo){
        User user = getUser(requestFrom);
        if (user == null){
            return;
        }
        if (!user.getFollowingList().contains(requestTo)){
            return;
        }

        ElasticsearchController.DeleteUsersTask deleteUsersTask
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask.execute(user);

        user.unFollow(requestFrom);

        ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
        addUserTask.execute(user);

        User user1 = getUser(requestTo);
        if (user1 == null){
            return;
        }

        ElasticsearchController.DeleteUsersTask deleteUsersTask1
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask1.execute(user1);

        user1.removeFollower(requestFrom);

        ElasticsearchController.AddUserTask addUserTask1
                = new ElasticsearchController.AddUserTask();
        addUserTask1.execute(user1);
    }


    public static void acceptRequest(String thisUser, String userToAccept){
        User user = getUser(thisUser);
        if (user == null){
            return;
        }
        if (user.getFollowerList().contains(userToAccept)||
                !user.getFollowRequests().contains(userToAccept)){
            return;
        }

        ElasticsearchController.DeleteUsersTask deleteUsersTask
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask.execute(user);

        user.acceptFollowRequest(userToAccept);

        ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
        addUserTask.execute(user);

        User user2 = getUser(userToAccept);
        ElasticsearchController.DeleteUsersTask deleteUsersTask2
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask2.execute(user2);

        user2.addFollowing(thisUser);

        ElasticsearchController.AddUserTask addUserTask2 = new ElasticsearchController.AddUserTask();
        addUserTask2.execute(user2);



    }

    public static void rejectRequest(String thisUser, String userToReject){
        User user = getUser(thisUser);
        if (user == null) {
            return;
        }

        ElasticsearchController.DeleteUsersTask deleteUsersTask
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask.execute(user);

        user.rejectFollowRequest(userToReject);

        ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
        addUserTask.execute(user);
    }


    public static void removeFollower(String thisUser, String userToRemove){
        User user = getUser(thisUser);
        if (user == null){
            return;
        }

        ElasticsearchController.DeleteUsersTask deleteUsersTask
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask.execute(user);

        user.removeFollower(userToRemove);

        ElasticsearchController.AddUserTask addUserTask = new ElasticsearchController.AddUserTask();
        addUserTask.execute(user);


        User user2 = getUser(userToRemove);
        if (user2 == null){
            return;
        }

        ElasticsearchController.DeleteUsersTask deleteUsersTask1
                = new ElasticsearchController.DeleteUsersTask();
        deleteUsersTask1.execute(user2);

        user2.unFollow(thisUser);

        ElasticsearchController.AddUserTask addUserTask1
                = new ElasticsearchController.AddUserTask();
        addUserTask1.execute(user2);
    }



}
