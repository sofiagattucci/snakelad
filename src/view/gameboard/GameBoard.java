package view.gameboard;

import javafx.scene.image.Image;
import utilities.Pair;
/**
 * Interface for a generic type of game board in the game.
 */
public interface GameBoard {

    /**
     * Getter of the board.
     * @return
     *     The board image
     */
    Image getBoard();

    /**
     * Getter of the board position.
     * @return
     *     A pair containing the X and Y coordinates of the board position
     */
    Pair<Double, Double> getPosition();

    /**
     * It assigns a new image to the board. The image is specified through its path.
     * @param path
     *     The path to the new board image
     */
    void newBoard(String path);

    /**
     * Setter of the size of the board.
     * @param n
     *     The number of boxes per side of the board
     */
    void setSize(int n);

    /**
     * Getter of the size of the board.
     * @return
     *     The number of boxes per side of the board
     */
     int getBoxesPerRow();

}