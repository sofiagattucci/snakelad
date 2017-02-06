package view;

import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import view.scenes.Menu;

/**
 * It handles the end of the game.
 */
public class GameOver extends BasicDialogBox {

    private static final String GAME_OVER = "GAME OVER";
    private static final String MESSAGE = "Game Over...\nWhat do you want to do?";
    private static final String RESTART = "Restart";
    private static final String MAIN_MENU = "Main Menu";

    private final ButtonType mainMenu = new ButtonType(MAIN_MENU);
    private final ButtonType restart = new ButtonType(RESTART);

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     */
    public GameOver(final Stage parentStage) {
        super(parentStage);
        this.getBox().setTitle(GAME_OVER);
        this.getBox().setHeaderText(MESSAGE);
        this.getBox().getButtonTypes().setAll(mainMenu, restart);
    }

    /**
     * It shows the game over box, then manages the choice the user made. 
     */
    @Override
    public void show() {
        if (this.getBox().showAndWait().get().getText().equals(MAIN_MENU)) {
            ViewImpl.getObserver().giveUp();
            this.getStage().setScene(Menu.getScene(this.getStage()));
        } else {
            ViewImpl.getObserver().restart();
        }
    }
}
