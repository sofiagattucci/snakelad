package model.user;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Represents the user who's playing the game, with his status and statistics.
 * It's designed using Singleton Pattern.
 */
public final class UserImpl implements User {

    private static final Supplier<RuntimeException> ILLEGAL_ARG_EXC_SUPPLIER = () -> new IllegalArgumentException("The argument passed"
                                                                                                                + " is less than 0!");
    private static final UserImpl SINGLETON = new UserImpl();

    private Optional<String> name;
    private int scores;
    private int numberOfDiceRoll;
    private int gamesWon;
    private int gamesLost;

    //private constructor
    private UserImpl() {
        this.name = Optional.empty();
        this.scores = 0;
        this.numberOfDiceRoll = 0;
        this.gamesLost = 0;
        this.gamesWon = 0;
    }

    //private method called in order to avoid repetition of identical code
    private void checkArgumentLessThanZero(final int argument) {
        if (argument < 0) {
            throw ILLEGAL_ARG_EXC_SUPPLIER.get();
        }
    }

    /**
     * Static method which returns the UserImpl unique instance.
     * @return the UserImpl unique instance.
     */
    public static UserImpl get() {
        return UserImpl.SINGLETON;
    }

    @Override
    public void setName(final String name) {
        this.name = Optional.of(name);
    }

    @Override
    public String getName() throws IllegalStateException {
        if (!this.name.isPresent()) {
            throw new IllegalStateException("The user's name is absent!");
        }

        return this.name.get();
    }

    @Override
    public void addScores(final int scoresValue) {
        this.scores += scoresValue;
        if (this.scores < 0) {
            this.scores = 0;
        }
    }

    @Override
    public void setScores(final int scoresValue) throws IllegalArgumentException {
        this.checkArgumentLessThanZero(scoresValue);
        this.scores = scoresValue;
    }

    @Override
    public int getScores() {
        return this.scores;
    }

    @Override
    public void setNumberOfDiceRoll(final int numberOfDiceRoll) throws IllegalArgumentException {
        this.checkArgumentLessThanZero(numberOfDiceRoll);
        this.numberOfDiceRoll = numberOfDiceRoll;
    }

    @Override
    public void setGamesWon(final int gamesWon) throws IllegalArgumentException {
        this.checkArgumentLessThanZero(gamesWon);
        this.gamesWon = gamesWon;
    }

    @Override
    public void setGamesLost(final int gamesLost) throws IllegalArgumentException {
        this.checkArgumentLessThanZero(gamesLost);
        this.gamesLost = gamesLost;
    }

    @Override
    public int getNumberOfDiceRoll() {
        return this.numberOfDiceRoll;
    }

    @Override
    public int getGamesWon() {
        return this.gamesWon;
    }

    @Override
    public int getGamesLost() {
        return this.gamesLost;
    }

}
