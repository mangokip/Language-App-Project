package com.app;
import java.util.ArrayList;

/**
 * Singleton class that manages a list of languages in the CockySpeak application.
 * It allows adding, removing, and managing the current language.
 * @author Bryce Klein
 */
public class LanguageList {
    private static LanguageList languageList = null;
    private ArrayList<Language> languages;

    /*
     * Basic constructor for singleton
     */
    private LanguageList() {
        languages = new ArrayList<>();
    }

    /*
     * Returns the instance of the LanguageList singleton.
     */
    public static LanguageList getInstance() {
        if (languageList == null) {
            languageList = new LanguageList();
        }
        return languageList;
    }

    /*
     * Gets the language with the specified name.
     */
    public Language getLanguage(String name) {
        for (Language language : languages) {
            if (language.getLanguageCode().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;
    }

    /*
     * Checks if a language exists with the specified name.
     */
    public boolean hasLanguage(String name) {
        for (Language language : languages) {
            if (language.getLanguageCode().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Adds a new language to the list.
     */
    public boolean addLanguage(String name) {
        if (hasLanguage(name)) {
            System.out.println("Language already exists with name: " + name);
            return false;
        }
        languages.add(new Language(name));
        return true;
    }

    /*
     * Removes a language from the list.
     */
    public boolean removeLanguage(String name) {
        Language language = getLanguage(name);
        if (language != null) {
            languages.remove(language);
            return true;
        }
        System.out.println("Language not found with name: " + name);
        return false;
    }

    /*
     *  Returns the list of languages.
     */
    public ArrayList<Language> getLanguages() {
        return languages;
    }
}