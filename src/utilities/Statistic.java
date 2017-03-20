package utilities;

/**
 * An interface to get user's statistics: number of games won/lost, total number of times 
 * the user has rolled a dice and user's current scores.
 */
public interface Statistic {

    /**
     * Gets the number of games won by the user.
     * @return the number of games won by the user.
     */
    int getGamesWon();

    /**
     * Gets the number of games lost by the user.
     * @return the number of games lost by the user.
     */
    int getGamesLost();

    /**
     * Gets the total number of times the user has rolled a dice.
     * @return the total number of times the user has rolled a dice.
     */
    int getNumberOfDiceRoll();

    /**
     * Gets the current user's scores.
     * @return the current user's scores.
     */
    int getScores();

}