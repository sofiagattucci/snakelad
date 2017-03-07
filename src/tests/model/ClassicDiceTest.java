package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Optional;
import java.util.Random;
import org.junit.Test;
import model.Dice;
import model.DiceFactoryImpl;
import utilities.ConsoleLog;

/**
 * Junit test used in order to test ClassicDice class.
 * This class has to achieve success in all its tests.
 */
public final class ClassicDiceTest {

    private static final int NUMBER_OF_ROLLS = 200;
    private static final int NUMBER_OF_SIDES = 6;

    //private method called to avoid too much repetition of identical code.
    private void printIllegalStateException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside ClassicDiceTest.");
    }

    //private method called to avoid too much repetition of identical code.
    private void failIllegalStateExceptionThrowing(final Exception e) {
        fail("should throw an IllegalStateException, not a " + e.getClass());
    }

    /**
     * Tests all methods inside ClassicDice class.
     */
    @Test
    public void testClassicDice() {
        //get singleton object representing the dice using DiceFactory
        final Dice dice = new DiceFactoryImpl().createClassicDice();

        //call getLastNumberAppeared() when there are no number appeared! It must throw IllegalStateException
        try {
            dice.getLastNumberAppeared();
            fail("cannot call getLastNumberAppeared() because there is no last number relased from dice.");
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //roll the dice and check if everything works correctly
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            final int number = dice.roll();
            if (number < 1 || number > NUMBER_OF_SIDES) {
                fail("The number from the classic dice must be between 1 and 6 included");
            }
            assertEquals(number, dice.getLastNumberAppeared());
        }

        //set random numbers as last number appeared on dice and check if everything works correctly
        final Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            final int value = rand.nextInt(NUMBER_OF_SIDES) + 1;
            dice.setLastNumberAppeared(Optional.of(value));
            assertEquals(dice.getLastNumberAppeared(), value);
        }

        //reset dice to initial configuration setting lastNumberAppeared equals to Optional.empty
        dice.setLastNumberAppeared(Optional.empty());
        try {
            dice.getLastNumberAppeared();
            fail("cannot call getLastNumberAppeared() because it's empty.");
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }
    }

}
