package model.player;

/**
 * The interface for a player.
 */
public interface Player {

    /**
     * Sets the player's position on the current game board.
     * @param playerPosition
     *          The number which represents the new position of the player on the numbered game board.
     * @throws IllegalArgumentException if the parameter 'newPosition' is not allowed.
     */
    void setPosition(int playerPosition) throws IllegalArgumentException;

    /**
     * Returns the current position of the player on the game board.
     * @return the number which represents the current position of the player on the numbered game board.
     */
    int getPosition();

}