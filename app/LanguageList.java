import java.util.ArrayList;

/**
 * Singleton class that manages a list of languages in the CockySpeak application.
 * It allows adding, removing, and managing the current language.
 * @author David Dinh
 */
public class LanguageList {
    // Attributes
    private static LanguageList languageList = null; // Singleton instance
    private ArrayList<Language> languages; // List of available languages

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the list of languages.
     */
    private LanguageList() {
        languages = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of LanguageList.
     * If the instance doesn't exist, it creates one.
     * 
     * @return the singleton instance of LanguageList
     */
    public static LanguageList getInstance() {
        if (languageList == null) {
            languageList = new LanguageList();
        }
        return languageList;
    }

    /**
     * Adds a language to the list of languages.
     * 
     * @param language the Language object to be added to the list
     */
    public void addLanguage(Language language) {
        // TODO: Add logic to add language
    }

    /**
     * Removes a language from the list of languages.
     * 
     * @param language the Language object to be removed from the list
     */
    public void removeLanguage(Language language) {
        // TODO: Add logic to remove language
    }

    /**
     * Sets the current language in the application.
     * 
     * @param language the Language object to set as the current language
     */
    public void setCurrentLanguage(Language language) {
        // TODO: Add logic to set the current language
    }

    /**
     * Gets the current language set in the application.
     * 
     * @return the current Language object
     */
    public Language getCurrentLanguage() {
        // TODO: Return the current language
        return null; // placeholder
    }

    /**
     * Gets a list of all available languages in the system.
     * 
     * @return an ArrayList of Language objects available in the system
     */
    public ArrayList<Language> getAvailableLanguages() {
        // TODO: Return a list of available languages
        return null; // placeholder
    }

    /**
     * Checks if a specific language by name exists in the list.
     * 
     * @param name the name of the language to search for
     * @return the Language object if found, otherwise null
     */
    public Language containsLang(String name) {
        // TODO: Add logic to check if a language is in the list
        return null; // placeholder
    }

    /**
     * Retrieves a specific language from the list.
     * 
     * @param language the Language object to search for
     * @return the matching Language object if found, otherwise null
     */
    public Language getLanguage(Language language) {
        // TODO: Return the language object if it exists
        return null; // placeholder
    }
}
