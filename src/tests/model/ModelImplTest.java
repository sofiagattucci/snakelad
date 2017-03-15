package tests.model;

import static org.junit.Assert.assertEquals;
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
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;

/**
 * Junit test used in order to test ModelImpl class.
 * This class has to achieve success in all its tests.
 */
public class ModelImplTest {

    private static final int ONE_PLAYER = 1;
    private static final int TWO_PLAYERS = 2;
    private static final int THREE_PLAYERS = 3;
    private static final int FOUR_PLAYERS = 4;
    private static final int FIVE_PLAYERS = 5;
    private static final int SIX_PLAYERS = 6;
    private static final int CLASSIC_DICE_MAX_VALUE = 6;
    private static final int CLASSIC_DICE_MIN_VALUE = 1;
    private static final int DICE_5_TO_10_MAX_VALUE = 10;
    private static final int DICE_5_TO_10_MIN_VALUE = 5;
    private static final int NEGATIVE_DICE_MAX_VALUE = 5;
    private static final int NEGATIVE_DICE_MIN_VALUE = -2;
    private static final int NUMBER_OF_ITERATIONS = 1000;
    private static final int NUMBER_SUB_ITERATION = 80;
    private static final int GAME_BOARD_1_SIDE_SIZE = 6;
    private static final int GAME_BOARD_2_SIDE_SIZE = 8;
    private static final int GAME_BOARD_3_SIDE_SIZE = 8;
    private static final int GAME_BOARD_4_SIDE_SIZE = 10;
    private static final int ITEMS_LIST_MAX_SIZE = 20;
    private static final String GAME_BOARD_1 = "./res/gameBoards/gameBoard1/file.txt";
    private static final String GAME_BOARD_2 = "./res/gameBoards/gameBoard2/file.txt";
    private static final String GAME_BOARD_3 = "./res/gameBoards/gameBoard3/file.txt";
    private static final String GAME_BOARD_4 = "./res/gameBoards/gameBoard4/file.txt";

    private final List<Integer> snakesListGameBoard1 = Arrays.asList(3, 4, 8, 24, 26);
    private final List<Integer> laddersListGameBoard1 = Arrays.asList(13, 28, 32, 33);
    private final List<Integer> snakesListGameBoard2 = Arrays.asList(7, 9, 14, 47, 55);
    private final List<Integer> laddersListGameBoard2 = Arrays.asList(16, 30, 51, 54, 62);
    private final List<Integer> snakesListGameBoard3 = Arrays.asList(1, 2, 5, 22, 24, 26);
    private final List<Integer> laddersListGameBoard3 = Arrays.asList(12, 24, 32, 43, 56, 60);
    private final List<Integer> snakesListGameBoard4 = Arrays.asList(76, 3, 48, 32, 29, 6);
    private final List<Integer> laddersListGameBoard4 = Arrays.asList(15, 60, 85);

    //private method called to avoid too much repetition of identical code.
    private void printIllegalStateException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside ModelImplTest.");
    }

    //private method called to avoid too much repetition of identical code.
    private void failIllegalStateExceptionThrowing(final Exception e) {
        fail("should throw an IllegalStateException, not a " + e.getClass());
    }

    //private method called to avoid too much repetition of identical code.
    private void printIllegalArgumentException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalArgumentException thrown with success inside ModelImplTest.");
    }

    //private method called to avoid too much repetition of identical code.
    private void failIllegalArgumentExceptionThrowing(final Exception e) {
        fail("should throw an IllegalArgumentException, not a " + e.getClass());
    }

    //private method called to avoid too much repetition of identical code.
    private void failExceptionThrowing(final Exception e) {
        fail("It mustn't throw any exception! " + e.getClass() + " was thrown!");
    }

    //private method called to avoid too much repetition of identical code.
    private void failItemDoesntExist() {
        fail("There is no item with the index passed.");
    }

    //private method called to avoid too much repetition of identical code.
    private void failCallMethodBeforeStartGame() {
        fail("cannot call the method because statGame() method must be called before any ModelImpl's other method.");
    }

    //private method called to avoid too much repetition of identical code.
    private void failBoundsClassicDice() {
        fail("diceValue out of bounds (Classic Dice)!");
    }

    //private method called to avoid too much repetition of identical code.
    private void failBounds5To10Dice() {
        fail("diceValue out of bounds (5to10 Dice)!");
    }

    //private method called to avoid too much repetition of identical code.
    private void failBoundsNegativeDice() {
        fail("diceValue out of bounds or equal to 0 (Negative Dice)!");
    }

    /**
     * Tests basic methods inside ModelImpl class.
     */
    @Test
    public void testBasicModelImpl() {
        final Model model = new ModelImpl();

        //call methods without calling startGame() method. It must throw IllegalStateException.
        try {
            model.getPlayerPosition(0);
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        try {
            model.getNumberFromDice();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        try {
            model.restartGame();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        try {
            model.giveUpGame();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        try {
            model.getGameBoardSideSize();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        try {
            model.tryGenerateCoin();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
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

        //call restartGame(), getNumberFromDice(), getPlayerPosition(), getGameBoardSideSize(), itemCollected(), giveUpGame(),
        //tryGenerateCoin(), tryGenerateDiamond() and tryGenerateSkull() methods after calling startGame() method.
        //It must NOT throw any exception.
        try {
            model.getNumberFromDice();
            model.getPlayerPosition(0);
            model.getGameBoardSideSize();
            model.tryGenerateCoin();
            model.tryGenerateDiamond();
            model.tryGenerateSkull();
            model.restartGame();
            model.giveUpGame();
        } catch (final Exception e) {
            this.failExceptionThrowing(e);
        }

        //call restartGame() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.restartGame();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //call getNumberFromDice() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.getNumberFromDice();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //call getPlayerPosition() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.getPlayerPosition(0);
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //call getGameBoardSideSize() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.getGameBoardSideSize();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //call giveUpGame() method after calling giveUpGame() method. It must throw an IllegalStateException.
        try {
            model.giveUpGame();
            this.failCallMethodBeforeStartGame();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateExceptionThrowing(e);
        }

        //call startGame() method with one only player. It must throw an IllegalArgumentException.
        try {
            model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_1), ONE_PLAYER, TypesOfDice.CLASSIC_DICE);
        } catch (final IllegalArgumentException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalArgumentException thrown with success inside ModelImplTest.");
        } catch (final Exception e) {
            this.failExceptionThrowing(e);
        }

    }

    /**
     * Tests a minimal simulation game.
     * This test is applied only on game board number 1.
     */
    @Test
    public void testGameBoard1() {
        final Model model = new ModelImpl();

        //check everything work correctly with game board n.1, six players and a classic dice.
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_1), SIX_PLAYERS, TypesOfDice.CLASSIC_DICE);
        final List<Integer> list = new ArrayList<>();
        list.addAll(snakesListGameBoard1);
        list.addAll(laddersListGameBoard1);
        int counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < CLASSIC_DICE_MIN_VALUE || diceValue > CLASSIC_DICE_MAX_VALUE) {
                this.failBoundsClassicDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % SIX_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.1, two players and a 5to10 dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_1), TWO_PLAYERS, TypesOfDice._5_TO_10_DICE);
        list.clear();
        list.addAll(snakesListGameBoard1);
        list.addAll(laddersListGameBoard1);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < DICE_5_TO_10_MIN_VALUE || diceValue > DICE_5_TO_10_MAX_VALUE) {
                this.failBounds5To10Dice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % TWO_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.1, five players and a negative dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_1), FIVE_PLAYERS, TypesOfDice.NEGATIVE_DICE);
        list.clear();
        list.addAll(snakesListGameBoard1);
        list.addAll(laddersListGameBoard1);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < NEGATIVE_DICE_MIN_VALUE || diceValue > NEGATIVE_DICE_MAX_VALUE || diceValue == 0) {
                this.failBoundsNegativeDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % FIVE_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }

        //call getGameBoardSideSize() method inside ModelImpl, checking everything works correctly.
        final int gameBoard1SideSize = model.getGameBoardSideSize();
        assertEquals(gameBoard1SideSize, GAME_BOARD_1_SIDE_SIZE);

        final List<Integer> itemsList = new LinkedList<>();

        for (int i = 0; i < NUMBER_SUB_ITERATION; i++) {
            final Optional<Integer> coin = model.tryGenerateCoin();
            if (coin.isPresent()) {
                itemsList.add(coin.get());
            }

            final Optional<Integer> diamond = model.tryGenerateDiamond();
            if (diamond.isPresent()) {
                itemsList.add(diamond.get());
            }

            final Optional<Integer> skull = model.tryGenerateSkull();
            if (skull.isPresent()) {
                itemsList.add(skull.get());
            }
        }

        assertTrue(itemsList.size() <= ITEMS_LIST_MAX_SIZE);

        final int listLength = itemsList.size();
        for (int i = 0; i < listLength; i++) {
            model.itemCollected(itemsList.get(i), Turn.CPU);
        }

        try {
            model.itemCollected(itemsList.get(0), Turn.CPU);
            this.failItemDoesntExist();
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentExceptionThrowing(e);
        }

        model.giveUpGame(); //end the game

    }

    /**
     * Tests a minimal simulation game.
     * This test is applied only on game board number 2.
     */
    @Test
    public void testGameBoard2() {
        final Model model = new ModelImpl();

      //check everything work correctly with game board n.2, four players and a classic dice.
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_2), FOUR_PLAYERS, TypesOfDice.CLASSIC_DICE);
        final List<Integer> list = new ArrayList<>();
        list.addAll(snakesListGameBoard2);
        list.addAll(laddersListGameBoard2);
        int counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < CLASSIC_DICE_MIN_VALUE || diceValue > CLASSIC_DICE_MAX_VALUE) {
                this.failBoundsClassicDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % FOUR_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.2, three players and a 5to10 dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_2), THREE_PLAYERS, TypesOfDice._5_TO_10_DICE);
        list.clear();
        list.addAll(snakesListGameBoard2);
        list.addAll(laddersListGameBoard2);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < DICE_5_TO_10_MIN_VALUE || diceValue > DICE_5_TO_10_MAX_VALUE) {
                this.failBounds5To10Dice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % THREE_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.2, six players and a negative dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_2), SIX_PLAYERS, TypesOfDice.NEGATIVE_DICE);
        list.clear();
        list.addAll(snakesListGameBoard2);
        list.addAll(laddersListGameBoard2);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < NEGATIVE_DICE_MIN_VALUE || diceValue > NEGATIVE_DICE_MAX_VALUE || diceValue == 0) {
                this.failBoundsNegativeDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % SIX_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }

        //call getGameBoardSideSize() method inside ModelImpl, checking everything works correctly.
        final int gameBoard2SideSize = model.getGameBoardSideSize();
        assertEquals(gameBoard2SideSize, GAME_BOARD_2_SIDE_SIZE);

        final List<Integer> itemsList = new LinkedList<>();

        for (int i = 0; i < NUMBER_SUB_ITERATION; i++) {
            final Optional<Integer> coin = model.tryGenerateCoin();
            if (coin.isPresent()) {
                itemsList.add(coin.get());
            }

            final Optional<Integer> diamond = model.tryGenerateDiamond();
            if (diamond.isPresent()) {
                itemsList.add(diamond.get());
            }

            final Optional<Integer> skull = model.tryGenerateSkull();
            if (skull.isPresent()) {
                itemsList.add(skull.get());
            }
        }

        assertTrue(itemsList.size() <= ITEMS_LIST_MAX_SIZE);

        final int listLength = itemsList.size();
        for (int i = 0; i < listLength; i++) {
            model.itemCollected(itemsList.get(i), Turn.CPU);
        }

        try {
            model.itemCollected(itemsList.get(0), Turn.CPU);
            this.failItemDoesntExist();
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentExceptionThrowing(e);
        }

        model.giveUpGame(); //end the game

    }

    /**
     * Tests a minimal simulation game.
     * This test is applied only on game board number 3.
     */
    @Test
    public void testGameBoard3() {
        final Model model = new ModelImpl();

        //check everything work correctly with game board n.3, five players and a classic dice.
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_3), FIVE_PLAYERS, TypesOfDice.CLASSIC_DICE);
        final List<Integer> list = new ArrayList<>();
        list.addAll(snakesListGameBoard3);
        list.addAll(laddersListGameBoard3);
        int counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < CLASSIC_DICE_MIN_VALUE || diceValue > CLASSIC_DICE_MAX_VALUE) {
                this.failBoundsClassicDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % FIVE_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.3, two players and a 5to10 dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_3), TWO_PLAYERS, TypesOfDice._5_TO_10_DICE);
        list.clear();
        list.addAll(snakesListGameBoard3);
        list.addAll(laddersListGameBoard3);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < DICE_5_TO_10_MIN_VALUE || diceValue > DICE_5_TO_10_MAX_VALUE) {
                this.failBounds5To10Dice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % TWO_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.3, four players and a negative dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_3), FOUR_PLAYERS, TypesOfDice.NEGATIVE_DICE);
        list.clear();
        list.addAll(snakesListGameBoard3);
        list.addAll(laddersListGameBoard3);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < NEGATIVE_DICE_MIN_VALUE || diceValue > NEGATIVE_DICE_MAX_VALUE || diceValue == 0) {
                this.failBoundsNegativeDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % FOUR_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }

        //call getGameBoardSideSize() method inside ModelImpl, checking everything works correctly.
        final int gameBoard3SideSize = model.getGameBoardSideSize();
        assertEquals(gameBoard3SideSize, GAME_BOARD_3_SIDE_SIZE);

        final List<Integer> itemsList = new LinkedList<>();

        for (int i = 0; i < NUMBER_SUB_ITERATION; i++) {
            final Optional<Integer> coin = model.tryGenerateCoin();
            if (coin.isPresent()) {
                itemsList.add(coin.get());
            }

            final Optional<Integer> diamond = model.tryGenerateDiamond();
            if (diamond.isPresent()) {
                itemsList.add(diamond.get());
            }

            final Optional<Integer> skull = model.tryGenerateSkull();
            if (skull.isPresent()) {
                itemsList.add(skull.get());
            }
        }

        assertTrue(itemsList.size() <= ITEMS_LIST_MAX_SIZE);

        final int listLength = itemsList.size();
        for (int i = 0; i < listLength; i++) {
            model.itemCollected(itemsList.get(i), Turn.CPU);
        }

        try {
            model.itemCollected(itemsList.get(0), Turn.CPU);
            this.failItemDoesntExist();
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentExceptionThrowing(e);
        }

        model.giveUpGame(); //end the game

    }

    /**
     * Tests a minimal simulation game.
     * This test is applied only on game board number 4.
     */
    @Test
    public void testGameBoard4() {
        final Model model = new ModelImpl();

        //check everything work correctly with game board n.4, three players and a classic dice.
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_4), THREE_PLAYERS, TypesOfDice.CLASSIC_DICE);
        final List<Integer> list = new ArrayList<>();
        list.addAll(snakesListGameBoard4);
        list.addAll(laddersListGameBoard4);
        int counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < CLASSIC_DICE_MIN_VALUE || diceValue > CLASSIC_DICE_MAX_VALUE) {
                this.failBoundsClassicDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % THREE_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.4, six players and a 5to10 dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_4), SIX_PLAYERS, TypesOfDice._5_TO_10_DICE);
        list.clear();
        list.addAll(snakesListGameBoard4);
        list.addAll(laddersListGameBoard4);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < DICE_5_TO_10_MIN_VALUE || diceValue > DICE_5_TO_10_MAX_VALUE) {
                this.failBounds5To10Dice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % SIX_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }
        model.giveUpGame(); //end the game

        //check everything work correctly with game board n.4, two players and a negative dice
        model.startGame(SceneryDataManager.get().readFromFile(GAME_BOARD_4), TWO_PLAYERS, TypesOfDice.NEGATIVE_DICE);
        list.clear();
        list.addAll(snakesListGameBoard4);
        list.addAll(laddersListGameBoard4);
        counter = NUMBER_SUB_ITERATION;
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            final int diceValue = model.getNumberFromDice();
            //check if dice value is between the bounds
            if (diceValue < NEGATIVE_DICE_MIN_VALUE || diceValue > NEGATIVE_DICE_MAX_VALUE || diceValue == 0) {
                this.failBoundsNegativeDice();
            }
            final Optional<Integer> position = model.getPlayerPosition(i % TWO_PLAYERS).getFirst();
            if (position.isPresent()) { //the player jumps
                final boolean isOk = list.contains(position.get());
                assertTrue(isOk);
            }
            counter--;
            if (counter == 0) {
                counter = NUMBER_SUB_ITERATION;
                model.restartGame();
            }
        }

        //call getGameBoardSideSize() method inside ModelImpl, checking everything works correctly.
        final int gameBoard4SideSize = model.getGameBoardSideSize();
        assertEquals(gameBoard4SideSize, GAME_BOARD_4_SIDE_SIZE);

        final List<Integer> itemsList = new LinkedList<>();

        for (int i = 0; i < NUMBER_SUB_ITERATION; i++) {
            final Optional<Integer> coin = model.tryGenerateCoin();
            if (coin.isPresent()) {
                itemsList.add(coin.get());
            }

            final Optional<Integer> diamond = model.tryGenerateDiamond();
            if (diamond.isPresent()) {
                itemsList.add(diamond.get());
            }

            final Optional<Integer> skull = model.tryGenerateSkull();
            if (skull.isPresent()) {
                itemsList.add(skull.get());
            }
        }

        assertTrue(itemsList.size() <= ITEMS_LIST_MAX_SIZE);

        final int listLength = itemsList.size();
        for (int i = 0; i < listLength; i++) {
            model.itemCollected(itemsList.get(i), Turn.CPU);
        }

        try {
            model.itemCollected(itemsList.get(0), Turn.CPU);
            this.failItemDoesntExist();
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentExceptionThrowing(e);
        }

        model.giveUpGame(); //end the game

    }

}
