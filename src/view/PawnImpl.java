package view;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
/**
 * This class represents a pawn of the game.
 */
public class PawnImpl implements Pawn {

    private final ImageView pawnIm;
    private final Pair<Double, Double> pawnStartingPos = new Pair<>((Dimension.SCREEN_W - Toolbar.getBoxWidth() - Dimension.BOARD_H) / 2 
            + Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw() / 2 - Dimension.PAWN_HEIGHT / 1.80,
            Dimension.BOARD_H + (Dimension.SCREEN_H - Dimension.BOARD_H) / 2 
            - Dimension.PAWN_HEIGHT - (Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw() - Dimension.PAWN_HEIGHT) / 2);
    private Direction direction;
    private int positionInRow;
    private int row;

    /**
     * Constructor of this class.
     * @param pawnPath
     *     The path to the pawn to create
     */
    public PawnImpl(final String pawnPath) {
        this.pawnIm = ImageManager.get().getImageView(pawnPath);
        this.pawnIm.setFitHeight(Dimension.PAWN_HEIGHT);
        this.pawnIm.setPreserveRatio(true);
        this.setInitPosition();
        this.direction = Direction.RIGHT;
        this.positionInRow = 0;
        this.row = 0;
    }

    private void setInitPosition() {
        this.pawnIm.setX(this.pawnStartingPos.getFirst());
        this.pawnIm.setY(this.pawnStartingPos.getSecond());
        this.row = 0;
    }

    @Override
    public void movePawn(final int nMoves) {
        new Thread(new PawnAnimation(this, nMoves)).start();
    }

    @Override
    public void movePawnAndJump(final int nMoves, final int finalPos) {
        new Thread(new PawnAnimation(this, nMoves, finalPos)).start();
    }

    @Override
    public void reset() {
        this.positionInRow = 0;
        this.direction = Direction.RIGHT;
        this.setInitPosition();
    }

    @Override
    public ImageView getPawn() {
        return this.pawnIm;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getPositionInRow() {
        return positionInRow;
    }

    @Override
    public void setPositionInRow(final int newPos) {
        this.positionInRow = newPos;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void setRow(final int newRow) {
        this.row = newRow;
    }

    @Override
    public Pair<Double, Double> getIniPos() {
        return this.pawnStartingPos;
    }

    @Override
    public void changeDirection() {
        this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
    }
}
