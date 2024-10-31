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
        DataWriter.saveUsers(userList, "/user.json");
        System.out.println("UserList in setup: " + userList);

    }

    @AfterEach
    public void tearDown() {
        userList.clear();
        DataWriter.saveUsers(userList, "/user.json");
    }

  
    @Test
    void testGetUsersSize() {
        userList = DataLoader.loadUsersFromResource("/user.json");
        assertEquals(1, userList.size(), "User list size should match the test setup with only 1 user.");
    }

    @Test
    void testGetUsersSizeZero() {
        userList.clear();
        DataWriter.saveUsers(userList, "/user.json");
        userList = DataLoader.loadUsersFromResource("/user.json");
        assertEquals(0, userList.size(), "User list size should be zero after clearing and saving an empty list.");
    }

    @Test
    void testGetUserPassword() {
        userList = DataLoader.loadUsersFromResource("/user.json");
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
        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource("/user.json");

        assertNotNull(loadedUsers, "Loaded users should not be null.");
        assertEquals(1, loadedUsers.size(), "Only one user should be loaded in the test.");

        User loadedUser = loadedUsers.get(0);
        assertEquals("TestUser", loadedUser.getUserName(), "Username should match 'TestUser'.");
        assertEquals("TestPassword123", loadedUser.getPassword(), "Password should match 'TestPassword123'.");
        assertEquals("testuser@example.com", loadedUser.getEmail(), "Email should match 'testuser@example.com'.");
    }

}
