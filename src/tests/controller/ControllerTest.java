package tests.controller;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import controller.Controller;
import utilities.ConsoleLog;
import utilities.enumeration.AudioTrack;
import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.Language;
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;

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
            controller.startMusic(AudioTrack.SNAKELAD);
            fail("Must invoke start method before calling startMusic!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.play(TWO_PLAYER, Difficulty.BEGINNER, TypesOfDice.CLASSIC_DICE, GameMode.SINGLE_PLAYER);
            fail("Must invoke start method before calling play!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            try {
                controller.clearStatistics();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fail("Must invoke start method before calling clearStatistics!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.stopMusic();
            fail("Must invoke start method before calling stopMusic!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }

        try {
            controller.setVolume(0);
            fail("Must invoke start method before calling setVolume!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }

        try {
            try {
                controller.login("Test");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fail("Must invoke start method before calling login!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
        try {
            controller.collisionHappened(0);
            fail("Must invoke start method before calling collisionHappened!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }

        try {
            controller.statistics();
            fail("Must invoke start method before calling statistics!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }

        try {
            try {
                controller.gameFinished(Turn.CPU);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fail("Must invoke start method before calling gameFinished!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }

        try {
            controller.changeMusic(AudioTrack.SNAKELAD);
            fail("Must invoke start method before calling changeMusic!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }

        try {
            controller.startClipJump();
            fail("Must invoke start method before calling startClipJump!");
        } catch (IllegalStateException s) {
            this.printErrorMessage();
        }
    }

}
