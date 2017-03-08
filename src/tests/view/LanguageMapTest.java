package tests.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import utilities.Language;
import utilities.LanguageLoader;
import view.LanguageStringMap;

/**
 * JUnit test for the class LanguageStringMap.
 */
public class LanguageMapTest {

    /**
     * JUnit Tests.
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
    }

    /**
     * JUnit test to verify that an exception is thrown.
     */
    @Test (expected = UnsupportedOperationException.class)
    public void testException() {
        LanguageStringMap.get().getMap().put("A", "B");
    }
}
