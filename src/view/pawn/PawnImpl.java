package view.pawn;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
import view.Dimension;
import view.scenes.game.Game;
import view.scenes.game.Toolbar;
/**
 * This class represents a pawn of the game.
 */
public class PawnImpl implements Pawn {

    private static final double PAWN_HEIGHT_PARAM = 2;

    private final ImageView pawnIm;
    private Pair<Double, Double> pawnStartingPos;
    private Direction direction;
    private int positionInRow;
    private int row;
    private final Game parentScene;
    private final Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Constructor of this class.
     * @param pawnPath
     *     The path to the pawn to create
     * @param scene
     *     The parent scene of this pawn (The scene where this pawn is shown)
     */
    public PawnImpl(final Game scene, final String pawnPath) {
        this.parentScene = scene;
        this.pawnIm = ImageManager.get().getImageView(pawnPath);
        this.pawnIm.setFitHeight(Dimension.getPawnHeight());
        this.pawnIm.setPreserveRatio(true);
        this.resizePawn();
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
    public void updateColor(final String path) {
        this.pawnIm.setImage(ImageManager.get().readFromFile(path));
    }

    @Override
    public final void resizePawn() {
        this.pawnIm.setFitHeight(Dimension.getPawnHeight());
        pawnStartingPos = new Pair<>((Dimension.SCREEN_W - Toolbar.getBoxWidth() - Dimension.BOARD_H) / 2 
                + (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow()) / 2 - Dimension.getPawnHeight() / PAWN_HEIGHT_PARAM,
                Dimension.BOARD_H + (Dimension.SCREEN_H - Dimension.BOARD_H) / 2 
                - Dimension.getPawnHeight() - (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow() 
                        - Dimension.getPawnHeight()) / 2);
        this.setInitPosition();
    }

    @Override
    public void movePawn(final int nMoves) {
        this.executor.execute(new PawnAnimation(this, nMoves));
    }

    @Override
    public void movePawnAndJump(final int nMoves, final int finalPos) {
        this.executor.execute(new PawnAnimation(this, nMoves, finalPos));
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

    @Override
    public Game getParentScene() {
        return this.parentScene;
    }
}
