package tests.model;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import model.Player;
import model.PlayerImpl;

/**
 * Junit test used in order to test Player class inside Model.
 * This class has to achieve success in all its tests.
 */
public final class PlayerTest {

    private static final int NUMBER_OF_ITERATIONS = 100;

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
            final int value = rand.nextInt(i) + 1;
            player.setNewPosition(value);
            assertEquals(player.getPosition(), value);
        }
        player.setNewPosition(0);
        assertEquals(player.getPosition(), 0);
    }

}
