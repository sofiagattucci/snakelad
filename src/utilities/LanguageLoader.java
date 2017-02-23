package utilities;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class load the language choose form user.
 * It's designed using Singleton pattern.
 */
public final class LanguageLoader {

    private static final LanguageLoader SINGLETON = new LanguageLoader();

    // private constructor
    private LanguageLoader() { }

    /**
     * Static method which returns a LanguageLoader unique instance.
     * @return a LanguageLoader unique instance.
     */
    public static LanguageLoader get() {
        return SINGLETON;
    }

    //fills and returns a map after loading language data from the proper file
    private Map<String, String> fillAndGetLanguageMap(final String lang) {
        final Map<String, String> map = new HashMap<>();

        final Locale locale = new Locale(lang);
        final ResourceBundle strings = ResourceBundle.getBundle("LanguagesFiles.StringsBundle", locale);
        final Enumeration<String> bundleKeys = strings.getKeys();

        while (bundleKeys.hasMoreElements()) {
            final String key = (String) bundleKeys.nextElement();
            final String value = strings.getString(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * Loads and returns the language data required.
     * @param language
     *          the language chosen.
     * @return a map which contains the language data loaded from the file with traductions in specified language.
     */
    public Map<String, String> getLanguage(final Language language) {

        switch (language) {
        case DE:
            return Collections.unmodifiableMap(this.fillAndGetLanguageMap("de"));
        case ES:
            return Collections.unmodifiableMap(this.fillAndGetLanguageMap("es"));
        case FR:
            return Collections.unmodifiableMap(this.fillAndGetLanguageMap("fr"));
        case IT:
            return Collections.unmodifiableMap(this.fillAndGetLanguageMap("it"));
        default:
            return Collections.unmodifiableMap(this.fillAndGetLanguageMap("en"));
        }
    }

}