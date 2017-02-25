package tests.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.junit.Test;
import model.Model;
import model.ModelImpl;
import utilities.ConsoleLog;
import utilities.SceneryDataManager;
import utilities.TypesOfDice;

/**
 * Junit test used in order to test ModelImpl class.
 * This class has to achieve success in all its tests.
 */
public class ModelImplTest {

    private static final int TWO_PLAYERS = 2;
    //private static final int THREE_PLAYERS = 3;
    //private static final int FOUR_PLAYERS = 4;
    //private static final int FIVE_PLAYERS = 5;
    private static final int SIX_PLAYERS = 6;
    private static final int NUMBER_OF_ITERATIONS = 1000;
    private static final String GAME_BOARD_1 = "./res/GameBoards/GameBoard1/file.txt";
    //private static final String GAME_BOARD_2 = "./res/GameBoards/GameBoard2/file.txt";
    //private static final String GAME_BOARD_3 = "./res/GameBoards/GameBoard3/file.txt";

    private final List<Integer> snakesListGameBoard1 = Arrays.asList(3, 4, 8, 24, 26);
    private final List<Integer> laddersListGameBoard1 = Arrays.asList(13, 28, 32, 33);
    //private final List<Integer> snakesListGameBoard2 = Arrays.asList(7, 9, 14, 47, 55);
    //private final List<Integer> laddersListGameBoard2 = Arrays.asList(16, 30, 51, 54, 62);
    //private final List<Integer> snakesListGameBoard3 = Arrays.asList(1, 2, 5, 22, 24, 26);
    //private final List<Integer> laddersListGameBoard3 = Arrays.asList(12, 24, 32, 43, 56, 60);

    private void logPrintIllegalStateException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success.");
    }

    private void failDuringExceptionThrowing(final Exception e) {
        fail("should throw an IllegalStateException, not a " + e.getClass());
    }

    /**
     * Tests methods inside ModelImpl class.
     */
    @Test
    public void testModelImpl() {
        final Model model = new ModelImpl();

        //call methods without calling startGame() method. It must throw IllegalStateException
        try {
            model.getPlayerPosition(0);
            fail("cannot call getPlayerPositionAndJump() because the method statGame() must be called before any ModelImpl's other method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

        try {
            model.getNumberFromDice();
            fail("cannot call getNumberFromDice() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

        try {
            model.restartGame();
            fail("cannot call restartGame() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

        try {
            model.giveUpGame();
            fail("cannot call giveUpGame() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

        //call startGame() method. It must NOT throw any exception.
        try {
            final Random rand = new Random();
            final List<Integer> list = new LinkedList<>();
            list.add(rand.nextInt(90) + 1);
            list.add(0);
            for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
                list.add(rand.nextInt(i) + 1);
            }
            list.add(0);
            for (int i = 1; i <= NUMBER_OF_ITERATIONS; i++) {
                list.add(rand.nextInt(i) + 1);
            }
            list.add(0);
            model.startGame(list, TWO_PLAYERS, TypesOfDice.CLASSIC_DICE);
        } catch (final Exception e) {
            fail("It must NOT throw any exception!");
        }

       //call resetGame(), getNumberFromDice(), getPlayerPositionAndJump() and giveUpGame() 
       //methods after calling startGame() method. It must NOT throw any exception.
        try {
            model.restartGame();
            model.getNumberFromDice();
            model.getPlayerPosition(0);
            model.giveUpGame();
        } catch (final Exception e) {
            fail("It must NOT throw any exception!");
        }

        //call restartGame() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.restartGame();
            fail("cannot call restartGame() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

       //call getNumberFromDice() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.getNumberFromDice();
            fail("cannot call getNumberFromDice() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

        //call getPlayerPositionAndJump() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.getPlayerPosition(0);
            fail("cannot call getPlayerPositionAndJump() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

       //call giveUpGame() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.giveUpGame();
            fail("cannot call giveUpGame() because the method statGame() must be called before any other ModelImpl's method.");
        } catch (final IllegalStateException e) {
            this.logPrintIllegalStateException();
        } catch (final Exception e) {
            this.failDuringExceptionThrowing(e);
        }

        //check everything work correctly with game board n.1
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_1), SIX_PLAYERS, TypesOfDice.CLASSIC_DICE);
        final List<Integer> list = new ArrayList<>();
        list.addAll(snakesListGameBoard1);
        list.addAll(laddersListGameBoard1);
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            model.getNumberFromDice();
            final Optional<Integer> position = model.getPlayerPosition(i % SIX_PLAYERS);
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            /*if ((i % (NUMBER_OF_ITERATIONS / 20)) == 1) {
                model.restartGame();
                System.out.println("ciuao");
            }*/
        }
    }

}
