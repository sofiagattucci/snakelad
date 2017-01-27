package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * It handles the end of the game.
 */
public class GameOver {

    private static final String GAME_OVER = "GAME OVER";
    private static final String MESSAGE = "Game Over...\nWhat do you want to do?";
    private static final String RESTART = "Restart";
    private static final String MAIN_MENU = "Main Menu";

    private final Stage parentSt;
    private final ButtonType mainMenu = new ButtonType(MAIN_MENU);
    private final ButtonType restart = new ButtonType(RESTART);
    private final Alert end = new Alert(AlertType.INFORMATION);

    /**
     * Default constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box
     */
    public GameOver(final Stage parentStage) {
        this.end.initOwner(parentStage);
        this.end.initModality(Modality.APPLICATION_MODAL);
        this.end.setTitle(GAME_OVER);
        this.end.setHeaderText(MESSAGE);
        this.end.getButtonTypes().clear();
        this.end.getButtonTypes().setAll(mainMenu, restart);
        this.parentSt = parentStage;
    }

    /**
     * It shows the pause box, then manages the choice the user made. 
     */
    public void show() {
        final String choose = this.end.showAndWait().get().getText();
        if (choose.equals(MAIN_MENU)) {
            ViewImpl.getObserver().giveUp();
            this.parentSt.setScene(Menu.getScene(parentSt));
        } else {
            ViewImpl.getObserver().restart();
        }
    }
}
