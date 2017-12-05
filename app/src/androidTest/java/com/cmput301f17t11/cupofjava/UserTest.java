package com.cmput301f17t11.cupofjava;

import android.test.ActivityInstrumentationTestCase2;

import com.cmput301f17t11.cupofjava.Models.User;
import com.robotium.solo.Solo;

/**
 * Created by Sajjad on 2017-12-04.
 */

public class UserTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public UserTest() {
        super(User.class);
    }

    /**
     * Test if User exists
     */
    public void testGetUser() {
        String username = "Bob";
        User user = new User(username);
        assertEquals(user.getUsername(), username);
    }

    /**
     * Test if User can follow other user
     */
    public void testFollowing(){
        User testToFollow = new User("Bob");
        User testFollowing = new User("Joe");
        testFollowing.addFollower("Bob");
        assertSame(testFollowing.getFollowingList().get(0), testToFollow);
    }

    /**
     * Test if user can be followed by other users
     */
    public void testFollower(){
        User testFollowed = new User("Bob");
        User testFollower = new User("Joe");
        testFollowed.addFollower("Joe");
        assertSame(testFollowed.getFollowerList().get(0), testFollower);
    }

    /**
     * Test if user can remove followers
     */
    public void testRemoveFollower() {
        User testFollowed = new User("Bob");
        User testFollower = new User("Joe");
        testFollowed.addFollower("Joe");
        testFollowed.removeFollower("Joe");
        assertSame(testFollowed.getFollowerList().get(0), testFollower);
    }



}

