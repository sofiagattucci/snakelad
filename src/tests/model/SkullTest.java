package tests.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Random;
import model.items.IntegerReturningItem;
import model.items.Skull;

/**
 * Junit test used in order to test Skull class.
 * This class has to achieve success in all its tests.
 */
public class SkullTest {

    private static final int SKULL_VALUE = -2;
    private static final int NUMBER_OF_ITERATION = 50;
    private static final int RAND_UPPER_BOUND = 10000;

    /**
     * Tests methods inside Skull class.
     */
    @Test
    public void testSkull() {
        IntegerReturningItem skull = new Skull(0);
        final Random rand = new Random();

        assertEquals(skull.runEffectGettingResult(), SKULL_VALUE);
        assertEquals(skull.getPosition(), 0);

        for (int i = 0; i < NUMBER_OF_ITERATION; i++) {
            final int position = rand.nextInt(RAND_UPPER_BOUND);
            skull = new Skull(position);
            assertEquals(skull.runEffectGettingResult(), SKULL_VALUE);
            assertEquals(skull.getPosition(), position);
        }
    }

}