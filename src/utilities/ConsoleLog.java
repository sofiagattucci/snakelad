package utilities;

/**
 * This class is used to print strings in the console. Used in some circumstances but mostly for 
 * debug tests.
 * Used Singleton Pattern.
 */
public final class ConsoleLog {

    private static final ConsoleLog SINGLETON_LOG = new ConsoleLog();

    private ConsoleLog() { }

    /**
     * Getter of the Log.
     * @return
     *     The log.
     */
    public static ConsoleLog get() {
        return SINGLETON_LOG;
    }

    /**
     * Method to print a string on the console.
     * @param s
     *     The string to print.
     */
    public void print(final String s) {
        System.out.println(s);
    }

}
