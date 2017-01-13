package controller;

/**.
 *Class that contain main method
 *
 */
public class Application {

    private final Controller controller;

    private Application() {
        this.controller = new Controller();
        this.controller.startApplication();
    }

    /**.
     * Main method
     * @param args
     */
    public static void main(final String[] args) {
        final Application startGame = new Application();
    }

}
