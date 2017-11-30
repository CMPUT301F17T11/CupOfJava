package com.cmput301f17t11.cupofjava.Models;


import com.cmput301f17t11.cupofjava.Models.Habit;
import com.cmput301f17t11.cupofjava.Models.HabitList;

import java.io.Serializable;
import java.util.ArrayList;

import io.searchbox.annotations.JestId;

/**
 * This class tracks names of users that the user
 * follows, has requested to follow, and following
 * the main user.
 */
public class User implements Serializable {
    private String username;

    private HabitList habitList;  //all the habits of the user
    @JestId
    private String id; //used for elasticsearch

    private ArrayList<String> followingList;
    private ArrayList<String> followerList;
    private ArrayList<String> followRequestList;

    /**
     * Constructor
     * @param name gets the username for the user
     */
    public User(String name) {
        this.username = name;
        this.habitList = new HabitList();
    }

    public void setHabitList(HabitList habitList) {
        this.habitList = habitList;
    }
    public String getUsername(){
        return this.username;
    }


    public HabitList getHabitList(){
        return this.habitList;
    }

    public ArrayList<Habit> getHabitListAsArray(){
        return this.habitList.getHabitListAsArray();
    }

    /**
     * Setter method to set the list of users that the current user is following
     * @param followingList list of all users being followed by the user
     *
     */
    public void setFollowingList(ArrayList<String> followingList) {
        this.followingList = followingList;
    }

    /**
     * Setter method to set the list of users who are following the current user.
     * @param followerList
     *
     */
    public void setFollowerList(ArrayList<String> followerList) {
        this.followerList = followerList;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return id for elastic search
     */

    public String getId() {
        return id;
    }

    public void addFollower(String id){
        followerList.add(id);
    }

    public void addFollowing(String id){
        followingList.add(id);
    }

    public void newFollowRequest(String id){
        followRequestList.add(id);
    }

    public void acceptFollowRequest(String id){}

    public boolean isFollowing(String id){
        return followingList.contains(id);
    }

    public boolean isFollower(String id){
        return followerList.contains(id);
    }

    public ArrayList<String> getFollowRequestList(){
        return this.followRequestList;
    }

    public ArrayList<String> getFollowingList(){
        return this.followingList;
    }

    public ArrayList<String> getFollowerList(){
        return this.followerList;
    }
}