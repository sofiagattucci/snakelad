package view.scenes;

import javafx.stage.Stage;
import view.Toolbar;
import view.dialog_boxes.MultiGameOver;

/**
 * This class creates and initializes the game scene for a player versus player game.
 */
public final class MultiplayerPlay extends Play {

    private static final int N_PLAYERS = 2;

    private static Play playScene = new MultiplayerPlay();
    private static Stage playStage;

    private MultiplayerPlay() {
        super();
    }

    @Override
    public void gameOver() {
        new MultiGameOver(playStage, this.getCurrentTurn()).show();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The game scene
     */
    public static Play getScene(final Stage stage) {
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