package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;

/**
 * This class tracks names of users that the user
 * follows, has requested to follow, and following
 * the main user.
 */
public class User {
    private String username;
    private ArrayList<String> followingList;
    private ArrayList<String> followerList;
    private ArrayList<String> followRequestList;

    /**
     * Constructor
     * @param name
     */
    public User(String name) {
        setUsername(name);
    }

    //Setter
    public void setUsername(String name){
        this.username = name;
    }

    //Getter
    public String getUsername(){
        return this.username;
    }

    public void addFollower(String id){}

    public void addFollowing(String id){}

    public void newFollowRequest(String id){}

    public void acceptFollowRequest(String id){}

    public boolean isFollowing(String id){
        return true;
    }

    public boolean isFollower(String id){
        return true;
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