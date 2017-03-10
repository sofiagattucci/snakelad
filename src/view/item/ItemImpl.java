package view.item;

import javafx.scene.image.ImageView;
import utilities.ImageManager;
import utilities.Pair;
import utilities.TypesOfItem;
import view.Dimension;
import view.scenes.game.Game;
import view.scenes.game.Toolbar;

/**
 * This class represents a coin in the GUI.
 */
public class ItemImpl implements Item {

    private static final double ITEM_HEIGHT_PARAM = 2.5;

    private final ImageView itemIV;
    private Pair<Double, Double> coinStartingPos;
    private final Game parentScene;
    private final int position;

    /**
     * Constructor of this class.
     * @param s
     *     The parent stage of the coin image view (The scene where the coin is located)
     * @param pos
     *     The number of the box where the coin must be put
     * @param type
     *     The type of item to create
     */
    public ItemImpl(final Game s, final int pos, final TypesOfItem type) {

        this.itemIV = ImageManager.get().getImageView(ItemTypes.get().getItem(type));
        this.position = pos;
        this.parentScene = s;
        this.itemIV.setPreserveRatio(true);
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
        this.itemIV.setX(this.coinStartingPos.getFirst() + (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow()) * nX);
        this.itemIV.setY(this.coinStartingPos.getSecond()  - (Dimension.BOARD_H / parentScene.getBoard().getBoxesPerRow()) * nY);
    }


    @Override
    public final void resize() {
        this.itemIV.setFitHeight(Dimension.getItemHeight());
        this.coinStartingPos = new Pair<>((Dimension.SCREEN_W - Toolbar.getBoxWidth() - Dimension.BOARD_H) / 2 
                + (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow()) / 2 - Dimension.getPawnHeight() / ITEM_HEIGHT_PARAM,
                Dimension.BOARD_H + (Dimension.SCREEN_H - Dimension.BOARD_H) / 2 
                - Dimension.getPawnHeight() - (Dimension.BOARD_H / this.parentScene.getBoard().getBoxesPerRow() 
                        - Dimension.getPawnHeight()) / 2);
        this.setPosition();
    }

    @Override
    public ImageView getItemImageView() {
        return this.itemIV;
    }
}
