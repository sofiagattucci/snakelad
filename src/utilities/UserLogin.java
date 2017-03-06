package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import model.User;
import model.UserImpl;

/**
 * It's used to read and write files which cointains login informations.
 * It's designed using Singleton Pattern.
 */
public final class UserLogin {

    private static final UserLogin SINGLETON = new UserLogin();
    private static final String USERS_DIRECTORY = "./res/Users/";
    private static final String USERS_SUFFIX = ".properties";
    private static final String USER_SCORES_KEY = "Scores";

    private final User user = UserImpl.get();

    // private constructor
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
        if (!map.containsKey(USER_SCORES_KEY)) {
            throw new IllegalStateException("The file hasn't 'Scores' key. It's damaged!");
        }

        user.setScores(Integer.parseInt(map.get(USER_SCORES_KEY)));
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

    private void createNewUserDefaultFile(final File userFile) {
        try {
            userFile.createNewFile();
        } catch (IOException e) {
            ConsoleLog.get().print("ERROR occurred during creating new user file located at: " + userFile.getPath());
            e.printStackTrace();
        }

        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(userFile))) {
            bw.write(USER_SCORES_KEY + " = 0");
            bw.newLine();
        } catch (IOException exception) {
            ConsoleLog.get().print("ERROR occurred during writing user file located at: " + userFile.getPath());
            exception.printStackTrace();
        }
    }

    /**
     * Allows to login a new user or an existing one in external properties files.
     * @param userName
     *                  The name of the user who wants to login.
     * @throws IllegalArgumentException if the 'userName' is empty (absent).
     */
    public void login(final String userName) throws IllegalArgumentException {
        if (userName.isEmpty()) {
            throw new IllegalArgumentException("The userName is absent!");
        }

        final File file = new File(USERS_DIRECTORY + userName + USERS_SUFFIX);

        if (file.exists()) {
            this.extractUserInfoFromFile(file);
        } else {
            this.createNewUserDefaultFile(file);
        }

    }

}
