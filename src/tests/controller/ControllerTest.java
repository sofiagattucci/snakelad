package tests.controller;

import static org.junit.Assert.fail;
import org.junit.Test;
import controller.Controller;
import model.TypesOfDice;
import utilities.ConsoleLog;
import utilities.Difficulty;
import utilities.Language;

/**
 * JUnit test for class Controller and all of his method.
 *
 */
public class ControllerTest {

      private static final int TWO_PLAYER = 2;

    private void printErrorMessage() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside ControllerTest.");
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
            ConsoleLog.get().print("");
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
