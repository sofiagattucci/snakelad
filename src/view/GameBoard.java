package view;

import javafx.scene.image.Image;
import utilities.ImageManager;
import utilities.Pair;

/**
 * This class manages the game board.
 */
public class GameBoard {

    private static final String BOARD_PATH = "./res/GameBoards/GameBoard1/GameBoard1.png";
    private static final int N_BOXES_PER_ROW = 8;
    private static final double BOARD_H = Dimension.SCREEN_H * 0.9;
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;

    private final Image board = ImageManager.get().readFromFile(BOARD_PATH);
    private final Pair<Double, Double> boardPosition = new Pair<Double, Double>((Dimension.SCREEN_W - BOX_WIDTH - BOARD_H) / 2,
            (Dimension.SCREEN_H - BOARD_H) / 2);

    /**
     * Getter of the board.
     * @return
     *     The board image
     */
    public Image getBoard() {
        return this.board;
    }

    /**
     * Getter of the board position.
     * @return
     *     A pair containing the X and Y coordinates of the board position
     */
    public Pair<Double, Double> getPosition() {
        return this.boardPosition;
    }
    /**
     * Getter of the height of the board.
     * @return
     *     The height of the game board image
     */
    public double getBoardHeight() {
        return BOARD_H;
    }

    /**
     * Getter of the number of boxes per row in the game board.
     * @return
     *     The number of the boxes per row in the game board
     */
    public int getBoxPerRaw() {
        return N_BOXES_PER_ROW;
    }
}
