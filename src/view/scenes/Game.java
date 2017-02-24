package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Side;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import utilities.Difficulty;
import utilities.ImageManager;
import view.Dimension;
import view.Toolbar;
import view.game_board.GameBoard;
import view.game_board.GameBoardImpl;
import view.game_board.GameBoardTypes;
import view.pawn.Pawn;
import view.pawn.PawnImpl;
import view.pawn.PawnTypes;

/**
 * This class creates and initializes a generic game scene.
 */
public abstract class Game extends BasicScene { 

    private static String boardPath = GameBoardTypes.get().getBoard(SetUpGame.getBoardType());
    private final Toolbar toolbar = new Toolbar();
    private final GameBoard board = new GameBoardImpl(boardPath);
    private final List<Pawn> pawnList = new ArrayList<>();
    private int currentTurn;

    /**
     * Constructor of his class.
     */
    protected Game() {

        this.getDefaultLayout().setRight(this.toolbar.getBox());
        this.setBackground();
        for (int i = 0; i < this.getTag(); i++) {
            final Pawn newPawn = new PawnImpl(PawnTypes.get().getPawn(i));
            this.getPawnList().add(newPawn);
            this.getDefaultLayout().getChildren().add(newPawn.getPawn());
        }
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
        this.pawnList.get(this.currentTurn % this.getTag()).movePawn(nBoxes);
        this.currentTurn++;
    }

    private void movePawn(final int nBoxes, final int finalPos) {
        this.pawnList.get(this.currentTurn % this.getTag()).movePawnAndJump(nBoxes, finalPos);
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
     * Updates the difficulty of the scenery.
     * @param newDiff
     *     The new difficulty
     */
    public void setScenary(final Difficulty newDiff) {
        boardPath = GameBoardTypes.get().getBoard(newDiff);
        board.newBoard(boardPath);
        this.setBackground();
    }

    /**
     * It updates the language of this scene.
     */
    public void updateLanguage() {
        this.toolbar.updateLanguage();
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
        return this.toolbar;
    }

    /**
     * Getter of the game board in the game screen.
     * @return
     *     The game board in the game screen
     */
    protected GameBoard getBoard() {
        return this.board;
    }

    /**
     * Getter of the pawn list, a list that contains the pawn usad in the game.
     * @return
     *     A list containing the pawns in the game screen
     */
    protected List<Pawn> getPawnList() {
        return this.pawnList;
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
