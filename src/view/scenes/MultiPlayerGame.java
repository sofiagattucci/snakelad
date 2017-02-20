package view.scenes;

import javafx.stage.Stage;
import view.Toolbar;
import view.dialog_boxes.MultiPlayerGameOver;

/**
 * This class creates and initializes the game scene for a player versus player game.
 */
public final class MultiPlayerGame extends Game {

    private static final int N_PLAYERS = 2;

    private static Game playScene = new MultiPlayerGame();
    private static Stage playStage;

    private MultiPlayerGame() {
        super();
    }

    @Override
    public void gameOver() {
        new MultiPlayerGameOver(playStage, this.getCurrentTurn()).show();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The game scene
     */
    public static Game getScene(final Stage stage) {
        playStage = stage;
        Toolbar.setStage(stage);
        return playScene;
    }

    @Override
    public void endTurn() {
        this.getToolbar().changeTurn((this.getCurrentTurn() % N_PLAYERS));
        this.getToolbar().endTurn();
    }

    @Override
    public int getTag() {
        return N_PLAYERS;
    }

}