package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

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
}
