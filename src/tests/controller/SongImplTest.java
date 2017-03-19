package tests.controller;

import static org.junit.Assert.fail;

import org.junit.Test;

import controller.AbstractSong;
import controller.BackgroundMusic;
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
        final AbstractSong music = new BackgroundMusic();
      //try to stop music before call start method.
        try {
            music.stop();
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
      //try to set current volume before initialize object,
        try {
            music.getCurrent();
            fail(MESSAGE);
        } catch (IllegalStateException i) {
            printMessageError();
        }
    }

}
