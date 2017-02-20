package view.scenes;

import javafx.stage.Stage;
import utilities.Turn;
import view.Toolbar;
import view.ViewImpl;
import view.dialog_boxes.SingleGameOver;

/**
 * This class creates and initializes the game scene for a player versus CPU game.
 */
public final class SinglePlayerPlay extends Play {

    private static final int PLAYER_INDEX = 0; //So CPU_INDEX will be 1...
    private static final int N_PLAYERS = 2;

    private static SinglePlayerPlay playScene = new SinglePlayerPlay();
    private static Stage playStage;

    private SinglePlayerPlay() {
        super();
    }

    @Override
    public void gameOver() {
         Turn winner;
        if (((this.getCurrentTurn() - 1) % N_PLAYERS) == PLAYER_INDEX) {
            winner = Turn.PLAYER;
        } else {
            winner = Turn.CPU;
        }
        new SingleGameOver(playStage, winner).show();
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

}
