package view.pawn;

import javafx.scene.image.ImageView;
import utilities.Pair;
import view.Direction;

/**
 * Interface for a generic pawn.
 */
public interface Pawn {

    /**
     * It updates the position of the pawn in the board by moving it of the chosen number of boxes.
     * @param nMoves
     *     The number of boxes the pawn needs to go on
     */
    void movePawn(int nMoves);

    /**
     * It updates the position of the pawn in the board by moving it of the chosen number of boxes.
     * @param nMoves
     *     The number of boxes the pawn needs to go on
     * @param finalPos
     *     The position of the pawn after the jump
     */
    void movePawnAndJump(int nMoves, int finalPos);

    /**
     * It resets the raw counter of the pawn and puts it to 0 again, then the direction is set to the default one (RIGHT).
     * In the end it puts the pawn to its starting position.
     */
    void reset();

    /**
     * Getter of the pawn.
     * @return
     *     The pawn itself.
     */
    ImageView getPawn();

    /**
     * Getter of the movements direction of the pawn.
     * @return
     *     An element of the enumeration Direction .
     */
    Direction getDirection();

    /**
     * Getter of the position of the pawn in the current row.
     * @return
     *     The pawn' s position in the row.
     */
    int getPositionInRow();

    /**
     * Getter of the row of the pawn.
     * @return
     *     The row where the pawn is.
     */
    int getRow();

    /**
     * Setter of the pawn.
     * @param newPos
     *     The new new position in the row.
     */
    void setPositionInRow(int newPos);

    /**
     * Setter of the pawn' s row.
     * @param newRow
     *     The new pawn' s row.
     */
    void setRow(int newRow);

    /**
     * getter of the starting point of the pawn.
     * @return
     *     The X:Y coordinates where the pawn has its starting point
     */
    Pair<Double, Double> getIniPos();

    /**
     * It changes the direction of the pawn' s movement.
     */
    void changeDirection();

    /**
     * It updates the color of the pawn by using the new one.
     * @param path
     *     The path to the new image to use
     */
    void updateColor(String path);

}