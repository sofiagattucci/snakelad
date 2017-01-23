package view;

import java.awt.Toolkit;
import java.io.FileInputStream;

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
import utilities.ConsoleLog;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends BasicScene {

    private static final String PAUSE = "Pause";
    private static final String ROLL = "Roll";
    private static final double BOX_SPACING = 20;
    private static final double VERTICAL_INSETS = 60;
    private static final double HORIZONTAL_INSETS = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.05;
    private static final double BOX_W_PERC = 0.20;
    private static final double BUTTON_WIDTH = 150;
    private static final double BUTTON_HEIGHT = 40;
    private static final int FONT_SIZE = 30;
    private static final Double BOARD_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.9;

    private static Play playScene = new Play();
    private static Stage playStage;

    private final Button back = new BasicButton(PAUSE);
    private final Button roll = new BasicButton(ROLL); 
    private final Label diceValue = new Label();
    private final Label turn = new Label(); 
    private final VBox box = new VBox(turn, diceValue, roll, back);

    private Play() {

        this.getDefaultLayout().setRight(this.box);

        this.box.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Dimension.SCREEN_W_PERC * BOX_W_PERC);
        this.box.setSpacing(BOX_SPACING);
        this.box.setPadding(new Insets(VERTICAL_INSETS, HORIZONTAL_INSETS, VERTICAL_INSETS, HORIZONTAL_INSETS));

        this.diceValue.setFont(new Font(FONT_SIZE));
        this.turn.setFont(new Font(FONT_SIZE));

        this.roll.setPrefWidth(BUTTON_WIDTH);
        this.roll.setPrefHeight(BUTTON_HEIGHT);

        this.back.setPrefWidth(BUTTON_WIDTH);
        this.back.setPrefHeight(BUTTON_HEIGHT);

        this.back.setOnAction(e -> { 
            final PauseBox pause = new PauseBox(playStage);
            pause.show();
        });

        this.roll.setOnAction(e -> ViewImpl.getObserver().rollDice());

        try (FileInputStream in = new FileInputStream("./res/GameBoards/GameBoard1.png")) {

            final Image board = new Image(in);

            final Double transposeY = (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - BOARD_H) / 2;
            final Double transposeX = (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - BUTTON_WIDTH - 2 * HORIZONTAL_INSETS - BOARD_H) / 2;

            final BackgroundPosition pos = new BackgroundPosition(Side.LEFT, transposeX, false, Side.TOP, transposeY, false);
            final BackgroundSize size = new BackgroundSize(BOARD_H, BOARD_H, false, false, false, false);

            final Background bg = new Background(new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, pos, size));
            this.getDefaultLayout().setBackground(bg);
            this.setFill(Color.LIGHTBLUE);

        } catch (Exception e) {
            ConsoleLog.get().print("Error while reading the game board...");
        }

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
