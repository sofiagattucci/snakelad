package view.scenes;

import javafx.stage.Stage;
import view.Pawn;
import view.PawnImpl;
import view.PawnTypes;
import view.Toolbar;

/**
 * This class creates and initializes the game scene for a player versus player game.
 */
public final class MultiplayerPlay extends Play {

    //private static final int PLAYER0_INDEX = 0;
    //private static final int PLAYER1_INDEX = 1;
    //private static final int PLAYER2_INDEX = 2;
    //private static final int PLAYER3_INDEX = 3;
    private static final int N_PLAYERS = 4;

    private static MultiplayerPlay playScene = new MultiplayerPlay();

    private int currentTurn;

    private MultiplayerPlay() {
        super();
        this.getDefaultLayout().setRight(this.toolbar.getBox());

        for (int i = 0; i < N_PLAYERS; i++) {
            final Pawn newPawn = new PawnImpl(PawnTypes.get().getPawn(i));
            this.pawnList.add(newPawn);
            this.getDefaultLayout().getChildren().add(newPawn.getPawn());
        }
    }

    private void movePawn(final int nBoxes) {
        this.pawnList.get(currentTurn % N_PLAYERS).movePawn(nBoxes);
        this.currentTurn++;
    }

    private void movePawn(final int nBoxes, final int finalPos) {
        this.pawnList.get(currentTurn % N_PLAYERS).movePawnAndJump(nBoxes, finalPos);
        this.currentTurn++;
    }

    /**
     * It resets the displayed values at the beginning of each game. Indeed at the beginning 
     * there is no value shown in the GUI for the dice value. 
     */
    public void firstTurn() {
        this.toolbar.reset();
        for (final Pawn elem: pawnList) {
            elem.reset();
        }
        this.currentTurn = 0;
    }

    /**
     * It updates the game screen each turn.
     * @param newDiceValue
     *     The new value of the dice
     */
    public void updateInfo(final int newDiceValue) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue);
    }

    /**
     * It updates the game screen each turn.
     * @param newDiceValue
     *     The new value of the dice
     * @param finalPosition
     *     The new position after a jump due to a snake/ladder
     */
    public void updateInfo(final int newDiceValue, final int finalPosition) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue, finalPosition);
    }

    /**
     * It handles the end of the game.
     */
    public void gameOver() {
        //new GameOver(playStage, this.currentTurn).show();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The game scene
     */
    public static MultiplayerPlay getScene(final Stage stage) {
        playStage = stage;
        Toolbar.setStage(stage);
        return playScene;
    }

    /**
     * It sets the roll button in the tool bar enabled again after the pawn finished to move.
     */
    public void endTurn() {
        //this.toolbar.changeTurn(this.currentTurn);
        this.toolbar.endTurn();
    }

}
