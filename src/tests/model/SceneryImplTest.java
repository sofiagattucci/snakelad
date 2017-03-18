package tests.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.scenery.Scenery;
import model.scenery.SceneryFactoryImpl;
import utilities.ConsoleLog;
import utilities.SceneryDataManager;

/**
 * Junit test used in order to test SceneryImpl class.
 * This class has to achieve success in all its tests.
 */
public class SceneryImplTest {

    private static final String GAME_BOARD_1 = "./res/gameBoards/gameBoard1/file.txt";
    private static final String GAME_BOARD_2 = "./res/gameBoards/gameBoard2/file.txt";
    private static final String GAME_BOARD_3 = "./res/gameBoards/gameBoard3/file.txt";
    private static final String GAME_BOARD_4 = "./res/gameBoards/gameBoard4/file.txt";
    private static final int NUMBER_OF_BOXES_GAME_BOARD_1 = 35;
    private static final int NUMBER_OF_BOXES_GAME_BOARD_2 = 63;
    private static final int NUMBER_OF_BOXES_GAME_BOARD_3 = 63;
    private static final int NUMBER_OF_BOXES_GAME_BOARD_4 = 99;
    private static final int GAME_BOARD_1_SIDE_SIZE = 6;
    private static final int GAME_BOARD_2_SIDE_SIZE = 8;
    private static final int GAME_BOARD_3_SIDE_SIZE = 8;
    private static final int GAME_BOARD_4_SIDE_SIZE = 10;

    private final Map<Integer, Integer> snakesMap = new HashMap<>();
    private final Map<Integer, Integer> laddersMap = new HashMap<>();

    //private method called to avoid too much repetition of identical code
    private void printIllegalArgumentException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalArgumentException thrown with success inside SceneryImplTest.");
    }

    //private method called to avoid too much repetition of identical code
    private void printIllegalStateException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside SceneryImplTest.");
    }

    //private method called to avoid too much repetition of identical code
    private void failIllegalArgumentException(final Exception e) {
        fail("should throw an IllegalArgumentException, not a " + e.getClass());
    }

    //private method called to avoid too much repetition of identical code
    private void failIllegalStateException(final Exception e) {
        fail("should throw an IllegalStateException, not a " + e.getClass());
    }

    //private method called to avoid too much repetition of identical code
    private void clearMaps() {
        this.snakesMap.clear();
        this.laddersMap.clear();
    }

    /**
     * Tests all methods inside SceneryImpl class, using game board n.1.
     */
    @Test
    public void testSceneryImplGameBoard1() {
        final List<Integer> dataList = new LinkedList<>(SceneryDataManager.get().readFromFile(GAME_BOARD_1));
        Scenery scenery = new SceneryFactoryImpl().createScenery(dataList);

        //call setNumberOfBoxes() with argument's value 0. It must throw an IllegalArgumentException
        try {
            scenery.setNumberOfBoxes(0);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setSnakesMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setSnakesMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setLaddersMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setLaddersMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        this.snakesMap.putAll(scenery.getSnakesMap());
        this.laddersMap.putAll(scenery.getLaddersMap());

        assertEquals(scenery.getNumberOfBoxes(), NUMBER_OF_BOXES_GAME_BOARD_1);

        assertEquals(scenery.getSideSize(), GAME_BOARD_1_SIDE_SIZE);

        scenery.clearMaps();

        //call getSnakesMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getSnakesMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //call getLaddersMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getLaddersMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);

        scenery.clearMaps();

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);
        this.clearMaps();
    }

    /**
     * Tests all methods inside SceneryImpl class, using game board n.2.
     */
    @Test
    public void testSceneryImplGameBoard2() {
        final List<Integer> dataList = new LinkedList<>(SceneryDataManager.get().readFromFile(GAME_BOARD_2));
        Scenery scenery = new SceneryFactoryImpl().createScenery(dataList);

        //call setNumberOfBoxes() with argument's value 0. It must throw an IllegalArgumentException
        try {
            scenery.setNumberOfBoxes(0);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setSnakesMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setSnakesMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setLaddersMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setLaddersMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        this.snakesMap.putAll(scenery.getSnakesMap());
        this.laddersMap.putAll(scenery.getLaddersMap());

        assertEquals(scenery.getNumberOfBoxes(), NUMBER_OF_BOXES_GAME_BOARD_2);

        assertEquals(scenery.getSideSize(), GAME_BOARD_2_SIDE_SIZE);

        scenery.clearMaps();

        //call getSnakesMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getSnakesMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //call getLaddersMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getLaddersMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);

        scenery.clearMaps();

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);
        this.clearMaps();
    }

    /**
     * Tests all methods inside SceneryImpl class, using game board n.3.
     */
    @Test
    public void testSceneryImplGameBoard3() {
        final List<Integer> dataList = new LinkedList<>(SceneryDataManager.get().readFromFile(GAME_BOARD_3));
        Scenery scenery = new SceneryFactoryImpl().createScenery(dataList);

        //call setNumberOfBoxes() with argument's value 0. It must throw an IllegalArgumentException
        try {
            scenery.setNumberOfBoxes(0);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setSnakesMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setSnakesMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setLaddersMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setLaddersMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        this.snakesMap.putAll(scenery.getSnakesMap());
        this.laddersMap.putAll(scenery.getLaddersMap());

        assertEquals(scenery.getNumberOfBoxes(), NUMBER_OF_BOXES_GAME_BOARD_3);

        assertEquals(scenery.getSideSize(), GAME_BOARD_3_SIDE_SIZE);

        scenery.clearMaps();

        //call getSnakesMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getSnakesMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //call getLaddersMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getLaddersMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);

        scenery.clearMaps();

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);
        this.clearMaps();
    }

    /**
     * Tests all methods inside SceneryImpl class, using game board n.4.
     */
    @Test
    public void testSceneryImplGameBoard4() {
        final List<Integer> dataList = new LinkedList<>(SceneryDataManager.get().readFromFile(GAME_BOARD_4));
        Scenery scenery = new SceneryFactoryImpl().createScenery(dataList);

        //call setNumberOfBoxes() with argument's value 0. It must throw an IllegalArgumentException
        try {
            scenery.setNumberOfBoxes(0);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setSnakesMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setSnakesMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        //call setLaddersMap() with an empty map argument. It must throw an IllegalArgumentException
        try {
            scenery.setLaddersMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            this.failIllegalArgumentException(e);
        }

        this.snakesMap.putAll(scenery.getSnakesMap());
        this.laddersMap.putAll(scenery.getLaddersMap());

        assertEquals(scenery.getNumberOfBoxes(), NUMBER_OF_BOXES_GAME_BOARD_4);

        assertEquals(scenery.getSideSize(), GAME_BOARD_4_SIDE_SIZE);

        scenery.clearMaps();

        //call getSnakesMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getSnakesMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //call getLaddersMap() when is empty. It must throw an IllegalStateException
        try {
            scenery.getLaddersMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            this.failIllegalStateException(e);
        }

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);

        scenery.clearMaps();

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().createScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);
        this.clearMaps();
    }

}
