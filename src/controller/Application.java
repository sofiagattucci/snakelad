package controller;

/**
 *Class that contain main method.
 *
 */
public final class Application {

    private final Controller controller;

    private Application() {
        this.controller = Controller.getController();
    }

    /**
     * Start the application.
     */
    private void startApplication() {
        this.controller.start();
    }

    /**
     * Main method.
     * @param args ignored.
     */
    public static void main(final String[] args) {
        final Application application = new Application();
        application.startApplication();
    }

}
