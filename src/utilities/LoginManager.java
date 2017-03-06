package utilities;

/**
 * It's used to read and write files which cointains login informations.
 * It's designed using Singleton Pattern.
 */
public final class LoginManager implements FileManager {

    private static final LoginManager SINGLETON = new LoginManager();

    // private constructor
    private LoginManager() { 

    }

    /**
     * Static method which returns the LoginManager unique instance.
     * @return the LoginManager unique instance.
     */
    public static LoginManager get() {
        return SINGLETON;
    }

    @Override
    public Object readFromFile(final String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void writeToFile(final String path) {
        // TODO Auto-generated method stub
    }

}
