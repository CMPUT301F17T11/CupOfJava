/*
 * Copyright CMPUT301F17T11. You may use, redistribute, or modify this code, as long as you do not
 * violate the Code of Student Behavior at the University of Alberta
 *
 */

package com.cmput301f17t11.cupofjava.Models;

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
    private ArrayList<String> followRequests;

    /**
     * Constructor
     * @param name gets the username for the user
     */
    public User(String name) {
        this.username = name;
        this.habitList = new HabitList();
        this.followingList = new ArrayList<>();
        this.followerList = new ArrayList<>();
        this.followRequests = new ArrayList<>();
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

    public void sendFollowRequest(String id){
        this.followRequests.add(id);
    }

    public void acceptFollowRequest(String username){
        this.followRequests.remove(username);
        this.followerList.add(username);
    }

    public void rejectFollowRequest(String username){
        this.followRequests.remove(username);
    }

    public boolean isFollowing(String id){
        return followingList.contains(id);
    }

    public boolean isFollower(String id){
        return followerList.contains(id);
    }

    public ArrayList<String> getFollowRequests(){
        return this.followRequests;
    }

    public ArrayList<String> getFollowingList(){
        return this.followingList;
    }

    public ArrayList<String> getFollowerList(){
        return this.followerList;
    }

    public void removeFollower(String username){
        this.followerList.remove(username);
    }

    public void unFollow(String username){
        this.followingList.remove(username);
    }
}