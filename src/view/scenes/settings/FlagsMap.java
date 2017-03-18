package view.scenes.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import utilities.enumeration.Language;

/**
 * This class hold a map with the different types of flags(Images) and their path.
 */
public final class FlagsMap {

    private static final String EN = "languagesFlags/united_kingdom.png";
    private static final String IT = "languagesFlags/italy.png";
    private static final String FR = "languagesFlags/france.png";
    private static final String ES = "languagesFlags/spain.png";
    private static final String DE = "languagesFlags/germany.png";

    private static final FlagsMap INSTANCE = new FlagsMap();
    private final Map<Language, String> flagsPathMap = new HashMap<>();

    private FlagsMap() {

        this.putEntry(Language.EN, EN);
        this.putEntry(Language.IT, IT);
        this.putEntry(Language.FR, FR);
        this.putEntry(Language.ES, ES);
        this.putEntry(Language.DE, DE);
    }

    private void putEntry(final Language l, final String path) {
        this.flagsPathMap.put(l, path);
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
