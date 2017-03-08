package tests.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import utilities.ConsoleLog;
import utilities.Language;
import view.scenes.settings.FlagsMap;

/**
 * JUnit test for FlagsMap class.
 */
public class FlagsMapTest {

    private static final String EN = "./res/languagesFlags/united_kingdom.png";
    private static final String IT = "./res/languagesFlags/italy.png";
    private static final String FR = "./res/languagesFlags/france.png";
    private static final String ES = "./res/languagesFlags/spain.png";
    private static final String DE = "./res/languagesFlags/germany.png";

    /**
     * Starting JUnit tests.
     */
    @Test
    public void test() {

        assertEquals(FlagsMap.get().getClass(), FlagsMap.class);

        assertEquals(FlagsMap.get().getMap().get(Language.EN), EN);
        assertEquals(FlagsMap.get().getMap().get(Language.IT), IT);
        assertEquals(FlagsMap.get().getMap().get(Language.FR), FR);
        assertEquals(FlagsMap.get().getMap().get(Language.DE), DE);
        assertEquals(FlagsMap.get().getMap().get(Language.ES), ES);
        try {
            FlagsMap.get().getMap().put(Language.EN, "A");
            fail("Failed test in FlagsMapTest line 37");
        } catch (UnsupportedOperationException e) {
            ConsoleLog.get().print("UnsupportedOperationException thrown witch success in FlagsMap line 38");
        }
    }
}
