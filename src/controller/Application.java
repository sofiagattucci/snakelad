package controller;

import java.util.Locale;
import java.util.ResourceBundle;

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

        final Locale locale = new Locale("it");
        final ResourceBundle strings = ResourceBundle.getBundle("LanguagesFiles.StringsBundle", locale);
        System.out.println(strings.getString("instructions"));
        //final Application application = new Application();
        //application.startApplication();
    }

}
