package view;

import javafx.scene.image.ImageView;

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
     * It puts the pawn in the right box.
     * @param finalPosition
     *     The box where the pawn has to be put.
     */
    void jump(int finalPosition);

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

}