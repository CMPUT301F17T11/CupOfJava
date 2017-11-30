package com.cmput301f17t11.cupofjava.Models;

import com.cmput301f17t11.cupofjava.Models.User;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *  Represents a container of User objects.
 *
 *  As of yet, it is not being used, but may be used later for prj5
 */
public class UserList implements Serializable {
    ArrayList<User> users;

    public UserList(){
        this.users = new ArrayList<>();
    }

    public void addUser(User user) throws IllegalArgumentException{
        if(this.users.contains(user))
        {
            throw new IllegalArgumentException();

        }
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

    public boolean hasUser(String username){
        for (int i = 0; i < this.users.size(); i++){
            if (this.users.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    User getUser(int index) { return users.get(index);}

    public int getUserIndexByName(String username){
        for (int i = 0; i < this.users.size(); i++){
            if (this.users.get(i).getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    public int getUserIndexByObj(User user){
        return this.users.indexOf(user);
    }

    public void deleteUser(User user){
        this.users.remove(user);
    }
}
