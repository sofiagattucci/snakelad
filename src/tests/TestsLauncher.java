package tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tests.controller.ControllerTest;
import tests.model.ClassicDiceTest;
import tests.model.Dice5To10Test;
import tests.model.ModelImplTest;
import tests.model.NegativeDiceTest;
import tests.model.PlayerTest;
import tests.view.DiceTypesTest;
import tests.view.LanguageMapTest;

/**
 * This class has the task of calling all Junit tests within the 
 * package 'tests' in order of testing the entire project.
 * This class has to achieve success in all its tests.
 */
public final class TestsLauncher {

     /**
     * Rule to manage expected exceptions.
     */
    @Rule
    public final ExpectedException thrown  = ExpectedException.none();

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
        new LanguageMapTest().test(this.thrown);
        new DiceTypesTest().test(this.thrown);
    }
}
