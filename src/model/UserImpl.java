package model;

/**
 * Represents the user who's playing the game, with his status and statistics.
 * It's designed using Singleton Pattern.
 */
public final class UserImpl implements User {

    private static final User SINGLETON = new UserImpl();

    private int scores;

    //private constructor
    private UserImpl() {

    }

    /**
     * Static method which returns the UserImpl unique instance.
     * @return the UserImpl unique instance.
     */
    public static User get() {
        return UserImpl.SINGLETON;
    }

    @Override
    public void addScores(final int scoresValue) throws IllegalArgumentException {
        if (scoresValue < 0) {
            throw new IllegalArgumentException("A scores' value less than 0 is not permitted!");
        }

        this.scores += scoresValue;
    }

    @Override
    public int getScores() {
        return this.scores;
    }

}
