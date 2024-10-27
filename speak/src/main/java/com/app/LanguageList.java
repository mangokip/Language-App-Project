package com.app;

import java.util.ArrayList;

/**
 * Singleton class that manages a list of languages in the CockySpeak
 * application. It allows adding, removing, and managing the current language.
 *
 * @author Bryce Klein
 */
public class LanguageList {

    private static LanguageList languageList;
    private ArrayList<Language> languages;

    /*
     * Basic constructor for singleton
     */
    private LanguageList() {
        languages = new ArrayList<>();
    }

    /**
     * Singleton pattern - getInstance method
     *
     * @return The instance of the LanguageList
     */
    public static LanguageList getInstance() {
        if (languageList == null) {
            languageList = new LanguageList();
        }
        return languageList;
    }

    /**
     * Gets the current language
     *
     * @param name The name of the language to get
     * @return The language object if found, null otherwise
     */
    public Language getLanguage(String name) {
        for (Language language : languages) {
            if (language.getLanguageCode().equalsIgnoreCase(name)) {
                return language;
            }
        }
        return null;
    }

    /**
     * Checks if a language exists with the given name
     *
     * @param name The name of the language to check
     * @return true if the language exists, false otherwise
     */
    public boolean hasLanguage(String name) {
        for (Language language : languages) {
            if (language.getLanguageCode().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new language to the list
     *
     * @param name The name of the language to add
     * @return true if the language was added successfully, false otherwise
     */
    public boolean addLanguage(String name) {
        if (hasLanguage(name)) {
            System.out.println("Language already exists with name: " + name);
            return false;
        }
        languages.add(new Language(name));
        return true;
    }

    /**
     * Removes a language from the list
     *
     * @param name The name of the language to remove
     * @return true if the language was removed successfully, false otherwise
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

    public ArrayList<Language> getLanguages() {
        return languages;
    }
}
