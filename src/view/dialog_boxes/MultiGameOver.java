package view.dialog_boxes;

import javafx.stage.Stage;

/**
 * It handles the end of a player versus player game.
 */
public class MultiGameOver extends GameOver {

    private static final String MESSAGE = "The winner is: Player ";
    private static final String MESSAGE2 = "\nWhat do you want to do?";

    /**
     * Constructor of this class.
     * @param parentStage
     *     The parent stage of the game over box.
     * @param winnerIndex
     *     The index of the winner of the game.
     */
    public MultiGameOver(final Stage parentStage, final int winnerIndex) {
        super(parentStage);
        this.getBox().setHeaderText(MESSAGE + winnerIndex + MESSAGE2);
    }
}
