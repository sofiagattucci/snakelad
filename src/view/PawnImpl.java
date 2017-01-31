package view;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
/**
 * This class represents a pawn of the game.
 */
public class PawnImpl implements Pawn {

    private static final int N_BOXES_PER_ROW = 8;
    private static final double BOARD_H = Dimension.SCREEN_H * 0.9;
    private static final double PAWN_HEIGHT = BOARD_H / N_BOXES_PER_ROW * 0.66;
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;

    private final ImageView pawnIm;
    private final Pair<Double, Double> pawnStartingPos = new Pair<>((Dimension.SCREEN_W - BOX_WIDTH - BOARD_H) / 2 
            + BOARD_H / N_BOXES_PER_ROW * 0.16, BOARD_H - BOARD_H / N_BOXES_PER_ROW * 0.39);
    private Direction direction;
    private int rowCounter;
    private int row; 

    /**
     * Constructor of this class.
     * @param pawnPath
     *     The path to the pawn to create
     */
    public PawnImpl(final String pawnPath) {
        this.pawnIm = ImageManager.get().getImageView(pawnPath);
        this.pawnIm.setFitHeight(PAWN_HEIGHT);
        this.pawnIm.setPreserveRatio(true);
        this.setInitPosition();
        this.direction = Direction.RIGHT;
        this.rowCounter = 0;
        this.row = 0;
    }

    private void setInitPosition() {
        this.pawnIm.setX(this.pawnStartingPos.getFirst());
        this.pawnIm.setY(this.pawnStartingPos.getSecond());
        this.row = 0;
    }

    @Override
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
        this.pawnIm.setY(pawnIm.getY() - BOARD_H / N_BOXES_PER_ROW);
    }

    private void moveRight() {
        this.pawnIm.setX(this.pawnIm.getX() + BOARD_H / N_BOXES_PER_ROW);
    }

    private void moveLeft() {
        this.pawnIm.setX(this.pawnIm.getX() - BOARD_H / N_BOXES_PER_ROW);
    }

    private void moveDown() {
        this.pawnIm.setY(this.pawnIm.getY() + BOARD_H / N_BOXES_PER_ROW);
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

    @Override
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
        this.pawnIm.setX(this.pawnStartingPos.getFirst() + (BOARD_H / N_BOXES_PER_ROW) * nX);
        this.pawnIm.setY(this.pawnStartingPos.getSecond() - (BOARD_H / N_BOXES_PER_ROW) * nY);
        this.row = nY;
    }

    @Override
    public void reset() {
        this.resetCounter();
        this.direction = Direction.RIGHT;
        this.setInitPosition();
    }

    @Override
    public ImageView getPawn() {
        return this.pawnIm;
    }
}
