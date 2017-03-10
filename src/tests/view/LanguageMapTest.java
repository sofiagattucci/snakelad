package tests.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import utilities.ConsoleLog;
import utilities.LanguageLoader;
import utilities.enumeration.Language;
import view.LanguageStringMap;

/**
 * JUnit test for the class LanguageStringMap.
 */
public class LanguageMapTest {

    /**
     * Starting JUnit Tests.
     */
    @Test
    public void test() {

        assertEquals(LanguageStringMap.get().getClass(), LanguageStringMap.class);
        assertTrue(LanguageStringMap.get().getMap().isEmpty());
        for (final Language lang: Language.values()) {
            LanguageStringMap.get().setLanguage(LanguageLoader.get().getLanguage(lang));
            assertFalse(LanguageStringMap.get().getMap().isEmpty());
            for (final String elem: LanguageStringMap.get().getMap().keySet()) {
                assertEquals(LanguageStringMap.get().getMap().get(elem), LanguageLoader.get().getLanguage(lang).get(elem));
            }
        }
        try {
            LanguageStringMap.get().getMap().put("A", "B");
            fail("\nFailed test in LanguageMapTest line 36");
        } catch (UnsupportedOperationException e) {
            ConsoleLog.get().print("\nUnsupportedOperationsException thrown with success in LanguageMapTest line 37");
        }
    }
}
