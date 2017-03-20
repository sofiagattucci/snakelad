package view.gameboard;

import javafx.scene.image.Image;
import utilities.ImageManager;
import utilities.Pair;
import view.Dimension;
import view.scenes.game.ToolbarImpl;

/**
 * This class manages the game board.
 */
public class GameBoardImpl  implements GameBoard {

    private static final int DEFAULT_SIZE = 8;

    private Image board;
    private final Pair<Double, Double> boardPosition = new Pair<Double, Double>(
            (Dimension.SCREEN_W - ToolbarImpl.getBoxWidth() - Dimension.BOARD_H) / 2,
            (Dimension.SCREEN_H - Dimension.BOARD_H) / 2);
    private int size = DEFAULT_SIZE;

    /**
     * Constructor of this class.
     * @param path
     *     The path to the game board image in the file system
     */
    public GameBoardImpl(final String path) {
        this.board = ImageManager.get().readFromFile(path);
    }

    @Override
    public Image getBoard() {
        return this.board;
    }

    @Override
    public Pair<Double, Double> getPosition() {
        return this.boardPosition;
    }

    @Override
    public int getBoxesPerRow() {
        return this.size;
    }

    @Override
    public void newBoard(final String path) {
        this.board = ImageManager.get().readFromFile(path);
    }

    @Override
    public void setSize(final int n) {
        this.size = n;
    }
}
