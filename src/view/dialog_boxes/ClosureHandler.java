package view.dialog_boxes;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * This class handles the closure of the application. 
 */
public final class ClosureHandler extends BasicDialogBox {

    private static final String QUIT_MESSAGE = "Quitting...";
    private static final String CONFIRMATION_MESSAGE = "Do you really want to quit?"; 

     /**
     * Constructor of the ClosureHandler.
     * @param parentStage
     *     The parent stage of this closure handler
     */
    public ClosureHandler(final Stage parentStage) {
        super(parentStage);
        this.getBox().setTitle(QUIT_MESSAGE);
        this.getBox().setHeaderText(CONFIRMATION_MESSAGE);
        this.getBox().getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
    }

    @Override
    public void show() {
        if (this.getBox().showAndWait().get() == ButtonType.OK) {
             Platform.exit();
        }
    }
}
