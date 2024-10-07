import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DataLoader class is responsible for loading lesson data from a JSON file.
 * It uses the Gson library to parse the JSON into a list of Lesson objects.
 * The class includes a main method for standalone testing of the data loading functionality.
 */
public class DataLoader {

    // Path to the JSON file containing the lessons
    private static final String LESSON_FILE = "path/to/your/lessons.json"; // Replace with the correct path

    /**
     * Loads lessons from a JSON file and returns them as a list of Lesson objects.
     * If the file is not found or an error occurs during loading, it returns an empty list.
     * 
     * @return a List of Lesson objects loaded from the JSON file
     */
    public List<Lesson> loadLessons() {
        try (FileReader reader = new FileReader(LESSON_FILE)) {
            Gson gson = new Gson();
            // Parsing JSON into a List of Lesson objects
            List<Lesson> lessons = gson.fromJson(reader, new TypeToken<List<Lesson>>(){}.getType());
            return lessons;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list if there's an error
        }
    }

    /**
     * Main method to test the DataLoader class.
     * Loads lessons from the JSON file and prints them to the console.
     * If no lessons are loaded or the file is not found, it prints an appropriate message.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        List<Lesson> lessons = dataLoader.loadLessons();

        if (lessons.isEmpty()) {
            System.out.println("No lessons loaded, or file not found.");
        } else {
            System.out.println("Lessons loaded successfully:");
            for (Lesson lesson : lessons) {
                System.out.println(lesson);
            }
        }
    }
}
