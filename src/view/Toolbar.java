package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.dialog_boxes.PauseBox;
import view.pawns.PawnImpl;
import view.pawns.PawnTypes;
import view.scenes.SinglePlayerGame;

/**
 * It sets up the tool bar in the game screen.
 */
public class Toolbar {

    private static final String PAUSE_KEY = "game.pause";
    private static final String ROLL_KEY = "game.roll";
    private static final String CPU = "CPU";
    private static final String PLAYER_KEY = "game.player";
    private static final String BLACK_LABEL = "-fx-text-fill: black";
    private static final String YELLOW_LABEL = "-fx-text-fill: yellow";
    private static final String BOX_COLOR = "-fx-background-color: #336699;";
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;
    private static final double BUTTON_WIDTH = BOX_WIDTH * 0.6;
    private static final double BUTTON_HEIGHT = Dimension.SCREEN_H * 0.07;
    private static final double BOX_SPACING = BUTTON_HEIGHT / 2.5;
    private static final double VERTICAL_INSETS = Dimension.SCREEN_H * 0.05;
    private static final double HORIZONTAL_INSETS = Dimension.SCREEN_W * 0.05;
    private static final double ALIGN_DICE = BUTTON_WIDTH / 11;
    private static final double ALIGN_GRID = -BUTTON_WIDTH * 0.1;
    private static final int SMALL_FONT_SIZE = 20;
    private static final int BIG_FONT_SIZE = 40;

    private static Stage toolStage;

    private static Label playerLabel = new Label(LanguageStringMap.get().getMap().get(PLAYER_KEY));
    private final Button pause = new BasicButton(LanguageStringMap.get().getMap().get(PAUSE_KEY));
    private final Button roll = new BasicButton(LanguageStringMap.get().getMap().get(ROLL_KEY)); 
    private final Font smallFont = new Font(SMALL_FONT_SIZE);
    private final Font bigFont = new Font(BIG_FONT_SIZE);
    private final GridPane gp = new GridPane();
    private final Dice dice = new DiceImpl();
    private final ImageView diceImView = new ImageView(dice.getDiceImage());
    private final VBox box = new VBox(gp, roll, diceImView, pause);
    private final List<Label> pawnLabel = new ArrayList<>();
    private final PauseBox pauseBox = new PauseBox(toolStage);
    private int numPlayers;
    private boolean singleFlag;

    /**
     * Constructor of the tool bar.
     */
    public Toolbar() {

        this.box.setPrefWidth(BOX_WIDTH);
        this.box.setSpacing(BOX_SPACING);
        this.box.setPadding(new Insets(VERTICAL_INSETS, HORIZONTAL_INSETS, VERTICAL_INSETS, HORIZONTAL_INSETS));
        this.box.setStyle(BOX_COLOR);

        this.gp.setTranslateX(ALIGN_GRID);
        this.diceImView.setTranslateX(ALIGN_DICE);

        this.roll.setPrefWidth(BUTTON_WIDTH);
        this.roll.setPrefHeight(BUTTON_HEIGHT);

        this.pause.setPrefWidth(BUTTON_WIDTH);
        this.pause.setPrefHeight(BUTTON_HEIGHT);

        this.pause.setOnAction(e ->  this.pauseBox.show());

        this.roll.setOnAction(e -> {
            this.roll.setVisible(false);
            ViewImpl.getObserver().rollDice();
        });
    }

    /**
     * It inserts the player' s name in the tool bar.
     * @param nPlayers
     *     The number of players of the game
     */
    public void putLabels(final int nPlayers) {
        this.numPlayers = nPlayers;
        for (int i = 0; i < nPlayers; i++) {
            this.pawnLabel.add(new Label(playerLabel.getText() + (i + 1)));
            this.pawnLabel.get(i).setFont(smallFont);
            this.gp.addRow(i, new PawnImpl(PawnTypes.get().getPawn(i)).getPawn(), this.pawnLabel.get(i));
        }
    }

    /**
     * It sets the CPU player in single player game mode.
     */
    public void setCPU() {
        this.pawnLabel.get(SinglePlayerGame.getCPUIndex()).setText(CPU);
        this.singleFlag = true;
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.roll.setText(LanguageStringMap.get().getMap().get(ROLL_KEY));
        this.pause.setText(LanguageStringMap.get().getMap().get(PAUSE_KEY));
        playerLabel.setText(LanguageStringMap.get().getMap().get(PLAYER_KEY));
        for (int i = 0; i < numPlayers; i++) {
            this.pawnLabel.get(i).setText(playerLabel.getText() + (i + 1));
        }
        if (this.singleFlag) {
            this.setCPU();
        }
        this.pauseBox.updateLanguage();
    }

    /**
     * Changes the turn shown in the game screen.
     * @param newTurn
     *     The new turn to set
     */
    public void changeTurn(final int newTurn) {

        for (final Label l: pawnLabel) {
            l.setFont(smallFont);
            l.setStyle(BLACK_LABEL);
        }
        this.pawnLabel.get(newTurn).setFont(bigFont);
        this.pawnLabel.get(newTurn).setStyle(YELLOW_LABEL);
    }

    /**
     * It resets the elements of the tool bar(turn, dice).
     */
    public void reset() {
        this.resetTurn();
        this.diceImView.setVisible(false);
        this.roll.setVisible(true);
    }

    private void resetTurn() {
        for (final Label l: pawnLabel) {
            l.setFont(smallFont);
            l.setStyle(BLACK_LABEL);
        }
        this.pawnLabel.get(0).setFont(bigFont);
        this.pawnLabel.get(0).setStyle(YELLOW_LABEL);
    }

    /**
     * Getter of the box.
     * @return
     *     The box which contains all elements of the tool bar
     */
    public VBox getBox() {
        return this.box;
    }

    /**
     * Getter of the dice image view.
     * @return
     *     The dice
     */
    public ImageView getDiceImView() {
        return this.diceImView;
    }

    /**
     * Getter of the map which contains all the possible dice images (the path to each image).
     * @return
     *     The dice map
     */
    public Map<Integer, String> getDiceSides() {
        return Collections.unmodifiableMap(this.dice.getDiceSides());
    }

    /**
     * Getter of the box' s width.
     * @return
     *     The width of the box
     */
    public static double getBoxWidth() {
        return BOX_WIDTH;
    }

    /**
     * It links the stage that contains the game scene to the one of this class.
     * @param stage
     *     The stage to link
     */
    public static void setStage(final Stage stage) {
        toolStage = stage;
    }

    /**
     * It sets the roll button enabled again after the pawn finished to move.
     */
    public void endTurn() {
        this.roll.setVisible(true);
    }
}
