package view;

import java.util.Collections;
import java.util.Map;

/**
 * This map manages the strings in the game using the correct selected language.
 */
public final class LanguageStringMap {

    private static final LanguageStringMap INSTANCE = new LanguageStringMap();

    private Map<String, String> stringMap;

    private LanguageStringMap() {

    }

    /**
     * Getter of this class unique instance.
     * @return
     *     This class unique instance.
     */
    public static LanguageStringMap get() {
        return INSTANCE;
    }

    /**
     * Getter of the string map.
     * @return
     *     The map containing the English - Current language strings.
     */
    public Map<String, String> getMap() {
        return Collections.unmodifiableMap(this.stringMap);
    }

    /**
     * It updates the map by setting in it the new language strings.
     * @param newMap
     *     The map containing the new language string elements
     */
    public void setLanguage(final Map<String, String> newMap) {
        this.stringMap = newMap;
    }
}
