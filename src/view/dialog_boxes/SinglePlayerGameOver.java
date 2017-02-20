package view.dialog_boxes;

import javafx.stage.Stage;
import utilities.Turn;

/**
 * It handles the end of the game.
 */
public class SinglePlayerGameOver extends GameOver {

    private static final String MESSAGE = "The winner is: ";
    private static final String MESSAGE2 = "\nWhat do you want to do?";

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     * @param winner
     *     The winner of the game.
     */
    public SinglePlayerGameOver(final Stage parentStage, final Turn winner) {
        super(parentStage);
        this.getBox().setHeaderText(MESSAGE + winner + MESSAGE2);
    }
}
