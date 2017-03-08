package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Allows to set statistics of user who's playing the game.
 * It's designed using Singleton Pattern.
 */
public final class UserStatisticsWriter {

    private static final UserStatisticsWriter SINGLETON = new UserStatisticsWriter();
    private static final String USERS_DIRECTORY = "./res/users/";
    private static final String USERS_SUFFIX = ".properties";
    private static final String USER_SCORES_KEY = "Scores";

    //private constructor
    private UserStatisticsWriter() { 

    }

    /**
     * Static method which returns the UserStatisticsWriter unique instance.
     * @return the UserStatisticsWriter unique instance.
     */
    public static UserStatisticsWriter get() {
        return SINGLETON;
    }

    /**
     * Sets the user current scores into his properties file.
     * @param userName
     *                  The string which represents the user's name.
     * @param userScores
     *                  The number which represents the current user's scores.
     * @throws IllegalArgumentException if 'userName' is empty, 'userScores' is less 
     *         than 0 or user's properties file is absent.
     */
    public void setUserScores(final String userName, final int userScores) throws IllegalArgumentException {
        if (userName.isEmpty()) {
            throw new IllegalArgumentException("The user's name is absent!");
        }

        if (userScores < 0) {
            throw new IllegalArgumentException("The user's scores is less than 0!");
        }

        final File file = new File(USERS_DIRECTORY + userName + USERS_SUFFIX);

        if (!file.exists()) {
            throw new IllegalArgumentException("The user properties file is absent!");
        }

        final Properties properties = new Properties();
        properties.setProperty(USER_SCORES_KEY, String.valueOf(userScores));

        try {
            final FileOutputStream fos = new FileOutputStream(file);
            properties.store(fos, "User's scores");
            fos.close();
        } catch (IOException e) {
            ConsoleLog.get().print("ERROR occurred during storing user's scores into properties "
                                 + "file located at: " + file.getPath());
            e.printStackTrace();
        }

    }

}
