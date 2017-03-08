package tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tests.controller.ControllerTest;
import tests.controller.GameSettingsTest;
import tests.model.ClassicDiceTest;
import tests.model.Dice5To10Test;
import tests.model.ModelImplTest;
import tests.model.NegativeDiceTest;
import tests.model.PlayerTest;

import tests.view.DiceTypesTest;
import tests.view.GameBoardTypesTest;
import tests.model.UserImplTest;
import tests.view.LanguageMapTest;
import tests.view.PawnTypesTest;
import tests.view.PawnsColorTest;

/**
 * This class has the task of calling all Junit tests within the 
 * package 'tests' in order of testing the entire project.
 * This class has to achieve success in all its tests.
 */
public final class TestsLauncher {

    /**
     * Rule to verify expected exceptions.
     */
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

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
        new GameSettingsTest().gameSettingsTest();
    }

    /**
     * Calls all Junit tests of View.
     */
    @Test
    public void testView() {
        //LanguageStringMap
        final LanguageMapTest languageMapTest = new LanguageMapTest();
        languageMapTest.test();
        this.thrown.expect(UnsupportedOperationException.class);
        languageMapTest.testException();
        //DiceTypes
        final DiceTypesTest diceTypesTest = new DiceTypesTest();
        diceTypesTest.test();
        this.thrown.expect(UnsupportedOperationException.class);
        diceTypesTest.testException();
        //GameBoardTypes
        new GameBoardTypesTest().test();
        //PawnsTypes
        new PawnTypesTest().test();
        //PawnsColor
        final PawnsColorTest pawnsColorTest = new PawnsColorTest();
        pawnsColorTest.test();
        this.thrown.expect(IndexOutOfBoundsException.class);
        pawnsColorTest.testSingleException();
        this.thrown.expect(IndexOutOfBoundsException.class);
        pawnsColorTest.testMultiException();
    }
}
