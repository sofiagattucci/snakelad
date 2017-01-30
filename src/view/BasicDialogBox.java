package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Basic implementation for a dialogue box.
 */
public abstract class BasicDialogBox {

    private final Alert end = new Alert(AlertType.INFORMATION);
    private final Stage parentSt;

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     */
    public BasicDialogBox(final Stage parentStage) {

        this.end.initOwner(parentStage);
        this.end.initModality(Modality.APPLICATION_MODAL);
        this.end.getButtonTypes().clear();
        this.parentSt = parentStage;
    }

    /**
     * Getter of the box's stage.
     * @return
     *     The stage that contains the dialog box.
     */
    protected Stage getStage() {
        return this.parentSt;
    }

    /**
     * It shows the pause box, then manages the choice the user made. 
     */
    public abstract void show();
}
