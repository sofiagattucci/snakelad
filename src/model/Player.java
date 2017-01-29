package model;

/**
 * The interface for a game player.
 * Defines the main methods of the Player class.
 */
public class Player {

    private static final int STARTING_POSITION = 0;

    private int position;

    /**
     * Player constructor.
     */
    public Player() {
        this.position = STARTING_POSITION;
    }

    /**
     * Returns the current position of the player on the game board.
     * @return the number which represents the current position of the player on the numbered game board.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Sets the new position on the game board in which the player now stands.
     * @param newPosition
     *          The number which represents the new position of the player on the numbered game board.
     */
    public void setNewPosition(final int newPosition) {
        this.position = newPosition;
    }

}
