package tests;

import org.junit.Test;

import tests.controller.ControllerTest;
import tests.model.ClassicDiceTest;
import tests.model.Dice5To10Test;
import tests.model.ModelImplTest;
import tests.model.NegativeDiceTest;
import tests.model.PlayerTest;
import tests.model.UserImplTest;
import tests.view.LanguageMapTest;

/**
 * This class has the task of calling all Junit tests within the 
 * package 'tests' in order of testing the entire project.
 * This class has to achieve success in all its tests.
 */
public final class TestsLauncher {

    /**
     * Calls all Junit tests of Model.
     */
    @Test
    public void testModel() {
        //Dice tests
        new ClassicDiceTest().testClassicDice();
        new Dice5To10Test().testDice5To10();
        new NegativeDiceTest().testNegativeDice();

        //Player test
        new PlayerTest().testPlayer();

        //User test
        new UserImplTest().testUserImpl();

        //ModelImpl tests
        final ModelImplTest modelImplTest = new ModelImplTest();
        modelImplTest.testBasicModelImpl();
        modelImplTest.testGameBoard1();
        modelImplTest.testGameBoard2();
        modelImplTest.testGameBoard3();
        modelImplTest.testGameBoard4();
    }

    /**
     * Calls all Junit tests of Controller.
     */
    @Test
    public void testController() {
        new ControllerTest().controllerTest();
    }

    /**
     * Calls all Junit tests of View.
     */
    @Test
    public void testView() {
        new LanguageMapTest().test();
    }
}
