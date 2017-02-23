package tests.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utilities.ConsoleLog;
import utilities.Language;
import utilities.LanguagesManager;
import view.LanguageStringMap;

/**
 * Test for the class LanguageStringMap.
 */
public class LanguageMapTest {

    /**
     * JUnit Tests. 
     */
    @Test
    public void test() {
        assertEquals(LanguageStringMap.get().getClass(), LanguageStringMap.class);
        assertTrue(LanguageStringMap.get().getMap().isEmpty());
        try {
            LanguageStringMap.get().getMap().put("A", "B");
        } catch (UnsupportedOperationException e) {
            ConsoleLog.get().print("\nUnsupportedOperationException thrown with success in line 27 of LanguageMapTest");
        }
        for (final Language lang: Language.values()) {
            LanguageStringMap.get().setLanguage(LanguagesManager.get().getLanguage(lang));
            assertFalse(LanguageStringMap.get().getMap().isEmpty());
            for (final String elem: LanguageStringMap.get().getMap().keySet()) {
                assertEquals(LanguageStringMap.get().getMap().get(elem), LanguagesManager.get().getLanguage(lang).get(elem));
            }
        }
    }
}
