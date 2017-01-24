package view;

import javafx.scene.image.ImageView;
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
    private int conta;

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
        this.conta = 0;
    }

    /**
     * It puts the pawn to its starting position.
     */
    public final void setInitPosition() {
        this.pawnIm.setX(this.pawnStartingPos.getFirst());
        this.pawnIm.setY(this.pawnStartingPos.getSecond());
    }

    /**
     * It updates the position of the pawn in the board by moving it of the chosen number of boxes.
     * @param nMoves
     *     The number of boxes the pawn needs to go on
     */
    public void movePawn(final int nMoves) {

        for (int i = 0; i < nMoves; i++) {
            if (this.conta == (N_BOX_PER_RAW - 1)) {
                this.conta = 0;
                this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
                this.pawnIm.setY(pawnIm.getY() - Play.getBoardHeight() / N_BOX_PER_RAW);
                continue;
            }
            this.conta++;
            if (this.direction == Direction.RIGHT) {
                this.pawnIm.setX(this.pawnIm.getX() + Play.getBoardHeight() / N_BOX_PER_RAW);
            } else {
                this.pawnIm.setX(this.pawnIm.getX() - Play.getBoardHeight() / N_BOX_PER_RAW);
            }
        }
    }

    /**
     * It resets the conta of the pawn and puts it to 0 again, then the direction is set to the default one (RIGHT).
     */
    public void reset() {
        this.conta = 0;
        this.direction = Direction.RIGHT;
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
