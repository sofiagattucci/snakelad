package tests;

import org.junit.Test;

import tests.model.ClassicDiceTest;
import tests.model.PlayerTest;
import tests.view.LanguageMapTest;
import tests.view.PawnTypesTest;

/**
 * This class has the task of calling all Junit tests within the 
 * package 'tests' in order of testing the entire project.
 * This class has to achieve success in all its tests.
 */
public final class TestLauncher {

    /**
     * Calls all Junit tests of Model.
     */
    @Test
    public void testModel() {
        new ClassicDiceTest().testClassicDice();
        new PlayerTest().testPlayer();
    }

    /**
     * Calls all Junit tests of Controller.
     */
    @Test
    public void testController() {

    }

    /**
     * Calls all Junit tests of View.
     */
    @Test
    public void testView() {
        new PawnTypesTest().pawnTypesTest();
        new LanguageMapTest().test();
    }
}
