package utilities;

import java.util.function.Supplier;

/**
 * Represents the user's statistics: number of games won/lost, total number of times the user 
 * has rolled a dice and user's current scores.
 * It has an inner builder class designed using Builder pattern in a 'fluent' way.
 */
public final class StatisticImpl implements Statistic {

    private static final Supplier<RuntimeException> ILLEGAL_ARG_EXC_SUPPLIER = () -> new IllegalArgumentException("Argument passed less than 0!");

    private final int gamesWon;
    private final int gamesLost;
    private final int numberOfDiceRoll;
    private final int scores;

    //package level access (visibility)
    StatisticImpl(final int gamesWon, final int gamesLost, final int numberOfDiceRoll, final int scores) {
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.numberOfDiceRoll = numberOfDiceRoll;
        this.scores = scores;
    }

    /**
     * An inner builder for StatisticImpl objects.
     * It's designed using Builder pattern in a 'fluent' way.
     */
    public static class StatisticBuilder {

        private int gamesWonValue;
        private int gameLostValue;
        private int numberOfDiceRollValue;
        private int scoresValue;

        /**
         * Sets the number of games won by the user and returns the builder in order to get a fluent interface.
         * @param gamesWon
         *                  The number of games won by the user.
         * @return the builder in order to get a fluent interface.
         * @throws IllegalArgumentException if the unique argument passed is less than 0.
         */
        public StatisticBuilder gamesWon(final int gamesWon) {
            if (gamesWon < 0) {
                throw ILLEGAL_ARG_EXC_SUPPLIER.get();
            }

            this.gamesWonValue = gamesWon;
            return this;
        }

        /**
         * Sets the number of games lost by the user and returns the builder in order to get a fluent interface.
         * @param gamesLost
         *                  The number of games lost by the user.
         * @return the builder in order to get a fluent interface.
         * @throws IllegalArgumentException if the unique argument passed is less than 0.
         */
        public StatisticBuilder gamesLost(final int gamesLost) {
            if (gamesLost < 0) {
                throw ILLEGAL_ARG_EXC_SUPPLIER.get();
            }

            this.gameLostValue = gamesLost;
            return this;
        }

        /**
         * Sets the total number of times the user has rolled a dice and returns the builder in order to get a fluent interface.
         * @param numberOfDiceRoll
         *                  The total number of times the user has rolled a dice.
         * @return the builder in order to get a fluent interface.
         * @throws IllegalArgumentException if the unique argument passed is less than 0.
         */
        public StatisticBuilder numberOfDiceRoll(final int numberOfDiceRoll) {
            if (numberOfDiceRoll < 0) {
                throw ILLEGAL_ARG_EXC_SUPPLIER.get();
            }

            this.numberOfDiceRollValue = numberOfDiceRoll;
            return this;
        }

        /**
         * Sets the current user's scores and returns the builder in order to get a fluent interface.
         * @param score
         *                  The current user's scores.
         * @return the builder in order to get a fluent interface.
         * @throws IllegalArgumentException if the unique argument passed is less than 0.
         */
        public StatisticBuilder score(final int score) {
            if (score < 0) {
                throw ILLEGAL_ARG_EXC_SUPPLIER.get();
            }

            this.scoresValue = score;
            return this;
        }

        /**
         * Builds and returns a StatisticImpl object.
         * @return a StatisticImpl object.
         */
        public Statistic build() {
            return new StatisticImpl(this.gamesWonValue, this.gameLostValue, this.numberOfDiceRollValue, this.scoresValue);
        }

    }

    @Override
    public int getGamesWon() {
        return this.gamesWon;
    }

    @Override
    public int getGamesLost() {
        return this.gamesLost;
    }

    @Override
    public int getNumberOfDiceRoll() {
        return this.numberOfDiceRoll;
    }

    @Override
    public int getScores() {
        return this.scores;
    }

}
