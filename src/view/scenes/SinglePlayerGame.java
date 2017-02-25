package view.scenes;

import javafx.stage.Stage;
import utilities.Turn;
import view.SinglePlayerToolbar;
import view.ViewImpl;
import view.dialogboxes.SinglePlayerGameOver;
import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * This class creates and initializes the game scene for a player versus CPU game.
 */
public final class SinglePlayerGame extends GameImpl<SinglePlayerToolbar> {

    private static final int PLAYER_INDEX = 0;
    private static final int CPU_INDEX = 1;
    private static final int N_PLAYERS = 2;

    private static SinglePlayerGame playScene = new SinglePlayerGame();
    private static Stage playStage;

    private final SinglePlayerToolbar singleTool = new SinglePlayerToolbar();

    private SinglePlayerGame() {
        super();
        this.putToolbar(this.singleTool);
    }

    @Override
    public void gameOver() {
         Turn winner;
        if (((this.getCurrentTurn() - 1) % N_PLAYERS) == PLAYER_INDEX) {
            winner = Turn.PLAYER;
        } else {
            winner = Turn.CPU;
        }
        new SinglePlayerGameOver(playStage, winner).show();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The game scene
     */
    public static GameImpl<SinglePlayerToolbar> getScene(final Stage stage) {
        playStage = stage;
        SinglePlayerToolbar.setStage(stage);
        return playScene;
    }

    /**
     * It manages the end of the turn, so it updates some informations.
     */
    public void endTurn() {
        this.getToolbar().changeTurn((this.getCurrentTurn() % N_PLAYERS));
        if ((this.getCurrentTurn() % N_PLAYERS) != PLAYER_INDEX) {
            ViewImpl.getObserver().rollDice();
        } else {
            this.getToolbar().endTurn();
        }
    }

    @Override
    public int getTag() {
        return N_PLAYERS;
    }

    /**
     * Getter of the player index.
     * @return
     *     The player index
     */
    public static int getCPUIndex() {
        return CPU_INDEX;
    }

    @Override
    protected AvailableColor getColor(final int n) {
        return PawnsColor.get().getSingleColor(n);
    }
}
