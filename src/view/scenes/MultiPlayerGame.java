package view.scenes;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.Difficulty;
import utilities.TypesOfDice;
import view.Toolbar;
import view.dialogboxes.MultiPlayerGameOver;
import view.pawn.PawnTypes;
import view.pawn.PawnsColor;

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

    /**
     * Updates the scenery and the dice used for the game.
     * @param newDiff
     *     The new difficulty
     * @param newDice
     *     The new dice to use
     */
    public void updateScene(final Difficulty newDiff, final TypesOfDice newDice) {
        super.updateScene(newDiff, newDice);
        for (int i = 0; i < this.getPawnList().size(); i++) {
            this.getPawnList().get(i).updateColor(PawnTypes.get().getPawn(PawnsColor.get().getMultiColor(i)));
        }
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
    protected Color getColor(final int n) {
        return PawnsColor.get().getMultiColor(n);
    }
}