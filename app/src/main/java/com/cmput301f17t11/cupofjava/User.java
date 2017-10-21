package com.cmput301f17t11.cupofjava;

/**
 * Created by nazim on 21/10/17.
 */

public class User {
    private String username;

    public User(String name){
        setUsername(name);
    }

    public void setUsername(String name){
        this.username = name;
    }

    public String getUsername(){
        return this.username;
    }
}