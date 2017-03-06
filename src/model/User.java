package model;

/**
 * Represents the user who's playing the game, with his status and statistics.
 */
public interface User {

    /**
     * Adds a number which represents the scores won by the user who's playing the game.
     * @param scoresValue
     *                  The number which represents the scores won by the user who's playing the game.
     * @throws IllegalArgumentException if scoresValue's value is not permitted (less than 0).
     */
    void addScores(int scoresValue) throws IllegalArgumentException;

    /**
     * Returns the scores of the current user.
     * @return the scores of the current user.
     */
    int getScores();

}