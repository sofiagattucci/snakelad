package tests.controller;

import static org.junit.Assert.fail;

import org.junit.Test;

import controller.Song;
import controller.SongImpl;
import utilities.ConsoleLog;

/**
 * Test for music.
 *
 */
public class SongImplTest {

    private static final String MESSAGE = "Must call initialize object calling start method!";

    private static void printMessageError() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside SongImplTest.");
    }
    /**
     * Test the music.
     */
    @Test
    public void musicTest() {
        final Song music = new SongImpl();
      //try to stop music before call start method.
        try {
            music.setStop();
            fail("Must call start method before call stop!");
        } catch (IllegalStateException i) {
            printMessageError();
        }
        //try to set volume before initialize object,
        try {
            music.setVolume(0);
            fail(MESSAGE);
        } catch (IllegalStateException i) {
            printMessageError();
        }
      //try to get maximum volume before initialize object,
        try {
            music.getMaximum();
            fail(MESSAGE);
        } catch (IllegalStateException i) {
            printMessageError();
        }
      //try to get minimum volume before initialize object,
        try {
            music.getMinimum();
            fail(MESSAGE);
        } catch (IllegalStateException i) {
            printMessageError();
        }
      //try to set current volume before initialize object,
        try {
            music.getCurrent();
            fail(MESSAGE);
        } catch (IllegalStateException i) {
            printMessageError();
        }
    }

}
