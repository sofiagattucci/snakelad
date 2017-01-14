package controller;

/**.
 *Class that contain main method
 *
 */
public final class Application {

    private final Controller controller;

    private Application() {
        this.controller = new Controller();
        this.controller.startApplication();
    }

    /**.
     * Main method
     * @param args arguments of main method
     */
    public static void main(final String[] args) {
        final Application application = new Application();
    }

}
