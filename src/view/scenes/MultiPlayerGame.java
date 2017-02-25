package view.scenes;

import javafx.stage.Stage;
import view.MultiPlayerToolbar;
import view.Toolbar;
import view.dialogboxes.MultiPlayerGameOver;
import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * This class creates and initializes the game scene for a player versus player game.
 */
public final class MultiPlayerGame extends GameImpl<MultiPlayerToolbar> {

    private final int numPlayers;
    private Stage playStage;
    private final MultiPlayerToolbar multiTool = new MultiPlayerToolbar();

    /**
     * Constructor of this class.
     * @param n
     *     The number of players in the game
     */
    public MultiPlayerGame(final int n) {
        super();
        this.numPlayers = n;
        this.putToolbar(this.multiTool);
    }

    @Override
    public void gameOver() {
        int winner;
        if (this.getCurrentTurn() % this.numPlayers == 0) {
            winner = numPlayers;
        } else {
            winner = this.getCurrentTurn() % numPlayers;
        }
        new MultiPlayerGameOver(playStage, winner).show();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     */
    public void setStage(final Stage stage) {
        this.playStage = stage;
        Toolbar.setStage(stage);
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

    @Override
    protected AvailableColor getColor(final int n) {
        return PawnsColor.get().getMultiColor(n);
    }
}