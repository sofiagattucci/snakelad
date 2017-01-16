package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class represents the pause window.
 */
public class PauseBox {

    private static final String RESUME = "Resume";
    private static final String GIVE_UP = "Give up";
    private static final String RESTART = "Restart";

    private final Alert pause = new Alert(AlertType.CONFIRMATION);
    private final ButtonType resume = new ButtonType(RESUME);
    private final ButtonType giveUp = new ButtonType(GIVE_UP);
    private final ButtonType restart = new ButtonType(RESTART);
    private final Stage parentSt;

    /**
     * Constructor of the class.
     * @param parentStage
     *     The parent of the pause box.
     */
    public PauseBox(final Stage parentStage) {

        pause.initOwner(parentStage);
        pause.initModality(Modality.APPLICATION_MODAL);
        pause.getButtonTypes().clear();
        pause.getButtonTypes().setAll(resume, restart, giveUp);
        this.parentSt = parentStage;
    }

    /**
     * It shows the pause box, then manages the choice the user made. 
     */
    public void show() {
        final String choose = this.pause.showAndWait().get().getText();
        if (choose.equals(GIVE_UP)) {
            ViewImpl.getObserver().giveUp();
            this.parentSt.setScene(Menu.getScene(parentSt));
        }
        if (choose.equals(RESTART)) {
            ViewImpl.getObserver().restart();
        }
    }
}
