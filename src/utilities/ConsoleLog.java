package utilities;

/**
 * This class is used to print strings in the console. Used in some circumstances but mostly for 
 * debug tests.
 * It's designed using Singleton pattern.
 */
public final class ConsoleLog {

    private static final ConsoleLog SINGLETON = new ConsoleLog();

    // private constructor
    private ConsoleLog() {

    }

    /**
     * Static method which returns the ConsoleLog unique instance.
     * @return the ConsoleLog unique instance.
     */
    public static ConsoleLog get() {
        return ConsoleLog.SINGLETON;
    }

    /**
     * Method to print a string on the console.
     * @param s 
     *          The string to print.
     */
    public void print(final String s) {
        System.out.println(s);
    }

}
