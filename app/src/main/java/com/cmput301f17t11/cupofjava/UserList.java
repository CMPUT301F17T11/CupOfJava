package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IllegalFormatCodePointException;



public class UserList{
    ArrayList<User> users;

    public UserList(){
        retrieveUserList(); //retrieve from a place saved
    }

    public void addUser(User user) throws IllegalArgumentException{
        if(this.users.contains(user))
        {
            throw new IllegalArgumentException();

        }
        this.users.add(user);
    }
    //this method was added by eshna
    public boolean hasUser(User user){
        if (this.users.contains(user)){
            return true;
        }
        else{
            return false;
        }
    }
    //this method was added by eshna
    User getUser(int index) { return users.get(index);}

    public ArrayList<User> retrieveUserList(){
     return users;
    } //TODO: implement userlist retrieval

    public ArrayList<User> sortNamesAlphabetically(){
        Collections.sort(users, new Comparator<User>() {

            public int compare(User u1, User u2) {
                return u1.getUsername().compareToIgnoreCase(u2.getUsername());
            }
        });

     return users;
    }

    public int getUserIndex(User user){
        return this.users.indexOf(user);
    }

    public void deleteUser(User user){
        this.users.remove(user);
    }
}
