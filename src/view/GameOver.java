package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * It handles the end of the game.
 */
public class GameOver extends BasicDialogBox {

    private static final String GAME_OVER = "GAME OVER";
    private static final String MESSAGE = "Game Over...\nWhat do you want to do?";
    private static final String RESTART = "Restart";
    private static final String MAIN_MENU = "Main Menu";

    private final Alert end = new Alert(AlertType.INFORMATION);
    private final ButtonType mainMenu = new ButtonType(MAIN_MENU);
    private final ButtonType restart = new ButtonType(RESTART);

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     */
    public GameOver(final Stage parentStage) {
        super(parentStage);
        this.end.setTitle(GAME_OVER);
        this.end.setHeaderText(MESSAGE);
        this.end.getButtonTypes().setAll(mainMenu, restart);
    }

    /**
     * It shows the game over box, then manages the choice the user made. 
     */
    @Override
    public void show() {
        final String choose = this.end.showAndWait().get().getText();
        if (choose.equals(MAIN_MENU)) {
            ViewImpl.getObserver().giveUp();
            this.getStage().setScene(Menu.getScene(this.getStage()));
        } else {
            ViewImpl.getObserver().restart();
        }
    }
}
