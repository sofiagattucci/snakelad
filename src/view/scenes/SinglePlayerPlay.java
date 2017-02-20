package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Side;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import utilities.ImageManager;
import utilities.Turn;
import view.Dimension;
import view.GameBoard;
import view.GameBoardImpl;
import view.GameBoardTypes;
import view.Pawn;
import view.PawnImpl;
import view.PawnTypes;
import view.Toolbar;
import view.ViewImpl;
import view.dialog_boxes.SingleGameOver;

/**
 * This class creates and initializes the game scene for a player versus CPU game.
 */
public final class SinglePlayerPlay extends BasicScene {

    private static final String BOARD_PATH = GameBoardTypes.get().getBoard(1);
    private static final int PLAYER_INDEX = 0; //So CPU_INDEX will be 1...
    private static final int N_PLAYERS = 2;

    private static SinglePlayerPlay playScene = new SinglePlayerPlay();
    private static Stage playStage;

    private int currentTurn;
    private final List<Pawn> pawnList = new ArrayList<>();
    private final Toolbar toolbar = new Toolbar(N_PLAYERS);
    private final GameBoard board = new GameBoardImpl(BOARD_PATH);

    private SinglePlayerPlay() {

        this.getDefaultLayout().setRight(this.toolbar.getBox());
        this.setBackground();

        for (int i = 0; i < N_PLAYERS; i++) {
            final Pawn newPawn = new PawnImpl(PawnTypes.get().getPawn(i));
            this.pawnList.add(newPawn);
            this.getDefaultLayout().getChildren().add(newPawn.getPawn());
        }
    }

    private void setBackground() {

        final BackgroundPosition pos = new BackgroundPosition(Side.LEFT, this.board.getPosition().getFirst(), false,
                Side.TOP, this.board.getPosition().getSecond(), false);
        final BackgroundSize size = new BackgroundSize(Dimension.BOARD_H, Dimension.BOARD_H, false, false, false, false);

        final Background bg = new Background(new BackgroundImage(this.board.getBoard(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, pos, size));
        this.getDefaultLayout().setBackground(bg);
        this.setFill(this.getBackColor());
    }

    private void updateDiceValue(final int newValue) {
        if (!this.toolbar.getDiceImView().isVisible()) {
            this.toolbar.getDiceImView().setVisible(true);
        }
        this.toolbar.getDiceImView().setImage(ImageManager.get().readFromFile(this.toolbar.getDiceSides().get(newValue)));
    }

    private void movePawn(final int nBoxes) {
        this.pawnList.get(this.currentTurn % N_PLAYERS).movePawn(nBoxes);
        this.currentTurn++;
    }

    private void movePawn(final int nBoxes, final int finalPos) {
        this.pawnList.get(this.currentTurn % N_PLAYERS).movePawnAndJump(nBoxes, finalPos);
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
        this.currentTurn = PLAYER_INDEX;
    }

    /**
     * It updates the game screen each turn.
     * @param nextTurn
     *     the next turn
     * @param newDiceValue
     *     The new value of the dice
     */
    public void updateInfo(final Turn nextTurn, final int newDiceValue) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue);
    }

    /**
     * It updates the game screen each turn.
     * @param nextTurn
     *     the next turn
     * @param newDiceValue
     *     The new value of the dice
     * @param finalPosition
     *     The new position after a jump due to a snake/ladder
     */
    public void updateInfo(final Turn nextTurn, final int newDiceValue, final int finalPosition) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue, finalPosition);
    }

    /**
     * It handles the end of the game.
     */
    public void gameOver() {
         Turn winner;
        if (((this.currentTurn - 1) % N_PLAYERS) == PLAYER_INDEX) {
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
    public static SinglePlayerPlay getScene(final Stage stage) {
        playStage = stage;
        Toolbar.setStage(stage);
        return playScene;
    }

    /**
     * It sets the roll button in the tool bar enabled again after the pawn finished to move.
     */
    public void endTurn() {
        this.toolbar.changeTurn((this.currentTurn % N_PLAYERS));
        if ((this.currentTurn % N_PLAYERS) != PLAYER_INDEX) {
            ViewImpl.getObserver().rollDice();
        } else {
            this.toolbar.endTurn();
        }
    }

}
