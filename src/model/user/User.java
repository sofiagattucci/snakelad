package model.user;

/**
 * Represents the user who's playing the game, with his status and statistics.
 */
public interface User {

    /**
     * Sets the name of the user who's playing the game.
     * @param name
     *          The name of the user who's playing the game.
     */
    void setName(String name);

    /**
     * Gets the name of the user who's playing the game.
     * @return the name of the user who's playing the game.
     * @throws IllegalStateException if the name to get is absent.
     */
    String getName() throws IllegalStateException;

    /**
     * Adds a number which represents the scores earned by the user who's playing the game.
     * @param scoresValue
     *                  The number which represents the scores earned by the user who's playing the game.
     * @throws IllegalArgumentException if scoresValue's value is not permitted (less than 0).
     */
    void addScores(int scoresValue) throws IllegalArgumentException;

    /**
     * Subtracts a number which represents the scores lost by the user who's playing the game.
     * @param scoresValue
     *                  The number which represents the scores lost by the user who's playing the game.
     * @throws IllegalArgumentException if scoresValue's value is not permitted (less than 0).
     */
    void subtractScores(int scoresValue) throws IllegalArgumentException;

    /**
     * Sets a number which represents the total scores of the user who's playing the game.
     * @param scoresValue
     *                  The number which represents the total scores of the user who's playing the game.
     * @throws IllegalArgumentException if scoresValue's value is not permitted (less than 0).
     */
    void setScores(int scoresValue) throws IllegalArgumentException;

    /**
     * Returns the scores of the current user.
     * @return the scores of the current user.
     */
    int getScores();

}