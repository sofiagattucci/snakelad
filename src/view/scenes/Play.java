package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Side;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import utilities.ImageManager;
import view.Dimension;
import view.GameBoard;
import view.GameBoardImpl;
import view.GameBoardTypes;
import view.Pawn;
import view.Toolbar;

/**
 * This class creates and initializes a generic game scene.
 */
public abstract class Play extends BasicScene { 

    private static final String BOARD_PATH = GameBoardTypes.get().getBoard(1);

    protected static Stage playStage;

    protected final Toolbar toolbar = new Toolbar(3);
    protected final GameBoard board = new GameBoardImpl(BOARD_PATH);
    protected final List<Pawn> pawnList = new ArrayList<>();

    /**
     * Constructor of his class.
     */
    protected Play() {
        this.getDefaultLayout().setRight(this.toolbar.getBox());
        this.setBackground();
    }

    private void setBackground() {

        final BackgroundPosition pos = new BackgroundPosition(Side.LEFT, this.board.getPosition().getFirst(), false,
                Side.TOP, this.board.getPosition().getSecond(), false);
        final BackgroundSize size = new BackgroundSize(Dimension.BOARD_H, Dimension.BOARD_H, false, false, false, false);

        final Background bg = new Background(new BackgroundImage(this.board.getBoard(), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, pos, size));
        this.getDefaultLayout().setBackground(bg);
        this.setFill(this.getBackColor());
    }

    protected void updateDiceValue(final int newValue) {
        if (!this.toolbar.getDiceImView().isVisible()) {
            this.toolbar.getDiceImView().setVisible(true);
        }
        this.toolbar.getDiceImView().setImage(ImageManager.get().readFromFile(this.toolbar.getDiceSides().get(newValue)));
    }

}
