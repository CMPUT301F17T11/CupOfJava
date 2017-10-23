package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserListTest extends ActivityInstrumentationTestCase2 {

    public UserListTest(){
        super(com.cmput301f17t11.cupofjava.MainActivity.class);
    }

    public void testAddUser(){
        UserList users = new UserList();
        User user = new User("User1");
        users.addUser(user);
        assertTrue(users.hasUser(user));
    }

    public void testDelete(){
        UserList list = new UserList();
        User user = new User("User1");
        list.addUser(user);
        list.deleteUser(user);
        assertFalse(list.hasUser(user));
    }

    public void testGetUser(){
        UserList users = new UserList(); //
        User user = new User("User1");
        users.addUser(user);
        User returnedUser = users.getUser(0);
        assertEquals(returnedUser.getUsername(), user.getUsername());

    }

    public void testHasUser(){
        UserList list = new UserList();
        User user = new User("User1");
        list.addUser(user);
        assertTrue(list.hasUser(user));
    }

    public void testRetrieveUserList(){

        UserList list = new UserList();
        User user1 = new User("User1");
        User user2 = new User("User2");
        list.addUser(user1);
        list.addUser(user2);

        ArrayList<User> returnedUserList = list.retrieveUserList();

        assertEquals(returnedUserList, list.users);

    }

    public void testSortUserListAlphabetically(){

        UserList list = new UserList();
        User user1 = new User("Beth");
        User user2 = new User("Amy");
        list.addUser(user1);
        list.addUser(user2);
        Collections.sort(list.users, new Comparator<User>() {

            public int compare(User u1, User u2) {
                return u1.getUsername().compareToIgnoreCase(u2.getUsername());
            }
        });

        ArrayList<User> returnedUserList = list.sortNamesAlphabetically();
        assertEquals(returnedUserList,list.users);

    }
}
