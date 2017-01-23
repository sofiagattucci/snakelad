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
            + Play.getBoardHeight() / N_BOX_PER_RAW * 0.27, Play.getBoardHeight() - Play.getBoardHeight() / N_BOX_PER_RAW * 0.40);

    /**
     * Constructor of this class.
     * @param pawnPath
     *     The path to the pawn to create
     */
    public Pawn(final String pawnPath) {
        this.pawnIm = ImageLoader.get().getImageView(pawnPath);
        this.pawnIm.setFitHeight(PAWN_HEIGHT);
        this.pawnIm.setPreserveRatio(true);
        this.initPosition();
    }

    private void initPosition() {
        this.pawnIm.setTranslateY(pawnStartingPos.getSecond());
        this.pawnIm.setTranslateX(pawnStartingPos.getFirst());
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
