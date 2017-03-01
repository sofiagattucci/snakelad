package tests.controller;

import static org.junit.Assert.fail;

import org.junit.Test;

import controller.Controller;
import utilities.Difficulty;
import utilities.Language;
import utilities.TypesOfDice;

/**
 * Junit test for class Controller and all of his method.
 *
 */
public class ControllerTest {

//    private static final int ZERO = 0;
//    private static final int ONE_PLAYER = 1;
    private static final int TWO_PLAYER = 2;
//    private static final int THREE_PLAYER = 3;
//    private static final int FOUR_PLAYER = 4;
//    private static final int FIVE_PLAYER = 5;
//    private static final int SIX_PLAYER = 6;


    private void printErrorMessage() {
        System.out.println("Must calling start method!");
    }
    /**
     * Test for all method in Controller class.
     */
    @Test
    public void controllerTest() {
        final Controller controller = Controller.getController();
        //try to call all method before calling start
        try {
            controller.giveUp();
            fail("Must invoke start method before calling giveUp!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.quit();
            fail("Must invoke start methodbefore calling quit!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.restart();
            fail("Must invoke start method before calling restart!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.rollDice();
            fail("Must invoke start method before calling rollDice!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.setLanguage(Language.EN);
            fail("Must invoke start method before calling setLanguage!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.startMusic();
            fail("Must invoke start method before calling startMusic!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.play(TWO_PLAYER, Difficulty.BEGINNER, TypesOfDice.CLASSIC_DICE);
            fail("Must invoke start method before calling play!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        } 
    }

}
