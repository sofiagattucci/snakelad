package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Optional;
import java.util.Random;
import org.junit.Test;
import model.ClassicDice;
import model.NegativeDice;
import utilities.ConsoleLog;

/**
 * Junit test used in order to test NegativeDice class inside Model.
 * This class has to achieve success in all its tests.
 */
public final class NegativeDiceTest {

    private static final int NUMBER_OF_ROLLS = 300;
    private static final int NUMBER_OF_SIDES = 7;
    private static final int MIN_NUMBER = -2;
    private static final int MAX_NUMBER = 5;

    /**
     * Tests all methods inside NegativeDice class.
     */
    @Test
    public void testNegativeDice() {
        final NegativeDice dice = new NegativeDice(ClassicDice.get());

        //call getLastNumberAppeared() when there are no number appeared! It must throw IllegalStateException
        try {
            dice.getLastNumberAppeared();
            fail("cannot call getLastNumberAppeared() because it's empty.");
        } catch (final IllegalStateException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalStateException thrown with success.");
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

        //roll the dice and check if everything works correctly
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            final int number = dice.roll();
            if (number < MIN_NUMBER || number > MAX_NUMBER || number == 0) {
                fail("The number from the NegativeDice must be between -2 and 5 included and 0 is not considered.");
            }
            assertEquals(number, dice.getLastNumberAppeared());
        }

        //set random numbers as last number appreared on dice and check if everything works correctly
        final Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            int value = rand.nextInt(NUMBER_OF_SIDES + 1) + MIN_NUMBER;
            if (value == 0) {
                value = rand.nextInt(NUMBER_OF_SIDES + MIN_NUMBER) + 1;
            }
            dice.setLastNumberAppeared(Optional.of(value));
            assertEquals(dice.getLastNumberAppeared(), value);
        }

        //set a number out of bounds. It must throw IllegalArgumentException
        try {
            dice.setLastNumberAppeared(Optional.of(NUMBER_OF_SIDES));
            fail("Argument out of bounds.");
        } catch (final IllegalArgumentException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalArgumentException thrown with success.");
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        //reset dice to initial configuration setting lastNumberAppeared equals to Optional.empty
        dice.setLastNumberAppeared(Optional.empty());
        try {
            dice.getLastNumberAppeared();
            fail("cannot call getLastNumberAppeared() because it's empty.");
        } catch (final IllegalStateException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalStateException thrown with success.");
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }
    }

}