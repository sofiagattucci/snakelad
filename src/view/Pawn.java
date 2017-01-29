package view;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
/**
 * This class represents a pawn of the game.
 */
public class Pawn {

    private static final int N_BOXES_PER_ROW = 8;
    private static final double PAWN_HEIGHT = Play.getBoardHeight() / N_BOXES_PER_ROW * 0.66;
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;

    private final ImageView pawnIm;
    private final Pair<Double, Double> pawnStartingPos = new Pair<>((Dimension.SCREEN_W - BOX_WIDTH - Play.getBoardHeight()) / 2 
            + Play.getBoardHeight() / N_BOXES_PER_ROW * 0.16, Play.getBoardHeight() - Play.getBoardHeight() / N_BOXES_PER_ROW * 0.39);
    private Direction direction;
    private int rowCounter;
    private int row; 

    /**
     * Constructor of this class.
     * @param pawnPath
     *     The path to the pawn to create
     */
    public Pawn(final String pawnPath) {
        this.pawnIm = ImageManager.get().getImageView(pawnPath);
        this.pawnIm.setFitHeight(PAWN_HEIGHT);
        this.pawnIm.setPreserveRatio(true);
        this.direction = Direction.RIGHT;
        this.rowCounter = 0;
        this.row = 0;
    }

    private void setInitPosition() {
        this.pawnIm.setX(this.pawnStartingPos.getFirst());
        this.pawnIm.setY(this.pawnStartingPos.getSecond());
        this.row = 0;
    }

    /**
     * It updates the position of the pawn in the board by moving it of the chosen number of boxes.
     * @param nMoves
     *     The number of boxes the pawn needs to go on
     */
    public void movePawn(final int nMoves) {

        for (int i = 0; i < nMoves; i++) {
            if ((this.row  == (N_BOXES_PER_ROW - 1)) && (this.rowCounter == (N_BOXES_PER_ROW - 1))) {
                this.goBack(nMoves - i);
                break;
            }
            if (this.rowCounter == (N_BOXES_PER_ROW - 1)) {
                this.resetCounter();
                this.row++;
                this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
                this.moveUp();
                continue;
            }
            this.rowCounter++;
            if (this.direction == Direction.RIGHT) {
                this.moveRight();
            } else {
                this.moveLeft();
            }
            if ((this.row  == (N_BOXES_PER_ROW - 1)) && (this.rowCounter == (N_BOXES_PER_ROW - 1)) && (i == nMoves - 1)) {
                Play.gameOver();
                break;
            }
        }

    }

    private void moveUp() {
        this.pawnIm.setY(pawnIm.getY() - Play.getBoardHeight() / N_BOXES_PER_ROW);
    }

    private void moveRight() {
        this.pawnIm.setX(this.pawnIm.getX() + Play.getBoardHeight() / N_BOXES_PER_ROW);
    }

    private void moveLeft() {
        this.pawnIm.setX(this.pawnIm.getX() - Play.getBoardHeight() / N_BOXES_PER_ROW);
    }

    private void moveDown() {
        this.pawnIm.setY(this.pawnIm.getY() + Play.getBoardHeight() / N_BOXES_PER_ROW);
    }

    private void resetCounter() {
        this.rowCounter = 0;
    }

    private void goBack(final int nMoves) {
        for (int i = 0; i < nMoves; i++) {
            if (this.rowCounter == 0) {
                this.moveDown();
                this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
                this.rowCounter = N_BOXES_PER_ROW;
                this.row--;
                continue;
            }
            this.rowCounter--;
            if (this.direction == Direction.LEFT) {
                this.moveRight();
            } else {
                this.moveLeft();
            }
        }
    }

    /**
     * It puts the pawn in the right box.
     * @param finalPosition
     *     The box where the pawn has to be put.
     */
    public void jump(final int finalPosition) {

        final int nX;
        final int nY = finalPosition / N_BOXES_PER_ROW;
        final int change = nY % 2;
        if (change == 0) {
            this.rowCounter = finalPosition % N_BOXES_PER_ROW;
            this.direction = Direction.RIGHT;
            nX = this.rowCounter;
        } else {
            this.direction = Direction.LEFT;
            nX = N_BOXES_PER_ROW - 1 - finalPosition % N_BOXES_PER_ROW;
            this.rowCounter = N_BOXES_PER_ROW - 1 - nX;
        }
        this.pawnIm.setX(this.pawnStartingPos.getFirst() + (Play.getBoardHeight() / N_BOXES_PER_ROW) * nX);
        this.pawnIm.setY(this.pawnStartingPos.getSecond() - (Play.getBoardHeight() / N_BOXES_PER_ROW) * nY);
        this.row = nY;
    }

    /**
     * It resets the raw counter of the pawn and puts it to 0 again, then the direction is set to the default one (RIGHT).
     * In the end it puts the pawn to its starting position.
     */
    public void reset() {
        this.resetCounter();
        this.direction = Direction.RIGHT;
        this.setInitPosition();
    }

    /**
     * Getter of the pawn.
     * @return
     *     The pawn itself.
     */
    public ImageView getPawn() {
        return this.pawnIm;
    }
}
