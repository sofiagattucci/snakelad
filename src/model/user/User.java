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
     * Adds a number which represents the score earned by the user who's playing the game.
     * @param scoreValue
     *                  The number which represents the score earned by the user who's playing the game.
     */
    void addScore(int scoreValue);

    /**
     * Sets a number which represents the total score of the user who's playing the game.
     * @param scoreValue
     *                  The number which represents the total score of the user who's playing the game.
     * @throws IllegalArgumentException if scoresValue's value is not permitted (less than 0).
     */
    void setScore(int scoreValue) throws IllegalArgumentException;

    /**
     * Sets the number which represents the total number of times the user has rolled a dice.
     * @param numberOfDiceRoll
     *                          The total number of times the user has rolled a dice.
     * @throws IllegalArgumentException if the number passed is less than 0.
     */
    void setNumberOfDiceRoll(int numberOfDiceRoll) throws IllegalArgumentException;

    /**
     * Sets the number of games won by the user.
     * @param gamesWon
     *                  The number of games won by the user.
     * @throws IllegalArgumentException if the number passed is less than 0.
     */
    void setGamesWon(int gamesWon) throws IllegalArgumentException;

    /**
     * Sets the number of games lost by the user.
     * @param gamesLost
     *                  The number of games lost by the user.
     * @throws IllegalArgumentException if the number passed is less than 0.
     */
    void setGamesLost(int gamesLost) throws IllegalArgumentException;

    /**
     * Returns the score of the current user.
     * @return the score of the current user.
     */
    int getScore();

    /**
     * Returns the number which represents the total number of times the user has rolled a dice.
     * @return the number which represents the total number of times the user has rolled a dice.
     */
    int getNumberOfDiceRoll();

    /**
     * Returns the number of games won by the user.
     * @return the number of games won by the user.
     */
    int getGamesWon();

    /**
     * Returns the number of games lost by the user.
     * @return the number of games lost by the user.
     */
    int getGamesLost();

}