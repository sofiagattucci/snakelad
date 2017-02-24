package view.dialogboxes;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.LanguageStringMap;

/**
 * It handles the end of a player versus player game.
 */
public class MultiPlayerGameOver extends GameOver {

    private static final String PLAYER_KEY = "game.player";

    private final Label player = new Label(LanguageStringMap.get().getMap().get(PLAYER_KEY));

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     * @param winnerIndex
     *     The index of the winner of the game.
     */
    public MultiPlayerGameOver(final Stage parentStage, final int winnerIndex) {
        super(parentStage);
        this.getBox().setHeaderText(this.getMsg().getText() + this.player.getText() + winnerIndex + "\n" + this.getMsg2().getText());
    }
}
