package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Side;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import utilities.ImageManager;
import utilities.Turn;
import view.Dimension;
import view.GameBoard;
import view.GameBoardImpl;
import view.GameBoardTypes;
import view.Pawn;
import view.Toolbar;

/**
 * This class creates and initializes a generic game scene.
 */
public abstract class Play extends BasicScene { 

    private static final String BOARD_PATH = GameBoardTypes.get().getBoard(1);

    private final Toolbar toolbar = new Toolbar();
    private final GameBoard board = new GameBoardImpl(BOARD_PATH);
    private final List<Pawn> pawnList = new ArrayList<>();
    private int currentTurn;

    /**
     * Constructor of his class.
     */
    protected Play() {

        this.getDefaultLayout().setRight(this.toolbar.getBox());
        this.setBackground();
        this.getToolbar().putLabels(getTag());
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
        this.pawnList.get(this.currentTurn % getTag()).movePawn(nBoxes);
        this.currentTurn++;
    }

    private void movePawn(final int nBoxes, final int finalPos) {
        this.pawnList.get(this.currentTurn % getTag()).movePawnAndJump(nBoxes, finalPos);
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
     * It holds the number of players in the game. At this time we don' t know the number so an abstract method is needed.
     * @return
     *     The number of players in the game
     */
    public abstract int getTag();

    /**
     * It handles the end of the game.
     */
    public abstract void gameOver();

    /**
     * It manages the end of the turn, so it updates some informations.
     */
    public abstract void endTurn();

    /**
     * Getter of the tool bar in the game screen.
     * @return
     *     The tool bar in the game screen
     */
    protected Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Getter of the game board in the game screen.
     * @return
     *     The game board in the game screen
     */
    protected GameBoard getBoard() {
        return board;
    }

    /**
     * Getter of the pawn list, a list that contains the pawn usad in the game.
     * @return
     *     A list containing the pawns in the game screen
     */
    protected List<Pawn> getPawnList() {
        return pawnList;
    }

    /**
     * Getter of the current turn (integer index).
     * @return
     *     An index that represents the pawn that is used in this turn. 
     */
    protected int getCurrentTurn() {
        return this.currentTurn;
    }
}
