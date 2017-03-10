package utilities;

/**
 * An interface to manage user's statistics: number of games won/lost, total number of times 
 * the user has rolled a dice and user's current scores.
 */
public interface Statistic {

    /**
     * Sets the number of games won by the user.
     * @param gamesWon
     *                  The number of games won by the user.
     */
    void setGamesWon(int gamesWon);

    /**
     * Sets the number of games lost by the user.
     * @param gamesLost
     *                  The number of games lost by the user.
     */
    void setGamesLost(int gamesLost);

    /**
     * Sets the total number of times the user has rolled a dice.
     * @param numberOfDiceRoll
     *                  The total number of times the user has rolled a dice.
     */
    void setNumberOfDiceRoll(int numberOfDiceRoll);

    /**
     * Sets the current user's scores.
     * @param scores
     *                  The current user's scores.
     */
    void setScores(int scores);

}
