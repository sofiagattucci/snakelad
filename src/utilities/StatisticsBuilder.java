package utilities;

/**
 * A builder for the Statistic object.
 * It's designed using Builder pattern.
 */
public interface StatisticsBuilder {

    /**
     * Sets the number of games won by the user and returns the builder in order to get a fluent interface.
     * @param gamesWon
     *                  The number of games won by the user.
     * @return the builder in order to get a fluent interface.
     */
    StatisticsBuilder gamesWon(int gamesWon);

    /**
     * Sets the number of games lost by the user and returns the builder in order to get a fluent interface.
     * @param gamesLost
     *                  The number of games lost by the user.
     * @return the builder in order to get a fluent interface.
     */
    StatisticsBuilder gamesLost(int gamesLost);

    /**
     * Sets the total number of times the user has rolled a dice and returns the builder in order to get a fluent interface.
     * @param numberOfDiceRoll
     *                  The total number of times the user has rolled a dice.
     * @return the builder in order to get a fluent interface.
     */
    StatisticsBuilder numberOfDiceRoll(int numberOfDiceRoll);

    /**
     * Sets the current user's scores and returns the builder in order to get a fluent interface.
     * @param scores
     *                  The current user's scores.
     * @return the builder in order to get a fluent interface.
     */
    StatisticsBuilder scores(int scores);

}
