package view.scenes;

import javafx.stage.Stage;
import view.Toolbar;
import view.dialog_boxes.MultiPlayerGameOver;

/**
 * This class creates and initializes the game scene for a player versus player game.
 */
public final class MultiPlayerGame extends Game {

    private final int numPlayers;
    private Stage playStage;

    /**
     * Constructor of this class.
     * @param n
     *     The number of players in the game
     */
    public MultiPlayerGame(final int n) {
        super();
        this.numPlayers = n;
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
    public Game getScene(final Stage stage) {
        this.playStage = stage;
        Toolbar.setStage(stage);
        return MultiPlayerScenes.get().getScene(this.numPlayers);
    }

    @Override
    public void endTurn() {
        this.getToolbar().changeTurn((this.getCurrentTurn() % numPlayers));
        this.getToolbar().endTurn();
    }

    @Override
    public int getTag() {
        return SetUpGame.getPlayers();
    }
}