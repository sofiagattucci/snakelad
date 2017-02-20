package tests;

import org.junit.Test;

import tests.model.ClassicDiceTest;

/**
 * This class has the task of calling all Junit tests within the 
 * package 'tests' in order of testing the entire project.
 */
public final class TestLauncher {

    /**
     * Calls all Junit tests of Model.
     */
    @Test
    public void testModel() {
        final ClassicDiceTest classicDiceTest = new ClassicDiceTest();
        classicDiceTest.testClassicDice();
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
