package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilities.ImageLoader;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends BasicScene {

    private static final String BOARD_PATH = "./res/GameBoards/GameBoard1/GameBoard1.png";
    private static final String PAWN1_PATH = "./res/Pawns/RedPawn.png";
    private static final String PAWN2_PATH = "./res/Pawns/LightBluePawn.png";
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;
    private static final double BOARD_H = Dimension.SCREEN_H * 0.9;

    private static Play playScene = new Play();
    private static Stage playStage;

    private final List<Pawn> pawnList = new ArrayList<>(Arrays.asList(new Pawn(PAWN1_PATH), new Pawn(PAWN2_PATH)));
    private final Toolbar toolbar = new Toolbar();
    private boolean changePawn = true;

    private Play() {

        this.getDefaultLayout().setRight(this.toolbar.getBox());

        this.setBackground();

        for (final Pawn elem: pawnList) {
            this.getDefaultLayout().getChildren().add(elem.getPawn());
        }
    }

    private void setBackground() {

        final Image board = ImageLoader.get().getImage(BOARD_PATH);

        final double transposeY = (Dimension.SCREEN_H - BOARD_H) / 2;
        final double transposeX = (Dimension.SCREEN_W - BOX_WIDTH - BOARD_H) / 2;

        final BackgroundPosition pos = new BackgroundPosition(Side.LEFT, transposeX, false, Side.TOP, transposeY, false);
        final BackgroundSize size = new BackgroundSize(BOARD_H, BOARD_H, false, false, false, false);

        final Background bg = new Background(new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, pos, size));
        this.getDefaultLayout().setBackground(bg);
        this.setFill(Color.LIGHTBLUE);
    }

    private void updateDiceValue(final int newValue) {
        if (!this.toolbar.getDice().isVisible()) {
            this.toolbar.getDice().setVisible(true);
        }
        this.toolbar.getDice().setImage(ImageLoader.get().getImage(this.toolbar.getDiceSides().get(newValue)));
    }

    private void movePawn(final int nBoxes) {
        if (this.changePawn) {
            this.pawnList.get(0).movePawn(nBoxes);
        } else {
            this.pawnList.get(1).movePawn(nBoxes);
        }
        this.changePawn = this.changePawn ? false : true;
    }

    /**
     * It changes the turn shown in the game screen.
     * @param turn
     *     The new turn to set
     */
    public void setTurn(final String turn) {
        this.toolbar.changeTurn(turn);
    }

    /*Package visibility*/
    /**
     * It resets the displayed values at the beginning of each game. Indeed at the beginning 
     * there is no value shown in the GUI for the dice value. 
     */
    protected void firstTurn() {
        this.toolbar.reset();
        for (final Pawn elem: pawnList) {
            elem.reset();
        }
        this.changePawn = true;
    }

    /**
     * It updates the game screen each turn.
     * @param turn
     *     the new turn
     * @param newDiceValue
     *     The new value of the dice
     */
    public void updateInfo(final String turn, final int newDiceValue) {
        this.updateDiceValue(newDiceValue);
        this.movePawn(newDiceValue);
    }

    /**
     * It updates the game screen each turn.
     * @param turn
     *     the new turn
     * @param newDiceValue
     *     The new value of the dice
     * @param finalPosition
     *     The new position after a jump due to a snake/ladder
     */
    public void updateInfo(final String turn, final int newDiceValue, final int finalPosition) {

        this.updateInfo(turn, newDiceValue);
        this.changePawn = this.changePawn ? false : true;
        if (this.changePawn) {
            this.pawnList.get(0).jump(finalPosition);
        } else {
            this.pawnList.get(1).jump(finalPosition);
        }
        this.changePawn = this.changePawn ? false : true;
    }

    /**
     * It handles the end of the game.
     */
    public static void gameOver() {
        new GameOver(playStage).show();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The game scene
     */
    public static Play getScene(final Stage stage) {
        playStage = stage;
        Toolbar.setStage(stage);
        return playScene;
    }
    /**
     * Getter of the board height BOARD_H.
     * @return
     *     BOARD_H, the height of the board.
     */
    protected static double getBoardHeight() {
        return BOARD_H;
    }
}
