package view;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.ImageLoader;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends BasicScene {

    private static final String PAUSE = "Pause";
    private static final String ROLL = "Roll";
    private static final String BOARD_PATH = "./res/GameBoards/GameBoard1.png";
    private static final double BOX_SPACING = 20;
    private static final double VERTICAL_INSETS = 60;
    private static final double HORIZONTAL_INSETS = Dimension.SCREEN_W * 0.05;
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;
    private static final double BUTTON_WIDTH = 0.18;
    private static final double BUTTON_HEIGHT = 0.07;
    private static final int FONT_SIZE = 30;
    private static final double BOARD_H = Dimension.SCREEN_H * 0.9;

    private static Play playScene = new Play();
    private static Stage playStage;

    private final Button back = new BasicButton(PAUSE);
    private final Button roll = new BasicButton(ROLL); 
    private final Label diceValue = new Label();
    private final Label turn = new Label(); 
    private final VBox box = new VBox(turn, diceValue, roll, back);

    private Play() {

        this.getDefaultLayout().setRight(this.box);

        this.box.setPrefWidth(BOX_WIDTH);
        this.box.setSpacing(BOX_SPACING);
        this.box.setPadding(new Insets(VERTICAL_INSETS, HORIZONTAL_INSETS, VERTICAL_INSETS, HORIZONTAL_INSETS));
        this.box.setStyle("-fx-background-color: #336699;");

        this.diceValue.setFont(new Font(FONT_SIZE));
        this.turn.setFont(new Font(FONT_SIZE));

        this.setBackground();

        this.roll.setPrefWidth(Dimension.SCREEN_W * BUTTON_WIDTH);
        this.roll.setPrefHeight(Dimension.SCREEN_H * BUTTON_HEIGHT);

        this.back.setPrefWidth(Dimension.SCREEN_W * BUTTON_WIDTH);
        this.back.setPrefHeight(Dimension.SCREEN_H * BUTTON_HEIGHT);

        this.back.setOnAction(e -> { 
            final PauseBox pause = new PauseBox(playStage);
            pause.show();
        });

        this.roll.setOnAction(e -> ViewImpl.getObserver().rollDice());
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
    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The game scene
     */
    public static Play getScene(final Stage stage) {
            playStage = stage;
            return playScene;
    }

    /**
     * This method is used to update the dice value shown on the screen.
     * @param newValue
     *     The new value of the dice
     */
    public void updateDiceValue(final int newValue) {
        this.diceValue.setText(String.valueOf(newValue));
    }

    /**
     * It changes the turn shown in the GUI.
     * @param turn
     *     The new turn
     */
    public void setTurn(final String turn) {
        this.turn.setText(turn);
    }

    /*Package visibility*/
    /**
     * It resets the displayed values at the beginning of each game. Indeed at the beginning 
     * there is no value shown in the GUI for the dice value. 
     */
    protected void firstTurn() {
        this.diceValue.setText("");
    }
}
