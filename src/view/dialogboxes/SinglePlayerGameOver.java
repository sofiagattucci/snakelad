package view.dialogboxes;

import javafx.stage.Stage;
import utilities.enumeration.Turn;
import view.LanguageStringMap;

/**
 * It handles the end of the game.
 */
public class SinglePlayerGameOver extends GameOver {

    private static final String NEWLINE = "\n";

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     * @param winner
     *     The winner of the game.
     */
    public SinglePlayerGameOver(final Stage parentStage, final Turn winner) {
        super(parentStage);
        String win;
        if (winner == Turn.PLAYER) {
            win = LanguageStringMap.get().getMap().get("game.player");
        } else {
            win = winner.name();
        }
        this.getBox().setHeaderText(this.getMsg().getText() + win + NEWLINE + this.getMsg2().getText());
    }
}
