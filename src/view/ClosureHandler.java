package view;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * This class handles the closure of the application. 
 */
public final class ClosureHandler {

    private static final String QUIT_MESSAGE = "Quitting...";
    private static final String CONFIRMATION_MESSAGE = "Do you really want to quit?"; 

    private static final ClosureHandler CLOSURE = new ClosureHandler();
    private final Alert confirmationBox = new Alert(AlertType.CONFIRMATION);

    private ClosureHandler() {

        confirmationBox.setTitle(QUIT_MESSAGE);
        confirmationBox.setHeaderText(CONFIRMATION_MESSAGE);
    }

    /**
     * Getter of the ClosureHandler.
     * 
     * @return
     *     The closure handler
     */
    public static ClosureHandler get() {
        return CLOSURE;
    }
    /**
     * It handles the option that the user chooses in the confirmation box.
     */
    public void close() {
        if (confirmationBox.showAndWait().get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}
