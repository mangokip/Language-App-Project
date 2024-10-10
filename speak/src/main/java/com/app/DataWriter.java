import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The DataWriter class is responsible for saving data related to users and lessons
 * in the CockySpeak application. It provides methods to save users and lessons to a data source.
 * @author David Dinh
 */
public class DataWriter {
    private static final String USER_FILE = "user.json";
    private static final String LESSON_FILE = "lesson.json";

    /**
     * Saves the list of users to the data source.
     *
     * @param users the ArrayList of User objects to be saved
     */
    public void saveUsers(ArrayList<User> users) {
        JSONArray userList = new JSONArray();

        for (User user : users) {
            JSONObject userDetails = new JSONObject();
            userDetails.put("UUID", user.getUUID());
            userDetails.put("username", user.getUsername());
            userDetails.put("password", user.getPassword());
            userDetails.put("email", user.getEmail());

            JSONObject languageProgress = new JSONObject();
            user.getLanguageProgress().forEach((language, progress) -> {
                JSONObject progressDetails = new JSONObject();
                progressDetails.put("completedLessons", progress.getCompletedLessons());
                progressDetails.put("totalLessons", progress.getTotalLessons());
                progressDetails.put("progressPercentage", progress.getProgressPercentage());
                languageProgress.put(language, progressDetails);
            });
            userDetails.put("languageProgress", languageProgress);

            userList.add(userDetails);
        }

        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(userList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Saves the list of lessons to the data source.
     *
     * @param lessons the ArrayList of Lesson objects to be saved
     */
    public void saveLessons(ArrayList<Lesson> lessons) {
        JSONArray lessonList = new JSONArray();
        
        for (Lesson lesson : lessons) {
            JSONObject lessonDetails = new JSONObject();
            lessonDetails.put("topic", lesson.getTopic());

            JSONArray questionsArray = new JSONArray();
            
            for (Question question : lesson.getQuestions()) {
                JSONObject questionDetails = new JSONObject();
                
                questionDetails.put("question", question.getQuestionText());
                questionDetails.put("answer", question.getAnswer());
                questionDetails.put("answerOptions", question.getAnswerOptions());
                questionDetails.put("difficulty", question.getDifficulty());
                questionDetails.put("category", question.getCategory());
                questionDetails.put("counter", question.getCounter());
                questionsArray.add(questionDetails);
            }
            lessonDetails.put("questions", questionsArray);
            lessonDetails.put("lessonStatus", lesson.isLessonStatus());

            lessonList.add(lessonDetails);
        }

        try (FileWriter file = new FileWriter(LESSON_FILE)) {
            file.write(lessonList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}