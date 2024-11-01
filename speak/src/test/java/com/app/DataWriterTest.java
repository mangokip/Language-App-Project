package com.app;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {

    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private static final String TEST_FILE_PATH = "/user.json";

    @BeforeEach
public void setup() {
    DataWriter.clearUserFile(TEST_FILE_PATH); 
    
    userList.getUsers().clear();  
    assertEquals(0, DataLoader.loadUsersFromResource(TEST_FILE_PATH).size(), 
                 "Expected user.json to start empty in setup.");
    User user = new User("TestUser", "SecurePass123", "testuser@example.com");  
    userList.getUsers().add(user);
    DataWriter.saveUsers(userList.getUsers(), TEST_FILE_PATH);
}

    @AfterEach
    public void tearDown() {
        userList.getUsers().clear();  
        DataWriter.clearUserFile("/user.json"); 

    
        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource("/user.json");
        assertEquals(0, loadedUsers.size(), "Expected user.json to be cleared in tearDown.");
    }

    @Test
    public void testSaveUsers() {
       
        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource(TEST_FILE_PATH);

        assertNotNull(loadedUsers, "Loaded users should not be null.");
        assertEquals(1, loadedUsers.size(), "Loaded users size should be 1.");

        User savedUser = loadedUsers.get(0);
        assertEquals("TestUser", savedUser.getUserName(), "Username should match 'TestUser'.");
        assertEquals("SecurePass123", savedUser.getPassword(), "Password should match 'SecurePass123'.");
        assertEquals("testuser@example.com", savedUser.getEmail(), "Email should match 'testuser@example.com'.");
    }

    @Test
    void testInvalidUser() {
      
        User user1 = new User("1no 1e9h", "!@!@#", "312m12");
        users.add(user1);
        DataWriter.saveUsers(users, TEST_FILE_PATH);

       
        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource(TEST_FILE_PATH);
        assertNotNull(loadedUsers, "Loaded users should not be null.");
        assertEquals(2, loadedUsers.size(), "Loaded users size should include invalid user.");

        User loadedInvalidUser = loadedUsers.get(1);
        assertEquals("312m12", loadedInvalidUser.getEmail(), "Email should match '312m12'.");
    }

    @Test
    void testNullUser() {
       
        users.add(null);
        DataWriter.saveUsers(users, TEST_FILE_PATH);

        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource(TEST_FILE_PATH);
        assertEquals(1, loadedUsers.size(), "Loaded users should only contain valid users after attempting to save a null user.");
    }

    @Test
    void testSavingUserWithUpdatedDetails() {
       
        User user = new User("TestUser", "SecurePass123", "testuser@example.com");
        Language language = new Language("Spanish");
        user.createLanguageProgress(language);
        users.add(user);
        DataWriter.saveUsers(users, TEST_FILE_PATH);

        
        user.setPassword("UpdatedPass123");
        user.setEmail("updateduser@example.com");
        ProgressTracker newProgress = new ProgressTracker(5, 10, 100, 3, 5, 20, 25, new IntermediateState());
        user.updateLanguageProgress(language, newProgress);

        DataWriter.saveUsers(users, TEST_FILE_PATH);

        
        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource(TEST_FILE_PATH);
        User updatedUser = loadedUsers.get(0);
        assertEquals("UpdatedPass123", updatedUser.getPassword(), "Password should match 'UpdatedPass123'.");
        assertEquals("updateduser@example.com", updatedUser.getEmail(), "Email should match 'updateduser@example.com'.");
        assertNotNull(updatedUser.getLanguageProgressTracker(language), "Language progress should not be null.");
    }

    @Test
    void testDuplicateUser() {
       
        User user1 = new User("TestUser", "SecurePass123", "testuser@example.com");
        User user2 = new User("TestUser", "SecurePass123", "testuser@example.com");
        users.add(user1);
        users.add(user2);
        DataWriter.saveUsers(userList.getUsers(), TEST_FILE_PATH);

        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource(TEST_FILE_PATH);
        assertEquals(2, loadedUsers.size(), "Loaded users should contain both duplicate entries.");
    }
}
