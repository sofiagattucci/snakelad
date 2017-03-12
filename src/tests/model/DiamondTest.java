package tests.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Random;
import model.items.Diamond;
import model.items.IntegerReturningItems;

/**
 * Junit test used in order to test Diamond class.
 * This class has to achieve success in all its tests.
 */
public class DiamondTest {

    private static final int DIAMOND_VALUE = 5;
    private static final int NUMBER_OF_ITERATION = 50;
    private static final int RAND_UPPER_BOUND = 10000;

    /**
     * Tests methods inside Diamond class.
     */
    @Test
    public void testDiamond() {
        IntegerReturningItems diamond = new Diamond(0);
        final Random rand = new Random();

        assertEquals(diamond.runEffectGettingResult(), DIAMOND_VALUE);
        assertEquals(diamond.getPosition(), 0);

        for (int i = 0; i < NUMBER_OF_ITERATION; i++) {
            final int position = rand.nextInt(RAND_UPPER_BOUND);
            diamond = new Diamond(position);
            assertEquals(diamond.runEffectGettingResult(), DIAMOND_VALUE);
            assertEquals(diamond.getPosition(), position);
        }
    }

}
