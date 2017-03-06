package model;

/**
 * Represents the user who's playing the game, with his status and statistics.
 * It's designed using Singleton Pattern.
 */
public final class UserImpl implements User {

    private static final UserImpl SINGLETON = new UserImpl();

    private int scores;

    //private constructor
    private UserImpl() {
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
            throw new IllegalArgumentException("Illegal score value (It's less than 0 or too big for the subtraction).");
        }
    }

    @Override
    public void addScores(final int scoresValue) throws IllegalArgumentException {
        this.checkScoresValue(scoresValue);
        this.scores += scoresValue;
    }

    @Override
    public void subtractScores(final int scoresValue) throws IllegalArgumentException {
        this.checkScoresValue(scoresValue);
        final int tmpScores = this.scores - scoresValue;
        this.checkScoresValue(tmpScores);
        this.scores = tmpScores;
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
