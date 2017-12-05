package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301f17t11.cupofjava.Models.User;
import com.cmput301f17t11.cupofjava.Models.UserList;
import com.robotium.solo.Solo;

/**
 * Created by Sajjad on 2017-12-04.
 */

public class UserListTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public UserListTest() {
        super(UserList.class);
    }

    /**
     * Testing for Adding a User
     */
    public void testAddUser() {
        UserList userList = new UserList();
        User user = new User("Joe");
        userList.addUser(user);
        assertTrue(userList.hasUser(user));
    }

    /**
     * Testing for deleting a User
     */
    public void testDeleteUser() {
        UserList userList = new UserList();
        User user = new User("Joe");
        userList.addUser(user);
        userList.deleteUser(user);
        assertFalse(userList.hasUser(user));
    }

    /**
     * Testing if UserList contains User
     */
    public void testHasUser() {
        UserList userList = new UserList();
        User user = new User("Joe");
        userList.addUser(user);
        assertTrue(userList.hasUser(user));
    }
}
