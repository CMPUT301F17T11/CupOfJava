package com.cmput301f17t11.cupofjava;

import java.util.ArrayList;

/**
 * Created by nazim on 21/10/17.
 */

public class UserList{
    ArrayList<User> users;

    public UserList(){
        retrieveUserList(); //retrieve from a place saved
    }

    public void addUser(User user){
        this.users.add(user);
    }

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

    private void retrieveUserList(){} //TODO: implement userlist retrieval

    public int getUserIndex(User user){
        return this.users.indexOf(user);
    }

    public void deleteUser(User user){
        this.users.remove(user);
    }
}
