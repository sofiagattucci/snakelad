package view;

import javafx.scene.image.ImageView;
import utilities.ConsoleLog;
import utilities.ImageLoader;
import utilities.Pair;
/**
 * This class represents a pawn of the game.
 */
public class Pawn {

    private static final int N_BOX_PER_RAW = 8;
    private static final double PAWN_HEIGHT = Play.getBoardHeight() / N_BOX_PER_RAW * 0.66;
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;

    private final ImageView pawnIm;
    private final Pair<Double, Double> pawnStartingPos = new Pair<>((Dimension.SCREEN_W - BOX_WIDTH - Play.getBoardHeight()) / 2 
            + Play.getBoardHeight() / N_BOX_PER_RAW * 0.16, Play.getBoardHeight() - Play.getBoardHeight() / N_BOX_PER_RAW * 0.39);
    private Direction direction;
    private int rowCounter;

    /**
     * Constructor of this class.
     * @param pawnPath
     *     The path to the pawn to create
     */
    public Pawn(final String pawnPath) {
        this.pawnIm = ImageLoader.get().getImageView(pawnPath);
        this.pawnIm.setFitHeight(PAWN_HEIGHT);
        this.pawnIm.setPreserveRatio(true);
        this.direction = Direction.RIGHT;
        this.rowCounter = 0;
    }

    private void setInitPosition() {
        this.pawnIm.setX(this.pawnStartingPos.getFirst());
        this.pawnIm.setY(this.pawnStartingPos.getSecond());
    }

    /**
     * It updates the position of the pawn in the board by moving it of the chosen number of boxes.
     * @param nMoves
     *     The number of boxes the pawn needs to go on
     * @param checkJump
     *     If there' s a snake/ladder in the arrival box so I need to change the pawn position
     * @param finalPosition
     *     The final position if the pawn stops on a snake/ladder
     */
    public void movePawn(final int nMoves, final boolean checkJump, final int finalPosition) {

        for (int i = 0; i < nMoves; i++) {
            if (this.rowCounter == (N_BOX_PER_RAW - 1)) {
                this.resetCounter();
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
        }
        if (checkJump) {
            this.jump(finalPosition);
        }
    }

    private void moveUp() {
        this.pawnIm.setY(pawnIm.getY() - Play.getBoardHeight() / N_BOX_PER_RAW);
    }

    private void moveRight() {
        this.pawnIm.setX(this.pawnIm.getX() + Play.getBoardHeight() / N_BOX_PER_RAW);
    }

    private void moveLeft() {
        this.pawnIm.setX(this.pawnIm.getX() - Play.getBoardHeight() / N_BOX_PER_RAW);
    }

    private void resetCounter() {
        this.rowCounter = 0;
    }

    private void jump(final int finalPosition) {
        ConsoleLog.get().print("JUMP" + finalPosition);
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
