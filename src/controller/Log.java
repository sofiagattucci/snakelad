package controller;

/**
 * This class is used to print strings in the console. Used in some circumstances but mostly for 
 * debug tests.
 * Used Singleton Pattern.
 */
public final class Log {

    private static final Log SINGLETON_LOG = new Log();

    private Log() { }

    /**
     * Getter of the Log.
     * @return
     *     The log
     */
    public static Log get() {
        return SINGLETON_LOG;
    }

    /**
     * Method to print a string on the console.
     * @param s
     *     The string to print
     */
    public void print(final String s) {
        System.out.println(s);
    }

}
