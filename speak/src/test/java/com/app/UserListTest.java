package com.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class UserListTest {
    private UserList userList;
    
    @Before
    public void setUp() {
        // Get instance of UserList (singleton)
        userList = UserList.getInstance();
        // Clear existing users before each test
        userList.getUsers().clear();
    }
    
    @Test
    public void testSingletonPattern() {
        // Test that getInstance always returns the same instance
        UserList instance1 = UserList.getInstance();
        UserList instance2 = UserList.getInstance();
        assertSame("Singleton instances should be the same", instance1, instance2);
    }
    
    @Test
    public void testAddUser() {
        // Test adding a new user
        assertTrue("Should successfully add new user", 
            userList.addUser("testUser", "password123", "test@email.com"));
            
        // Verify user was added
        assertTrue("Should find added user", 
            userList.hasUser("testUser"));
            
        // Test adding duplicate username
        assertFalse("Should fail to add duplicate username", 
            userList.addUser("testUser", "differentPass", "different@email.com"));
    }
    
    @Test
    public void testGetUser() {
        // Add a test user
        userList.addUser("john", "pass123", "john@email.com");
        
        // Test getting existing user
        User user = userList.getUser("john");
        assertNotNull("Should retrieve existing user", user);
        assertEquals("Should retrieve correct username", "john", user.getUserName());
        assertEquals("Should retrieve correct email", "john@email.com", user.getEmail());
        
        // Test getting non-existent user
        assertNull("Should return null for non-existent user", 
            userList.getUser("nonexistent"));
    }
    
    @Test
    public void testHasUser() {
        // Test with non-existent user
        assertFalse("Should return false for non-existent user", 
            userList.hasUser("nobody"));
            
        // Add a user and test
        userList.addUser("alice", "pass123", "alice@email.com");
        assertTrue("Should return true for existing user", 
            userList.hasUser("alice"));
            
        // Test case sensitivity
        assertFalse("Should be case sensitive", 
            userList.hasUser("Alice"));
    }
    
    @Test
    public void testIsMatch() {
        // Add a test user
        userList.addUser("testUser", "pass123", "test@email.com");
        
        // Test matching email
        assertTrue("Should match existing email", 
            userList.isMatch("test@email.com"));
            
        // Test case insensitive email matching
        assertTrue("Should match email case-insensitive", 
            userList.isMatch("TEST@email.com"));
            
        // Test non-matching email
        assertFalse("Should not match non-existent email", 
            userList.isMatch("nonexistent@email.com"));
    }
    
    @Test
    public void testGetUsers() {
        // Test empty list initially
        assertEquals("Should start with empty list", 
            0, userList.getUsers().size());
            
        // Add some users
        userList.addUser("user1", "pass1", "user1@email.com");
        userList.addUser("user2", "pass2", "user2@email.com");
        
        // Test list contents
        ArrayList<User> users = userList.getUsers();
        assertEquals("Should have correct number of users", 
            2, users.size());
        assertTrue("Should contain added users", 
            users.stream().anyMatch(u -> u.getUserName().equals("user1")));
        assertTrue("Should contain added users", 
            users.stream().anyMatch(u -> u.getUserName().equals("user2")));
    }
    
    @Test
    public void testAddUserWithNullValues() {
        // Test adding user with null username
        assertFalse("Should fail to add user with null username", 
            userList.addUser(null, "pass123", "email@test.com"));
            
        // Test adding user with null password
        assertFalse("Should fail to add user with null password", 
            userList.addUser("username", null, "email@test.com"));
            
        // Test adding user with null email
        assertFalse("Should fail to add user with null email", 
            userList.addUser("username", "pass123", null));
    }
    
    @Test
    public void testAddUserWithEmptyValues() {
        // Test adding user with empty username
        assertFalse("Should fail to add user with empty username", 
            userList.addUser("", "pass123", "email@test.com"));
            
        // Test adding user with empty password
        assertFalse("Should fail to add user with empty password", 
            userList.addUser("username", "", "email@test.com"));
            
        // Test adding user with empty email
        assertFalse("Should fail to add user with empty email", 
            userList.addUser("username", "pass123", ""));
    }
    
    @Test
    public void testConcurrentModification() {
        // Add initial users
        userList.addUser("user1", "pass1", "user1@email.com");
        userList.addUser("user2", "pass2", "user2@email.com");
        
        // Test that getUsers returns a copy or unmodifiable list
        ArrayList<User> users = userList.getUsers();
        try {
            users.clear(); // Try to modify the returned list
            // Test if the original list is unchanged
            assertEquals("Original list should be unchanged", 
                2, userList.getUsers().size());
        } catch (UnsupportedOperationException e) {
            // This is also acceptable if the list is unmodifiable
            assertTrue("Unmodifiable list is acceptable", true);
        }
    }
}
