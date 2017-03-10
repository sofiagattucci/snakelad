package view.scenes.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.ImageManager;
import utilities.Pair;
import utilities.TypesOfDice;
import view.BasicButton;
import view.Dimension;
import view.LanguageStringMap;
import view.ViewImpl;
import view.dialogboxes.PauseBox;
import view.dice.Dice;
import view.dice.DiceImpl;
import view.pawn.AvailableColor;
import view.pawn.PawnImpl;
import view.pawn.PawnTypes;

/**
 * It manages the tool bar in the game screen.
 */
public abstract class Toolbar {

    private static final String PAUSE_KEY = "game.pause";
    private static final String ROLL_KEY = "game.roll";
    private static final String PLAYER_KEY = "game.player";
    private static final String TOOLBAR_ID = "Toolbar";
    private static final String BLACK_LABEL_ID = "Toolbar-black-label";
    private static final String YELLOW_LABEL_ID = "Toolbar-yellow-label";
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;
    private static final double BUTTON_WIDTH = BOX_WIDTH * 0.6;
    private static final double BUTTON_HEIGHT = Dimension.SCREEN_H * 0.07;
    private static final double BOX_SPACING = BUTTON_HEIGHT / 2.5;
    private static final double VERTICAL_INSETS = Dimension.SCREEN_H * 0.05;
    private static final double HORIZONTAL_INSETS = Dimension.SCREEN_W * 0.05;
    private static final double ALIGN_DICE = BUTTON_WIDTH / 11;
    private static final double ALIGN_GRID = -BUTTON_WIDTH * 0.1;
    private static final int SMALL_FONT_SIZE = 20;
    private static final int BIG_FONT_SIZE = 30;

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
    private final List<Pair<ImageView, Label>> pawnList = new ArrayList<>();
    private final PauseBox pauseBox = new PauseBox(toolStage);
    private int numPlayers;

    /**
     * Constructor of the tool bar.
     */
    public Toolbar() {

        this.box.setPrefWidth(BOX_WIDTH);
        this.box.setSpacing(BOX_SPACING);
        this.box.setPadding(new Insets(VERTICAL_INSETS, HORIZONTAL_INSETS, VERTICAL_INSETS, HORIZONTAL_INSETS));
        this.box.setId(TOOLBAR_ID);

        this.gp.setTranslateX(ALIGN_GRID);
        this.diceImView.setTranslateX(ALIGN_DICE);

        this.roll.setPrefWidth(BUTTON_WIDTH);
        this.roll.setPrefHeight(BUTTON_HEIGHT);

        this.pause.setPrefWidth(BUTTON_WIDTH);
        this.pause.setPrefHeight(BUTTON_HEIGHT);

        this.pause.setOnAction(e ->  {
            ViewImpl.getObserver().pause();
            this.pauseBox.show();
        });

        this.roll.setOnAction(e -> {
            this.roll.setVisible(false);
            this.pause.setVisible(false);
            ViewImpl.getObserver().rollDice();
        });
    }

    /**
     * It inserts the player' s name in the tool bar.
     * @param scene
     *     The scene where this tool bar is put
     * @param nPlayers
     *     The number of players of the game
     */
    public void putLabels(final Game scene, final int nPlayers) {
        this.numPlayers = nPlayers;
        IntStream.iterate(0, i -> i + 1)
                 .limit(nPlayers)
                 .forEach(i -> {
                     final Pair<ImageView, Label> p = new Pair<>(new PawnImpl(scene,
                             PawnTypes.get().getPawn(this.getColorFromMode(i))).getPawn(), new Label(playerLabel.getText() + (i + 1)));
                     this.pawnList.add(p);
                     this.pawnList.get(i).getSecond().setFont(smallFont);
                     this.gp.addRow(i, p.getFirst(), p.getSecond());
                 });
    }

    /**
     * It updates the images (color) of the pawn shown in the tool bar.
     */
    public void updateLabelsColor() {
        IntStream.iterate(0, i -> i + 1)
                 .limit(this.numPlayers)
                 .forEach(i -> this.pawnList.get(i).getFirst().setImage(
                         ImageManager.get().readFromFile(PawnTypes.get().getPawn(this.getColorFromMode(i)))));
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.roll.setText(LanguageStringMap.get().getMap().get(ROLL_KEY));
        this.pause.setText(LanguageStringMap.get().getMap().get(PAUSE_KEY));
        playerLabel.setText(LanguageStringMap.get().getMap().get(PLAYER_KEY));
        IntStream.iterate(0, i -> i + 1)
                 .limit(this.numPlayers)
                 .forEach(i -> this.pawnList.get(i).getSecond().setText(playerLabel.getText() + (i + 1)));
        this.pauseBox.updateLanguage();
    }

    /**
     * Changes the turn shown in the game screen.
     * @param newTurn
     *     The new turn to set
     */
    public void changeTurn(final int newTurn) {

        for (final Pair<ImageView, Label> l: this.pawnList) {
            l.getSecond().setFont(smallFont);
            l.getSecond().setId(BLACK_LABEL_ID);
        }
        this.pawnList.get(newTurn).getSecond().setFont(bigFont);
        this.pawnList.get(newTurn).getSecond().setId(YELLOW_LABEL_ID);
    }

    /**
     * It resets the elements of the tool bar(turn, dice).
     */
    public void reset() {
        this.resetTurn();
        this.diceImView.setVisible(false);
        this.roll.setVisible(true);
        this.pause.setVisible(true);
    }

    private void resetTurn() {
        for (final Pair<ImageView, Label> l: this.pawnList) {
            l.getSecond().setFont(smallFont);
            l.getSecond().setId(BLACK_LABEL_ID);
        }
        this.pawnList.get(0).getSecond().setFont(bigFont);
        this.pawnList.get(0).getSecond().setId(YELLOW_LABEL_ID);
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
        this.pause.setVisible(true);
    }

    /**
     * It updates the dice used.
     * @param newDice
     *     The new dice to use.
     */
    public void updateDice(final TypesOfDice newDice) {
        this.dice.changeDice(newDice);
    }

    /**
     * Getter of the color of the selected pawn. The color depends on the game mode selected.
     * @param i
     *     The index of the pawn
     * @return
     *     The pawn used color
     */
    protected abstract AvailableColor getColorFromMode(int i);

    /**
     * Getter of the list which holds the pairs Image/Label shown in the tool bar.
     * @return
     *     The list of pairs ImageView, Pair of the pawns
     */
    protected List<Pair<ImageView, Label>> getPawnList() {
        return this.pawnList;
    }
}
