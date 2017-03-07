package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

import model.Player;
import model.PlayerImpl;
import utilities.ConsoleLog;

/**
 * Junit test used in order to test Player class.
 * This class has to achieve success in all its tests.
 */
public final class PlayerTest {

    private static final int NUMBER_OF_ITERATIONS = 1000;
    private static final int BIG_NUMBER = 500;

    /**
     * Tests all methods inside Player class.
     */
    @Test
    public void testPlayer() {
        //initialize player object
        final Player player = new PlayerImpl();
        //call getPosition() and setNewPosition(), checking if everything works correctly
        assertEquals(player.getPosition(), 0);
        for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
            final Random rand = new Random();
            final int value = rand.nextInt(i);
            player.setNewPosition(value);
            assertEquals(player.getPosition(), value);
        }
        player.setNewPosition(0);
        assertEquals(player.getPosition(), 0);

        //call setNewPosition() with negatives arguments. It must throw an IllegalArgumentException.
        try {
            final Random rand = new Random();
            final int negativeValue = rand.nextInt(BIG_NUMBER) - BIG_NUMBER;
            player.setNewPosition(negativeValue);
            fail("cannot call setNewPosition() with a negative argument!");
        } catch (final IllegalArgumentException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalArgumentException thrown with success inside PlayerTest.");
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

    }

}
