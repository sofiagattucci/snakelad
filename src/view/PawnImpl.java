package view;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
import view.scenes.Play;
/**
 * This class represents a pawn of the game.
 */
public class PawnImpl implements Pawn {

    private static final double PAWN_HEIGHT = Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw() * 0.66;

    private final ImageView pawnIm;
    private final Pair<Double, Double> pawnStartingPos = new Pair<>((Dimension.SCREEN_W - Toolbar.getBoxWidth() - Dimension.BOARD_H) / 2 
            + Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw() / 2 - PAWN_HEIGHT / 1.80,
            Dimension.BOARD_H + (Dimension.SCREEN_H - Dimension.BOARD_H) / 2 
            - PAWN_HEIGHT - (Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw() - PAWN_HEIGHT) / 2);
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
        this.pawnIm.setFitHeight(PAWN_HEIGHT);
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

        for (int i = 0; i < nMoves; i++) {
            if ((this.row  == (GameBoardImpl.getBoxesPerRaw() - 1)) && (this.positionInRow == (GameBoardImpl.getBoxesPerRaw() - 1))) {
                this.goBack(nMoves - i);
                break;
            }
            if (this.positionInRow == (GameBoardImpl.getBoxesPerRaw() - 1)) {
                this.resetCounter();
                this.row++;
                this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
                this.moveUp();
                continue;
            }
            this.positionInRow++;
            if (this.direction == Direction.RIGHT) {
                this.moveRight();
            } else {
                this.moveLeft();
            }
            if ((this.row  == (GameBoardImpl.getBoxesPerRaw() - 1)) && (this.positionInRow == (GameBoardImpl.getBoxesPerRaw() - 1)) 
                    && (i == nMoves - 1)) {
                Play.gameOver();
                break;
            }
        }

    }

    private void moveUp() {
        this.pawnIm.setY(pawnIm.getY() - Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw());
    }

    private void moveRight() {
        this.pawnIm.setX(this.pawnIm.getX() + Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw());
    }

    private void moveLeft() {
        this.pawnIm.setX(this.pawnIm.getX() - Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw());
    }

    private void moveDown() {
        this.pawnIm.setY(this.pawnIm.getY() + Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw());
    }

    private void resetCounter() {
        this.positionInRow = 0;
    }

    private void goBack(final int nMoves) {
        for (int i = 0; i < nMoves; i++) {
            if (this.positionInRow == 0) {
                this.moveDown();
                this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
                this.positionInRow = GameBoardImpl.getBoxesPerRaw();
                this.row--;
                continue;
            }
            this.positionInRow--;
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
        final int nY = finalPosition / GameBoardImpl.getBoxesPerRaw();
        final int change = nY % 2;
        if (change == 0) {
            this.positionInRow = finalPosition % GameBoardImpl.getBoxesPerRaw();
            this.direction = Direction.RIGHT;
            nX = this.positionInRow;
        } else {
            this.direction = Direction.LEFT;
            nX = GameBoardImpl.getBoxesPerRaw() - 1 - finalPosition % GameBoardImpl.getBoxesPerRaw();
            this.positionInRow = GameBoardImpl.getBoxesPerRaw() - 1 - nX;
        }
        this.pawnIm.setX(this.pawnStartingPos.getFirst() + (Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw()) * nX);
        this.pawnIm.setY(this.pawnStartingPos.getSecond() - (Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw()) * nY);
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
