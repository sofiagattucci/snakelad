package model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import model.ClassicDice;
import model.Dice;
import utilities.ConsoleLog;

/**
 * Junit test used in order to test ClassicDice class.
 * This class has to achieve success in all its tests.
 */
public class ClassicDiceTest {

    private static final int NUMBER_OF_ROLLS = 100;
    private static final int NUMBER_OF_SIDES = 6;

    private final Dice dice = ClassicDice.get();

    /**
     * Tests getLastNumberAppeared() and roll() methods in ClassicDice class.
     */
    @Test
    public void testClassicDice() {
        //call getLastNumberAppeared() when there are no number appeared! It must throw IllegalStateException
        try {
            dice.getLastNumberAppeared();
            fail("cannot call getLastNumberAppeared() because it's empty.");
        } catch (final IllegalStateException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalStateException was thrown with success.");
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

        //roll the dice and check if everything works correctly
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            final int number = dice.roll();
            if (number < 1 || number > NUMBER_OF_SIDES) {
                fail("The number from the classic dice must be between 1 and 6 included");
            }
            assertEquals(number, dice.getLastNumberAppeared());
        }
    }

}
