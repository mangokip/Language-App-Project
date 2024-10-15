package com.app;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class User {

    private UUID id;
    private String username;
    private String password;
    private String email; //what does type do 
    private HashMap<Language, ProgressTracker> progressTrackers;

    
    

    public User(String username, String password, String email) {
        this.id = UUID.randomUUID();
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.progressTrackers = new HashMap<>();
    }
    public User(UUID uuid, String username, String password, String email) {
        this.id = uuid;
        setUserName(username);
        setPassword(password);
        setEmail(email);
        this.progressTrackers = new HashMap<>();
    }
    public UUID getUUID() {
        return this.id;
    }


    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   

    /**
     * User's language of choice to be selected
     *
     * @param language - language to be chosen
     */
    public void selectedLanguage(Language language) {

    }

    /**
     * Grabs a ProgressTracker object to be linked to a user for each language
     * they are learning
     *
     * @return -
     * HashMap<Language (key of what the progressTracker will be assigned to), ProgressTracker (progress tracker object to be linked to language)>
     */
    public HashMap<Language, ProgressTracker> getProgressTracker() {
        return this.progressTrackers;

    }

    /**
     * Adds or updates a ProgressTracker for a specific language in the user's progressTrackers map.
     *
     * @param language the language being learned
     * @param questionsCompleted the number of questions completed for that language
     * @param lessonsCompleted the number of lessons completed for that language
     * @param xp the experience points earned for that language
     * @param streak the current streak for that language
     * @param completedLessons the number of completed lessons for that language
     * @param totalLessons the total number of lessons for that language
     * @param progressPercentage the percentage of progress for that language
     * @param state the current state (level) of the user in that language
     */
    public void addLanguageProgress(Language language, int questionsCompleted, int lessonsCompleted, int xp, int streak, int completedLessons, int totalLessons, int progressPercentage, State state) {
        ProgressTracker progress = new ProgressTracker(questionsCompleted, lessonsCompleted, xp, streak, completedLessons, totalLessons, progressPercentage, state, null, null, null);
        this.progressTrackers.put(language, progress);
    }
    
    public List<String> getLanguageProgress() {
        List<String> progressList = new ArrayList<>();
        for (Map.Entry<Language, ProgressTracker> entry : progressTrackers.entrySet()) {
            Language language = entry.getKey();
            ProgressTracker progressTracker = entry.getValue();
            progressList.add("Language: " + language.getLanguageCode() + ", Progress: " + progressTracker.displayProgress());
        }
        return progressList;
    }

   

}


    