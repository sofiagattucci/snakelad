package tests;

import org.junit.Test;

import tests.model.ClassicDiceTest;
import tests.model.PlayerTest;

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
        final ClassicDiceTest classicDiceTest = new ClassicDiceTest();
        classicDiceTest.testClassicDice();
        final PlayerTest playerTest = new PlayerTest();
        playerTest.testPlayer();
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

    }
}
