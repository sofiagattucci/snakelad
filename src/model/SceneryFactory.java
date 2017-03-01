package model;

import java.util.List;

/**
 * A Factory for the game scenery.
 * It's designed using Factory Method pattern.
 */
public interface SceneryFactory {

    /**
     * Creates and returns a scenery.
     * @param data
     *          The list that contains the data (snakes and ladders positions, 
     *          number of cells on the game board) useful to start the game.
     * @return a scenery created from the 'data' argument.
     */
    Scenery createScenery(List<Integer> data);

}
