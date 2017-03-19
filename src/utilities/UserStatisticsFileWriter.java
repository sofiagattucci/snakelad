package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.function.Supplier;

import model.user.User;
import model.user.UserImpl;

/**
 * Allows to set statistics of user who's playing the game.
 * It's designed using Singleton Pattern.
 */
public final class UserStatisticsFileWriter {

    private static final Supplier<RuntimeException> ILLEGAL_ARG_EXC_SUPPLIER = () -> new IllegalArgumentException("The argument passed "
                                                                                                                + "is less than 0!");
    private static final UserStatisticsFileWriter SINGLETON = new UserStatisticsFileWriter();
    private static final String USERS_DIRECTORY = "./res/users/";
    private static final String USERS_SUFFIX = ".properties";
    private static final String USER_SCORES_KEY = "Scores";
    private static final String USER_NUMBER_OF_DICE_ROLL_KEY = "NumberOfDiceRoll";
    private static final String USER_GAMES_WON_KEY = "GamesWon";
    private static final String USER_GAMES_LOST_KEY = "GamesLost";

    private final User user;

    //private constructor
    private UserStatisticsFileWriter() { 
        this.user = UserImpl.get();
    }

    /**
     * Static method which returns the UserStatisticsWriter unique instance.
     * @return the UserStatisticsWriter unique instance.
     */
    public static UserStatisticsFileWriter get() {
        return SINGLETON;
    }

    /**
     * Writes the user's current scores into his .properties file.
     * @param userScores
     *                  The number which represents the current user's scores.
     * @param numberOfDiceRoll
     *                  The total number of times the user has rolled a dice.
     * @param gamesWon
     *                  The number of games won by the user.
     * @param gamesLost
     *                  The number of games lost by the user.
     * @throws IllegalArgumentException if the arguments passed are less than 0 or user's properties file is absent.
     * @throws IOException if an error about input/output happened.
     */
    public void writeUserStatistics(final int userScores, final int numberOfDiceRoll, 
                                    final int gamesWon, final int gamesLost) throws IllegalArgumentException, IOException {

        if (userScores < 0 || numberOfDiceRoll < 0 || gamesWon < 0 || gamesLost < 0) {
            throw ILLEGAL_ARG_EXC_SUPPLIER.get();
        }

        final File file = new File(USERS_DIRECTORY + this.user.getName() + USERS_SUFFIX);

        if (!file.exists()) {
            throw new IllegalArgumentException("The user properties file is absent!");
        }

        final Properties properties = new Properties();
        properties.setProperty(USER_SCORES_KEY, String.valueOf(userScores));
        properties.setProperty(USER_NUMBER_OF_DICE_ROLL_KEY, String.valueOf(numberOfDiceRoll));
        properties.setProperty(USER_GAMES_WON_KEY, String.valueOf(gamesWon));
        properties.setProperty(USER_GAMES_LOST_KEY, String.valueOf(gamesLost));

        try {
            final FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, "Statistics of " + this.user.getName());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("ERROR occurred during storing user's statistics into properties "
                                + "file located at: " + file.getPath());
        }

    }

}
