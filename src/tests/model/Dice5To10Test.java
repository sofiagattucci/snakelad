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
 * Junit test used in order to test Dice5To10 class inside Model.
 * This class has to achieve success in all its tests.
 */
public final class Dice5To10Test {

    private static final int NUMBER_OF_ROLLS = 200;
    private static final int NUMBER_OF_SIDES = 6;
    private static final int DELTA = 4;
    private static final int MIN_NUMBER = 5;
    private static final int MAX_NUMBER = 10;

    //private method called to avoid too much repetition of identical code.
    private void printIllegalStateException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside Dice5To10Test.");
    }

    //private method called to avoid too much repetition of identical code.
    private void failIllegalStateExceptionThrowing(final Exception e) {
        fail("should throw an IllegalStateException, not a " + e.getClass());
    }

    /**
     * Tests all methods inside Dice5To10 class.
     */
    @Test
    public void testDice5To10() {
        //create dice object using DiceFactory 
        final Dice dice = new DiceFactoryImpl().create5To10Dice(new DiceFactoryImpl().createClassicDice());

        //call getLastNumberAppeared() when there are no number appeared! It must throw IllegalStateException
        try {
            dice.getLastNumberAppeared();
            fail("cannot call getLastNumberAppeared() because it's empty.");
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //roll the dice and check if everything works correctly
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            final int number = dice.roll();
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                fail("The number from the 5To10Dice must be between 5 and 10 included");
            }
            assertEquals(number, dice.getLastNumberAppeared());
        }

        //set random numbers as last number appeared on dice and check if everything works correctly
        final Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_ROLLS; i++) {
            final int value = rand.nextInt(NUMBER_OF_SIDES) + 1 + DELTA;
            dice.setLastNumberAppeared(Optional.of(value));
            assertEquals(dice.getLastNumberAppeared(), value);
        }

        //set a number out of bounds. It must throw IllegalArgumentException
        try {
            dice.setLastNumberAppeared(Optional.of(NUMBER_OF_SIDES + DELTA + 1));
            fail("Argument out of bounds.");
        } catch (final IllegalArgumentException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalArgumentException thrown with success inside Dice5To10Test.");
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
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
