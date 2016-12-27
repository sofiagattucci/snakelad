package model;

/**
 * Interface for the Model.
 */
public interface Model {

    /**
     * Calculates a random integer and returns it.
     * 
     * @return the integer number randomly chosen.
     */
    int getRandomNumber();

    /**
     * 
     */
    void reset();

    /**
     * Starts a new game.
     */
    void newGame();
}