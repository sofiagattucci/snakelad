package view.dialogboxes;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ViewImpl;

/**
 * Basic implementation for a dialog box.
 */
public abstract class BasicDialogBox {

    private final Alert box = new Alert(AlertType.INFORMATION);
    private final Stage parentSt;

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     */
    public BasicDialogBox(final Stage parentStage) {

        this.box.initOwner(parentStage);
        this.box.initModality(Modality.APPLICATION_MODAL);
        this.box.getButtonTypes().clear();
        this.parentSt = parentStage;
        this.box.getDialogPane().getStylesheets().add(ViewImpl.getStylesheet());
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
     * Getter of the box.
     * @return
     *     It returns the box itself.
     */
    protected Alert getBox() {
        return this.box;
    }

    /**
     * It shows the pause box, then manages the choice the user made. 
     */
    public abstract void show();
}
