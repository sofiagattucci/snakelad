package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import model.user.User;
import model.user.UserImpl;

/**
 * It's used to read and write files which contains login informations.
 * It's designed using Singleton Pattern.
 */
public final class UserLogin {

    private static final UserLogin SINGLETON = new UserLogin();
    private static final String USERS_DIRECTORY = "./res/users/";
    private static final String USERS_SUFFIX = ".properties";
    private static final String USER_SCORES_KEY = "Scores";
    private static final String USER_NUMBER_OF_DICE_ROLL_KEY = "NumberOfDiceRoll";
    private static final String USER_GAMES_WON_KEY = "GamesWon";
    private static final String USER_GAMES_LOST_KEY = "GamesLost";

    private final User user = UserImpl.get();

    //private constructor
    private UserLogin() { 

    }

    /**
     * Static method which returns the LoginManager unique instance.
     * @return the LoginManager unique instance.
     */
    public static UserLogin get() {
        return SINGLETON;
    }

    private void fillUserWithInfo(final Map<String, String> map) {
        if (!map.containsKey(USER_SCORES_KEY) || !map.containsKey(USER_NUMBER_OF_DICE_ROLL_KEY)
            || !map.containsKey(USER_GAMES_WON_KEY) || !map.containsKey(USER_GAMES_LOST_KEY)) {
            throw new IllegalStateException("The file hasn't all required keys. It's damaged! Please fix file and relaunch the game");
        }

        this.user.setScores(Integer.parseInt(map.get(USER_SCORES_KEY)));
        this.user.setNumberOfDiceRoll(Integer.parseInt(map.get(USER_NUMBER_OF_DICE_ROLL_KEY)));
        this.user.setGamesWon(Integer.parseInt(map.get(USER_GAMES_WON_KEY)));
        this.user.setGamesLost(Integer.parseInt(map.get(USER_GAMES_LOST_KEY)));
    }

    private void extractUserInfoFromFile(final File userFile) {
        final Properties properties = new Properties();

        try {
            final FileInputStream fis = new FileInputStream(userFile);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            ConsoleLog.get().print("ERROR occurred during loading properties file located at: " + userFile.getPath());
            e.printStackTrace();
        }

        final Set<String> keys = properties.stringPropertyNames();
        final Map<String, String> map = new HashMap<>();
        for (final String key : keys) {
            map.put(key, properties.getProperty(key));
        }

        this.fillUserWithInfo(map);
    }

    private void createNewUserDefaultFile(final File userFile) throws IOException {
        try {
            userFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Error during creation of user's default file");
        }

        final Properties properties = new Properties();
        properties.setProperty(USER_SCORES_KEY, String.valueOf(0));
        properties.setProperty(USER_NUMBER_OF_DICE_ROLL_KEY, String.valueOf(0));
        properties.setProperty(USER_GAMES_WON_KEY, String.valueOf(0));
        properties.setProperty(USER_GAMES_LOST_KEY, String.valueOf(0));

        try {
            final FileOutputStream fos = new FileOutputStream(userFile);
            properties.store(fos, "Statistic of " + this.user.getName());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("ERROR occurred during storing user's statistics into properties "
                                + "file located at: " + userFile.getPath());
        }

    }

    /**
     * Allows to login a new user or an existing one in external properties files.
     * @param userName
     *                  The name of the user who wants to login.
     * @throws IllegalArgumentException if the 'userName' is empty (absent).
     * @throws IOException if an error about input/output happened.
     */
    public void login(final String userName) throws IllegalArgumentException, IOException {
        if (userName.isEmpty()) {
            throw new IllegalArgumentException("The userName is absent!");
        }

        final File file = new File(USERS_DIRECTORY + userName + USERS_SUFFIX);

        if (file.exists()) {
            this.user.setName(userName);
            this.extractUserInfoFromFile(file);
        } else { //the file doesn't exist or 'users' directory doesn't exist
            final File dir = new File(USERS_DIRECTORY);
            if (dir.isDirectory()) {
                this.user.setName(userName);
                this.createNewUserDefaultFile(file);
            } else {
                if (!dir.mkdir()) { //if the directory is not created (RuntimeException is thrown)
                    throw new RuntimeException("Error during creating the empty directory 'users'.");
                }
                this.user.setName(userName);
                this.createNewUserDefaultFile(file);
            }
        }

    }

}
