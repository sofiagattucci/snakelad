package model;

/**
 * Implements methods for a player.
 */
public class PlayerImpl implements Player {

    private static final int STARTING_POSITION = 0;

    private int position;

    /**
     * Player constructor.
     */
    public PlayerImpl() {
        this.position = STARTING_POSITION;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setNewPosition(final int newPosition) throws IllegalArgumentException {
        if (newPosition < 0) {
            throw new IllegalArgumentException("Negative argument 'newPosition' is not allowed.");
        }
        this.position = newPosition;
    }

}
