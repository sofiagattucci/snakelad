package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;
import org.junit.Test;

import model.user.User;
import model.user.UserImpl;
import utilities.ConsoleLog;

/**
 * Junit test used in order to test UserImpl class.
 * This class has to achieve success in all its tests.
 */
public class UserImplTest {

    private static final int NUMBER_OF_ITERATIONS = 100;
    private static final int NUMBER_BOUND_FOR_RAND = 200;
    private static final int BIG_NUMBER = 850000;
    private static final int NEGATIVE_VALUE = -350;

    //private method called to avoid too much repetition of identical code
    private void printIllegalArgumentException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalArgumentException thrown with success inside UserImplTest.");
    }

    //private method called to avoid too much repetition of identical code
    private void failIllegalArgumentException(final Exception e) {
        fail("should throw an IllegalArgumentException, not a " + e.getClass());
    }

    /**
     * Tests all methods inside UserImpl class.
     */
    @Test
    public void testUserImpl() {
        final User user = UserImpl.get();

        //check if the initial user's scores value is 0
        assertEquals(user.getScores(), 0);

        //call getName() when the user's name field is empty. It must throw an IllegalStateException
        try {
            user.getName();
        } catch (final IllegalStateException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalStateException thrown with success inside UserImplTest.");
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

        //set names and check if everything works correctly
        user.setName("Mario");
        assertEquals(user.getName(), "Mario");
        user.setName("Giacomo");
        assertEquals(user.getName(), "Giacomo");
        user.setName("Beatrice");
        user.setName("Alessandro");
        user.setName("Cecilia");
        assertEquals(user.getName(), "Cecilia");

        //add, subtract and set scores checking everything works correctly
        assertEquals(user.getScores(), 0);
        final Random rand = new Random();
        int counter = 0;

        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int randValue = rand.nextInt(NUMBER_BOUND_FOR_RAND);
            user.addScores(randValue);
            counter += randValue;
            assertEquals(user.getScores(), counter);
        }

        for (int i = 0; i < NUMBER_OF_ITERATIONS + NUMBER_OF_ITERATIONS; i++) {
            final int randValue = rand.nextInt(NUMBER_BOUND_FOR_RAND);
            user.addScores(-randValue);
            counter -= randValue;
            if (counter < 0) {
                counter = 0;
            }
            assertEquals(user.getScores(), counter);
        }

        user.setScores(BIG_NUMBER);
        assertEquals(user.getScores(), BIG_NUMBER);

        user.setScores(0);
        assertEquals(user.getScores(), 0);

        //set a negative scores value. It must throw an IllegalArgumentException
        try {
            user.setScores(NEGATIVE_VALUE);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        user.setScores(1);
        assertEquals(user.getScores(), 1);
        user.addScores(-1);
        assertEquals(user.getScores(), 0);
        //it must NOT be less than 0
        user.addScores(-1);
        assertEquals(user.getScores(), 0);

        //call setNumberOfDiceRoll() with a negative argument. It must throw an IllegalArgumentException
        try {
            user.setNumberOfDiceRoll(-1);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setGamesLost() with a negative argument. It must throw an IllegalArgumentException
        try {
            user.setGamesLost(-1);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setGamesWon() with a negative argument. It must throw an IllegalArgumentException
        try {
            user.setGamesWon(-1);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        user.setGamesLost(BIG_NUMBER);
        assertEquals(user.getGamesLost(), BIG_NUMBER);

        user.setGamesWon(BIG_NUMBER);
        assertEquals(user.getGamesWon(), BIG_NUMBER);

        user.setNumberOfDiceRoll(BIG_NUMBER);
        assertEquals(user.getNumberOfDiceRoll(), BIG_NUMBER);

    }

}
