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
    private static final int NUMBER_OF_BOXES = 35;
    private static final int GAME_BOARD_1_SIDE_SIZE = 6;

    private final Map<Integer, Integer> snakesMap = new HashMap<>();
    private final Map<Integer, Integer> laddersMap = new HashMap<>();

    //private method called to avoid too much repetition of identical code.
    private void printIllegalArgumentException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalArgumentException thrown with success inside SceneryImplTest.");
    }

    //private method called to avoid too much repetition of identical code.
    private void printIllegalStateException() {
        final ConsoleLog log = ConsoleLog.get();
        log.print("IllegalStateException thrown with success inside SceneryImplTest.");
    }


    /**
     * Tests all methods inside UserImpl class.
     */
    @Test
    public void testSceneryImpl() {
        final List<Integer> dataList = new LinkedList<>(SceneryDataManager.get().readFromFile(GAME_BOARD_1));
        Scenery scenery = new SceneryFactoryImpl().setUpScenery(dataList);

        //call setNumberOfBoxes() with argument's value 0. It must throw an IllegalArgumentException.
        try {
            scenery.setNumberOfBoxes(0);
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        //call setSnakesMap() with an empty map argument. It must throw an IllegalArgumentException.
        try {
            scenery.setSnakesMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        //call setLaddersMap() with an empty map argument. It must throw an IllegalArgumentException.
        try {
            scenery.setLaddersMap(Collections.emptyMap());
        } catch (final IllegalArgumentException e) {
            this.printIllegalArgumentException();
        } catch (final Exception e) {
            fail("should throw an IllegalArgumentException, not a " + e.getClass());
        }

        this.snakesMap.putAll(scenery.getSnakesMap());
        this.laddersMap.putAll(scenery.getLaddersMap());

        assertEquals(scenery.getNumberOfBoxes(), NUMBER_OF_BOXES);

        assertEquals(scenery.getSideSize(), GAME_BOARD_1_SIDE_SIZE);

        scenery.clearMaps();

        //call getSnakesMap() when is empty. It must throw an IllegalStateException.
        try {
            scenery.getSnakesMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

        //call getLaddersMap() when is empty. It must throw an IllegalStateException.
        try {
            scenery.getLaddersMap();
        } catch (final IllegalStateException e) {
            this.printIllegalStateException();
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().setUpScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);

        scenery.clearMaps();

        //Set up again the scenery, filling its maps
        scenery = new SceneryFactoryImpl().setUpScenery(dataList);

        assertEquals(scenery.getSnakesMap(), this.snakesMap);
        assertEquals(scenery.getLaddersMap(), this.laddersMap);

    }
}
