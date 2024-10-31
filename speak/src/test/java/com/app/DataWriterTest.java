package com.app;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class DataWriterTest {

    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getUsers();

   

    @AfterEach
    public void tearDown() {
        userList.clear();
        DataWriter.saveUsers(userList);  
    }

    @Test
    void testSaveUsers() {
      
        ArrayList<User> loadedUsers = DataLoader.loadUsers();
        
        assertNotNull(loadedUsers, "Loaded users should not be null.");
        assertEquals(1, loadedUsers.size(), "Loaded users size should be 1.");

        User savedUser = loadedUsers.get(0);
        assertEquals("TestUser", savedUser.getUserName(), "Username should match 'TestUser'.");
        assertEquals("SecurePass123", savedUser.getPassword(), "Password should match 'SecurePass123'.");
        assertEquals("testuser@example.com", savedUser.getEmail(), "Email should match 'testuser@example.com'.");
    }
}
