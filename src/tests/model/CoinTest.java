package tests.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Random;
import model.items.Coin;
import model.items.IntegerReturningItems;

/**
 * Junit test used in order to test Coin class.
 * This class has to achieve success in all its tests.
 */
public class CoinTest {

    private static final int COIN_VALUE = 1;
    private static final int NUMBER_OF_ITERATION = 50;
    private static final int RAND_UPPER_BOUND = 10000;

    /**
     * Tests methods inside Coin class.
     */
    @Test
    public void testCoin() {
        IntegerReturningItems coin = new Coin(0);
        final Random rand = new Random();

        assertEquals(coin.runEffectGettingResult(), COIN_VALUE);
        assertEquals(coin.getPosition(), 0);

        for (int i = 0; i < NUMBER_OF_ITERATION; i++) {
            final int position = rand.nextInt(RAND_UPPER_BOUND);
            coin = new Coin(position);
            assertEquals(coin.runEffectGettingResult(), COIN_VALUE);
            assertEquals(coin.getPosition(), position);
        }
    }

}
