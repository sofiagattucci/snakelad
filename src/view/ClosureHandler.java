package view;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

/**
 * This class handles the closure of the application. 
 */
public final class ClosureHandler {

    private static final String QUIT_MESSAGE = "Quitting...";
    private static final String CONFIRMATION_MESSAGE = "Do you really want to quit?"; 

    private final Alert confirmationBox = new Alert(AlertType.CONFIRMATION);

     /**
     * Constructor of the ClosureHandler.
     * @param parentStage
     *     The parent stage of this closure handler
     */
    public ClosureHandler(final Stage parentStage) {

        this.confirmationBox.initOwner(parentStage);
        this.confirmationBox.initModality(Modality.APPLICATION_MODAL);
        this.confirmationBox.setTitle(QUIT_MESSAGE);
        this.confirmationBox.setHeaderText(CONFIRMATION_MESSAGE);
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
