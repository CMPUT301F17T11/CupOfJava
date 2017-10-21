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

    private void retrieveUserList(){} //TODO: implement userlist retrieval

    public int getUserIndex(User user){
        return this.users.indexOf(user);
    }

    public void deleteUser(int index){
        this.users.remove(index);
    }
}
