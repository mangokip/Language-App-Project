package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getUsers();
    private User testUser;

    @BeforeEach
    public void setup() {
        userList.clear();
        testUser = new User("TestUser", "TestPassword123", "testuser@example.com");
        userList.add(testUser);
        DataWriter.saveUsers(userList);
        System.out.println("UserList in setup: " + userList);

    }

    @AfterEach
    public void tearDown() {
        userList.clear();
        DataWriter.saveUsers(userList);
    }

    @Test
    void testGetUsersSize() {
        userList = DataLoader.loadUsers();
        assertEquals(12, userList.size());
    }

    @Test
    void testGetUsersSizeZero() {
        userList.clear();
        DataWriter.saveUsers(userList);
        userList = DataLoader.loadUsers();
        assertEquals(0, userList.size());
    }

    @Test
    void testGetUserPassword() {
        userList = DataLoader.loadUsers();
        assertEquals("TestPassword123", userList.get(0).getPassword());
    }

    @Test
    public void testLoadWords() {
        Map<String, List<Word>> languageWords = DataLoader.loadWords();
        assertNotNull(languageWords);
        assertTrue(languageWords.size() > 0);
        
        List<Word> spanishWords = languageWords.get("Spanish");
        assertNotNull(spanishWords);
        assertTrue(spanishWords.size() > 0);
    }

    @Test
    public void testLoadUsers() {
        ArrayList<User> loadedUsers = DataLoader.loadUsers();
        assertNotNull(loadedUsers);
        assertEquals(12, loadedUsers.size());

        User loadedUser = loadedUsers.get(0);
        assertEquals("JaneDoe", loadedUser.getUserName());
        assertEquals("newpassword123", loadedUser.getPassword());
        assertEquals("john.doe@example.com", loadedUser.getEmail());
    }
}
