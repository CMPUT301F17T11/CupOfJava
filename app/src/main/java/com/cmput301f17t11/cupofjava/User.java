package com.cmput301f17t11.cupofjava;

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