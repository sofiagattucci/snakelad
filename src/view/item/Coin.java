package view.item;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
import view.Dimension;
import view.scenes.game.Game;
import view.scenes.game.Toolbar;

/**
 * This class represents a coin in the GUI.
 */
public class Coin implements Item {

    private static final String COIN_PATH = "./res/icons/coin.gif";
    private static final double COIN_HEIGHT_PARAM = 2;

    private final ImageView coinIV = ImageManager.get().getImageView(COIN_PATH);
    private Pair<Double, Double> coinStartingPos;
    private final Game parentScene;
    private final int position;

    /**
     * Constructor of this class.
     * @param s
     *     The parent stage of the coin image view (The scene where the coin is located)
     * @param pos
     *     The number of the box where the coin must be put
     */
    public Coin(final Game s, final int pos) {

        this.position = pos;
        this.parentScene = s;
        this.coinIV.setPreserveRatio(true);
        this.resize();
    }

    private void setPosition() {
        final int nX;
        final int nY = position / parentScene.getBoard().getBoxesPerRow();
        final int change = nY % 2;
        if (change == 0) {
            nX = position % parentScene.getBoard().getBoxesPerRow();
        } else {
            nX = parentScene.getBoard().getBoxesPerRow() - 1 - position % parentScene.getBoard().getBoxesPerRow();
        }
        this.coinIV.setX(this.coinStartingPos.getFirst() + (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow()) * nX);
        this.coinIV.setY(this.coinStartingPos.getSecond()  - (Dimension.BOARD_H / parentScene.getBoard().getBoxesPerRow()) * nY);
    }


    @Override
    public final void resize() {
        this.coinIV.setFitHeight(Dimension.getPawnHeight());
        this.coinStartingPos = new Pair<>((Dimension.SCREEN_W - Toolbar.getBoxWidth() - Dimension.BOARD_H) / 2 
                + (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow()) / 2 - Dimension.getPawnHeight() / COIN_HEIGHT_PARAM,
                Dimension.BOARD_H + (Dimension.SCREEN_H - Dimension.BOARD_H) / 2 
                - Dimension.getPawnHeight() - (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow() 
                        - Dimension.getPawnHeight()) / 2);
        this.setPosition();
    }

    @Override
    public ImageView getItemImageView() {
        return this.coinIV;
    }
}
