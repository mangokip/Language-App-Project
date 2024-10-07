import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DataLoader class is responsible for reading lesson data from a JSON file.
 * This version is a simple test that reads the file's content and prints it,
 * but it does not yet parse the JSON into objects.
 */
public class DataLoader {

    // Path to the JSON file containing the lessons
    private static final String LESSON_FILE = "json/lesson.json"; // Replace with the correct path

    /**
     * Loads lessons from a JSON file and returns them as a list of Lesson objects.
     * This version simply reads the file and returns an empty list, without parsing the content.
     * 
     * @return a List of Lesson objects (currently empty)
     */
    public List<Lesson> loadLessons() {
        List<Lesson> lessons = new ArrayList<>(); // Return an empty list for now
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(LESSON_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            // Print the JSON content (without parsing)
            System.out.println("JSON file content:");
            System.out.println(content.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lessons; // Returning an empty list for now
    }

    /**
     * Main method to test the DataLoader class.
     * Loads lessons from the JSON file and prints the file's content to the console.
     * If no lessons are loaded or the file is not found, it prints an appropriate message.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader();
        dataLoader.loadLessons();

    }
}
