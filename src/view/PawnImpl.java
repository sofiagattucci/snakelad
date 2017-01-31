package view;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
/**
 * This class represents a pawn of the game.
 */
public class PawnImpl implements Pawn {

    private static final double PAWN_HEIGHT = GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw() * 0.66;

    private final ImageView pawnIm;
    private final Pair<Double, Double> pawnStartingPos = new Pair<>((Dimension.SCREEN_W - Toolbar.getBoxWidth() - GameBoard.getBoardHeight()) / 2 
            + GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw() * 0.16,
            GameBoard.getBoardHeight() - GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw() * 0.39);
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
            if ((this.row  == (GameBoard.getBoxesPerRaw() - 1)) && (this.positionInRow == (GameBoard.getBoxesPerRaw() - 1))) {
                this.goBack(nMoves - i);
                break;
            }
            if (this.positionInRow == (GameBoard.getBoxesPerRaw() - 1)) {
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
            if ((this.row  == (GameBoard.getBoxesPerRaw() - 1)) && (this.positionInRow == (GameBoard.getBoxesPerRaw() - 1)) 
                    && (i == nMoves - 1)) {
                Play.gameOver();
                break;
            }
        }

    }

    private void moveUp() {
        this.pawnIm.setY(pawnIm.getY() - GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw());
    }

    private void moveRight() {
        this.pawnIm.setX(this.pawnIm.getX() + GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw());
    }

    private void moveLeft() {
        this.pawnIm.setX(this.pawnIm.getX() - GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw());
    }

    private void moveDown() {
        this.pawnIm.setY(this.pawnIm.getY() + GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw());
    }

    private void resetCounter() {
        this.positionInRow = 0;
    }

    private void goBack(final int nMoves) {
        for (int i = 0; i < nMoves; i++) {
            if (this.positionInRow == 0) {
                this.moveDown();
                this.direction = this.direction == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT; 
                this.positionInRow = GameBoard.getBoxesPerRaw();
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
        final int nY = finalPosition / GameBoard.getBoxesPerRaw();
        final int change = nY % 2;
        if (change == 0) {
            this.positionInRow = finalPosition % GameBoard.getBoxesPerRaw();
            this.direction = Direction.RIGHT;
            nX = this.positionInRow;
        } else {
            this.direction = Direction.LEFT;
            nX = GameBoard.getBoxesPerRaw() - 1 - finalPosition % GameBoard.getBoxesPerRaw();
            this.positionInRow = GameBoard.getBoxesPerRaw() - 1 - nX;
        }
        this.pawnIm.setX(this.pawnStartingPos.getFirst() + (GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw()) * nX);
        this.pawnIm.setY(this.pawnStartingPos.getSecond() - (GameBoard.getBoardHeight() / GameBoard.getBoxesPerRaw()) * nY);
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
