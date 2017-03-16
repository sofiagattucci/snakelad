package view.scenes.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import javafx.geometry.Side;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import utilities.ImageManager;
import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfDice;
import utilities.enumeration.TypesOfItem;
import view.Dimension;
import view.ViewImpl;
import view.gameboard.GameBoard;
import view.gameboard.GameBoardImpl;
import view.gameboard.GameBoardTypes;
import view.item.ItemImpl;
import view.item.Item;
import view.pawn.AvailableColor;
import view.pawn.Pawn;
import view.pawn.PawnImpl;
import view.pawn.PawnTypes;
import view.scenes.BasicScene;
import view.scenes.setup.SetUpGame;

/**
 * This class creates and initializes a generic game scene.
 */
public abstract class GameImpl extends BasicScene implements Game { 

    private static String boardPath = GameBoardTypes.get().getBoard(SetUpGame.getBoardType());
    private Toolbar toolbar;
    private final GameBoard board = new GameBoardImpl(boardPath);
    private final List<Pawn> pawnList = new ArrayList<>();
    private final Map<Integer, Item> itemMap = new HashMap<>();
    private int currentTurn;

    /**
     * Constructor of his class.
     */
    protected GameImpl() {

        this.setBackground(); 
        IntStream.range(0, this.getNumPlayers())
                 .forEach(i -> {
                      final Pawn newPawn = new PawnImpl(this, PawnTypes.get().getPawn(this.getColor(i)));
                      this.pawnList.add(newPawn);
                      this.getDefaultLayout().getChildren().add(newPawn.getPawn());
        });
        this.getStylesheets().add(ViewImpl.getStylesheet());
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
        this.pawnList.get(this.currentTurn % this.getNumPlayers()).movePawn(nBoxes);
        this.currentTurn++;
    }

    private void movePawn(final int nBoxes, final int finalPos) {
        this.pawnList.get(this.currentTurn % this.getNumPlayers()).movePawnAndJump(nBoxes, finalPos);
        this.currentTurn++;
    }

    @Override
    public void firstTurn() {
        this.toolbar.reset();
        for (final Item i: this.itemMap.values()) {
            this.getDefaultLayout().getChildren().remove(i.getItemImageView());
        }
        this.itemMap.clear();
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
        this.board.newBoard(boardPath);
        this.setBackground();
        this.getToolbar().updateLabelsColor();
        this.toolbar.updateDice(newDice);
        IntStream.iterate(0, i -> i + 1)
                 .limit(this.pawnList.size())
                 .forEach(i -> this.pawnList.get(i).updateColor(PawnTypes.get().getPawn(this.getColor(i))));
    }

    @Override
    public void resizePawns() {
        IntStream.iterate(0, i -> i + 1)
                 .limit(this.pawnList.size())
                 .forEach(i -> this.pawnList.get(i).resizePawn());
    }

    @Override
    public void updateLanguage() {
        this.toolbar.updateLanguage();
    }

    @Override
    public GameBoard getBoard() {
        return this.board;
    }

    @Override
    public void putItem(final int pos, final TypesOfItem type) {
        final Item newItem = new ItemImpl(this, pos, type);
        this.itemMap.put(pos, newItem);
        this.getDefaultLayout().getChildren().add(newItem.getItemImageView());
    }

    /**
     * It holds the number of players in the game. At this time we don' t know the number so an abstract method is needed.
     * @return
     *     The number of players in the game
     */
    protected abstract int getNumPlayers();

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
     * Getter of the current turn (integer index).
     * @return
     *     An index that represents the pawn that is used in this turn. 
     */
    protected int getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * It puts the tool bar in the game scene and adds it in the layout scene graph.
     * @param t
     *     The tool bar to use in the scene
     */
    protected void putToolbar(final Toolbar t) {
        this.toolbar = t;
        this.getDefaultLayout().setRight(this.toolbar.getBox());
        this.getToolbar().putLabels(this, getNumPlayers());
    }

    @Override
    public Map<Integer, Item> getItemMap() {
        return Collections.unmodifiableMap(this.itemMap);
    }

    @Override
    public void removeItem() {

        final Set<Integer> keySet = new HashSet<>(ViewImpl.getPlayScene().getItemMap().keySet());
        for (final int key: keySet) {
            if (this.pawnList.get((this.currentTurn - 1) % this.getNumPlayers()).getPawn().intersects(
                    ViewImpl.getPlayScene().getItemMap().get(key).getItemImageView().getBoundsInLocal())) {
                ViewImpl.getObserver().collisionHappened(key);
                this.getDefaultLayout().getChildren().remove(this.itemMap.get(key).getItemImageView());
                this.itemMap.remove(key);
            }
        }
    }
}
