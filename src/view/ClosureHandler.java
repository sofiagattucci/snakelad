package view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

/**
 * This class handles the closure of the application. 
 */
public final class ClosureHandler {

    private static final String QUIT_MESSAGE = "Quitting...";
    private static final String CONFIRMATION_MESSAGE = "Do you really want to quit?"; 

    private static final ClosureHandler CLOSURE = new ClosureHandler();
    private static Stage closureStage;

    private ClosureHandler() {
        final Alert confirmationBox = new Alert(AlertType.CONFIRMATION);
        confirmationBox.initOwner(closureStage);
        confirmationBox.setTitle(QUIT_MESSAGE);
        confirmationBox.setHeaderText(CONFIRMATION_MESSAGE);
        final Optional<ButtonType> result = confirmationBox.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    /**
     * Getter of the ClosureHandler.
     * @param stage
     *     A stage to be linked to the one of this class
     * @return
     *     The closure handler
     */
    public static ClosureHandler get(final Stage stage) {
        closureStage = stage;
        return CLOSURE;
    }
}
