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
import utilities.TypesOfDice;
import view.Dimension;
import view.Toolbar;
import view.gameboard.GameBoard;
import view.gameboard.GameBoardImpl;
import view.gameboard.GameBoardTypes;
import view.pawn.AvailableColor;
import view.pawn.Pawn;
import view.pawn.PawnImpl;
import view.pawn.PawnTypes;

/**
 * This class creates and initializes a generic game scene.
 * @param <X>
 *     The type of tool bar to use for the game. It depends on the game mode selected
 */
public abstract class GameImpl<X extends Toolbar> extends BasicScene implements Game { 

    private static String boardPath = GameBoardTypes.get().getBoard(SetUpGame.getBoardType());
    private Toolbar toolbar;
    private final GameBoard board = new GameBoardImpl(boardPath);
    private final List<Pawn> pawnList = new ArrayList<>();
    private int currentTurn;

    /**
     * Constructor of his class.
     */
    protected GameImpl() {

        this.setBackground();
        for (int i = 0; i < this.getTag(); i++) {
            final Pawn newPawn = new PawnImpl(PawnTypes.get().getPawn(this.getColor(i)));
            this.getPawnList().add(newPawn);
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
        this.pawnList.get(this.currentTurn % this.getTag()).movePawn(nBoxes);
        this.currentTurn++;
    }

    private void movePawn(final int nBoxes, final int finalPos) {
        this.pawnList.get(this.currentTurn % this.getTag()).movePawnAndJump(nBoxes, finalPos);
        this.currentTurn++;
    }

    @Override
    public void firstTurn() {
        this.toolbar.reset();
        for (final Pawn elem: pawnList) {
            elem.reset();
        }
        this.currentTurn = 0;
    }

    @Override
    public void updateInfo(final int newDiceValue) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue);
    }

    @Override
    public void updateInfo(final int newDiceValue, final int finalPosition) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue, finalPosition);
    }

    @Override
    public void updateScene(final Difficulty newDiff, final TypesOfDice newDice) {
        boardPath = GameBoardTypes.get().getBoard(newDiff);
        board.newBoard(boardPath);
        this.setBackground();
        this.getToolbar().updateLabelsColor();
        this.toolbar.updateDice(newDice);
        for (int i = 0; i < this.getPawnList().size(); i++) {
            this.getPawnList().get(i).updateColor(PawnTypes.get().getPawn(this.getColor(i)));
        }
    }

    @Override
    public void updateLanguage() {
        this.toolbar.updateLanguage();
    }

    @Override
    public abstract int getTag();

    @Override
    public abstract void gameOver();

    /**
     * Getter of the color to use for a pawn. It depends on the game mode selected.
     * @param n
     *     The index of the pawn
     * @return
     *     The right color to use for the selected pawn
     */
    protected abstract AvailableColor getColor(int n);

    @Override
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

    /**
     * It sets up the right tool bar in the scene. 
     * @param t
     *     The tool bar to use in the scene
     */
    protected void setToolbar(final Toolbar t) {
        this.toolbar = t;
    }

    /**
     * It puts the tool bar in the game scene and adds it in the layout scene graph.
     * @param t
     *     The tool bar to use in the scene
     */
    protected void putToolbar(final X t) {
        this.toolbar = t;
        this.getDefaultLayout().setRight(this.toolbar.getBox());
        this.getToolbar().putLabels(getTag());
    }

}
