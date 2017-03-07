package model;

import java.util.Map;

/**
 * Interface of a game's scenery. 
 * Defines default scenery's methods.
 */
public interface Scenery {

    /**
     * Sets the number of boxes in the scenery's game board.
     * @param numberOfBoxes
     *                  The number of boxes in the scenery's game board.
     * @throws IllegalArgumentException if the argument is less or equal to 0.
     */
    void setNumberOfBoxes(int numberOfBoxes) throws IllegalArgumentException;

    /**
     * Sets the snakes position map inside the scenery.
     * @param snakesMap
     *                  Map which contains positions of all snakes in the scenery.
     * @throws IllegalArgumentException if the map argument is an empty map.
     */
    void setSnakesMap(Map<Integer, Integer> snakesMap) throws IllegalArgumentException;


    /**
     * Sets the ladders position map inside the scenery.
     * @param laddersMap
     *                  Map which contains positions of all ladders in the scenery.
     * @throws IllegalArgumentException if the map argument is an empty map.
     */
    void setLaddersMap(Map<Integer, Integer> laddersMap) throws IllegalArgumentException;

    /**
     * Returns the number of boxes in the current game board.
     * @return the number of boxes in the current game board.
     * @throws IllegalStateException if 'numberOfBoxes' has not been set.
     */
    int getNumberOfBoxes() throws IllegalStateException;

    /**
     * Returns a map which contains positions of all snakes in the scenery.
     * @return a map which contains positions of all snakes in the scenery.
     */
    Map<Integer, Integer> getSnakesMap();

    /**
     * Returns a map which contains positions of all ladders in the scenery.
     * @return a map which contains positions of all ladders in the scenery.
     */
    Map<Integer, Integer> getLaddersMap();

    /**
     * Returns the number which represents the length of the current game board's side.
     * @return the number which represents the length of the current game board's side.
     */
    int getSideSize();

    /**
     * Clears snakes and ladders maps.
     */
    void clearMaps();

}
