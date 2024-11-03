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

    @BeforeEach
    public void setup() {
        userList = DataLoader.loadUsersFromResource("/user.json");

    }

    @AfterEach
    public void tearDown() {
        DataWriter.saveUsers(userList, "/user.json");
    }

    @Test
    public void testGetUsersSize() {
        userList = DataLoader.loadUsersFromResource("/user.json");
        assertEquals(12, userList.size(), "User list size should match the test setup with only 1 user.");
    }

    @Test
    public void testGetUsersSizeZero() {
        userList.clear();
        DataWriter.saveUsers(userList, "/user.json");
        userList = DataLoader.loadUsersFromResource("/user.json");
        assertEquals(0, userList.size(), "User list size should be zero after clearing and saving an empty list.");
    }

    @Test
    public void testLoadWords() {
        Map<String, List<Word>> languageWords = DataLoader.loadWordsFromResource("/words.json");
        assertNotNull(languageWords);
        assertTrue(languageWords.size() > 0);

        List<Word> spanishWords = languageWords.get("Spanish");
        assertNotNull(spanishWords);
        assertTrue(spanishWords.size() > 0);
    }

    @Test
    public void testPhrases() {

        List<Phrase> languagePhrases = DataLoader.loadPhrasesFromResource("/phrases.json");
        assertNotNull(languagePhrases);
        assertTrue(languagePhrases.size() > 0);
    }

    @Test
    public void testFlashCards() {

        List<Flashcard> languagePhraseCards = DataLoader.loadPhraseCardsFromResource("/phrases.json");
        assertNotNull(languagePhraseCards);
        assertTrue(languagePhraseCards.size() > 0);

        
    }

    @Test
    public void testLoadUsers() {
        ArrayList<User> loadedUsers = DataLoader.loadUsersFromResource("/user.json");

        assertNotNull(loadedUsers, "Loaded users should not be null.");
        assertEquals(12, loadedUsers.size(), "Only one user should be loaded in the test.");

        User loadedUser = loadedUsers.get(0);
        assertEquals("JohnDoe", loadedUser.getUserName(), "Username should match 'JohnDoe'.");
        assertEquals("john.doe@example.com", loadedUser.getEmail(), "Email should match 'testuser@example.com'.");
        assertNotNull(loadedUser.getUUID());
        assertEquals("newPassword123", loadedUser.getPassword(), "Password should match 'TestPassword123'."); // test case is wrong to debug, password is case sensitive as intended
    }

}
