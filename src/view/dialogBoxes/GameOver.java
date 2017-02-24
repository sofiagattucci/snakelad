package view.dialogBoxes;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.LanguageStringMap;
import view.ViewImpl;
import view.scenes.Menu;

/**
 * It handles the end of a player versus player game.
 */
public class GameOver extends BasicDialogBox {

    private static final String TITLE_KEY = "gameOver.title";
    private static final String RESTART_KEY = "gameOver.restart";
    private static final String MAIN_MENU_KEY = "gameOver.mainMenu";
    private static final String MSG_KEY = "gameOver.msg";
    private static final String MSG2_KEY = "gameOver.msg2";

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final ButtonType mainMenu = new ButtonType(LanguageStringMap.get().getMap().get(MAIN_MENU_KEY));
    private final ButtonType restart = new ButtonType(LanguageStringMap.get().getMap().get(RESTART_KEY));
    private final Label msg = new Label(LanguageStringMap.get().getMap().get(MSG_KEY));
    private final Label msg2 = new Label(LanguageStringMap.get().getMap().get(MSG2_KEY));

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     */
    public GameOver(final Stage parentStage) {
        super(parentStage);
        this.getBox().setTitle(this.title.getText());
        this.getBox().getButtonTypes().addAll(this.mainMenu, this.restart);
    }

    /**
     * Getter of the msg label.
     * @return
     *      The label containing the msg
     */
    protected Label getMsg() {
        return this.msg;
    }

    /**
     * Getter of the msg2 label.
     * @return
     *      The label containing the msg2
     */
    protected Label getMsg2() {
        return this.msg2;
    }

    /**
     * It shows the game over box, then manages the choice the user made. 
     */
    @Override
    public void show() {
        if (this.getBox().showAndWait().get().getText().equals(this.mainMenu.getText())) {
            ViewImpl.getObserver().giveUp();
            this.getStage().setScene(Menu.getScene(this.getStage()));
        } else {
            ViewImpl.getObserver().restart();
        }
    }
}
