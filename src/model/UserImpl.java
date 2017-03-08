package model;

import java.util.Optional;

/**
 * Represents the user who's playing the game, with his status and statistics.
 * It's designed using Singleton Pattern.
 */
public final class UserImpl implements User {

    private static final UserImpl SINGLETON = new UserImpl();

    private Optional<String> name;
    private int scores;

    //private constructor
    private UserImpl() {
        this.name = Optional.empty();
        this.scores = 0;
    }

    /**
     * Static method which returns the UserImpl unique instance.
     * @return the UserImpl unique instance.
     */
    public static UserImpl get() {
        return UserImpl.SINGLETON;
    }

    //private method called to avoid too much repetition of identical code.
    private void checkScoresValue(final int scoresValue) {
        if (scoresValue < 0) {
            throw new IllegalArgumentException("Illegal score value (It's less than 0).");
        }
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
    public void addScores(final int scoresValue) throws IllegalArgumentException {
        this.checkScoresValue(scoresValue);
        this.scores += scoresValue;
    }

    @Override
    public void subtractScores(final int scoresValue) throws IllegalArgumentException {
        this.checkScoresValue(scoresValue);
        this.scores -= scoresValue;
        if (this.scores < 0) {
            this.scores = 0;
        }
    }

    @Override
    public void setScores(final int scoresValue) throws IllegalArgumentException {
        this.checkScoresValue(scoresValue);
        this.scores = scoresValue;
    }

    @Override
    public int getScores() {
        return this.scores;
    }

}
