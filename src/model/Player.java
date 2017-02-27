package model;

/**
 * The interface for a player.
 */
public interface Player {

    /**
     * Returns the current position of the player on the game board.
     * @return the number which represents the current position of the player on the numbered gameboard.
     */
    int getPosition();

    /**
     * Sets the new position on the game board in which the player now stands.
     * @param newPosition
     *          The number which represents the new position of the player on the numbered gameboard.
     * @throws IllegalArgumentException if the param 'newPosition' is not allowed.
     */
    void setNewPosition(int newPosition) throws IllegalArgumentException;

}