package view.scenes;

import javafx.stage.Stage;
import view.Pawn;
import view.PawnImpl;
import view.PawnTypes;
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
        for (int i = 0; i < N_PLAYERS; i++) {
            final Pawn newPawn = new PawnImpl(PawnTypes.get().getPawn(i));
            this.getPawnList().add(newPawn);
            this.getDefaultLayout().getChildren().add(newPawn.getPawn());
        }
    }

    /**
     * It handles the end of the game.
     */
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

    /**
     * It manages the end of the turn, so it updates some informations.
     */
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