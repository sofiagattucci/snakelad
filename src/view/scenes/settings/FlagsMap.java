package view.scenes.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import utilities.Language;

/**
 * This class hold a map with the different types of flags(Images) and their path.
 */
public final class FlagsMap {

    private static final String EN = "./res/LanguagesFlags/united_kingdom.png";
    private static final String IT = "./res/LanguagesFlags/italy.png";
    private static final String FR = "./res/LanguagesFlags/france.png";
    private static final String ES = "./res/LanguagesFlags/spain.png";
    private static final String DE = "./res/LanguagesFlags/germany.png";

    private static final FlagsMap INSTANCE = new FlagsMap();
    private final Map<Language, String> flagsPathMap = new HashMap<>();

    private FlagsMap() {
            this.flagsPathMap.put(Language.EN, EN);
            this.flagsPathMap.put(Language.IT, IT);
            this.flagsPathMap.put(Language.FR, FR);
            this.flagsPathMap.put(Language.ES, ES);
            this.flagsPathMap.put(Language.DE, DE);
    }

    /**
     * Getter of this class unique instance.
     * @return
     *     This class unique instance
     */
    public static FlagsMap get() {
        return INSTANCE;
    }

    /**
     * Getter of the path to the flags that represents a language.
     * @return
     *     The maps that holds all the flags' images paths
     */
    public Map<Language, String> getMap() {
        return Collections.unmodifiableMap(this.flagsPathMap);
    }
}
