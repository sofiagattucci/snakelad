package view;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.ImageLoader;

/**
 * It sets up the tool bar in the game screen.
 */
public class Toolbar {

    private static final String PAUSE = "Pause";
    private static final String ROLL = "Roll";
    private static final String PLAYER = "Player";
    private static final String CPU = "CPU";
    private static final String PAWN1_PATH = "./res/Pawns/RedPawn.png";
    private static final String PAWN2_PATH = "./res/Pawns/LightBluePawn.png";
    private static final double BOX_WIDTH = Dimension.SCREEN_W * 0.22;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double VERTICAL_INSETS = Dimension.SCREEN_H * 0.05;
    private static final double HORIZONTAL_INSETS = Dimension.SCREEN_W * 0.05;
    private static final int FONT_SIZE = 35;
    private static final double BUTTON_WIDTH = Dimension.SCREEN_W * 0.18;
    private static final double BUTTON_HEIGHT = Dimension.SCREEN_H * 0.07;
    private static final double CENTER_DICE = BUTTON_WIDTH / 15;
    private static final int N_DICE_SIDES = 6;

    private static Stage toolStage;

    private final Button pause = new BasicButton(PAUSE);
    private final Button roll = new BasicButton(ROLL); 
    private final Label turn = new Label();
    private final GridPane gp = new GridPane();
    private final ImageView dice = ImageLoader.get().getImageView("./res/Dice/ClassicDice/DiceSide1.png");
    private final VBox box = new VBox(turn, gp, roll, dice, pause);
    private final Map<Integer, String> diceSides = new HashMap<>();

    /**
     * Constructor of the tool bar.
     */
    public Toolbar() {

        this.box.setPrefWidth(BOX_WIDTH);
        this.box.setSpacing(BOX_SPACING);
        this.box.setPadding(new Insets(VERTICAL_INSETS, HORIZONTAL_INSETS, VERTICAL_INSETS, HORIZONTAL_INSETS));
        this.box.setStyle("-fx-background-color: #336699;");
        this.turn.setFont(new Font(FONT_SIZE));

        gp.addRow(0, new Pawn(PAWN1_PATH).getPawn(), new Label(PLAYER));
        gp.addRow(1, new Pawn(PAWN2_PATH).getPawn(), new Label(CPU));

        this.dice.setTranslateX(CENTER_DICE);

        this.roll.setPrefWidth(BUTTON_WIDTH);
        this.roll.setPrefHeight(BUTTON_HEIGHT);

        this.pause.setPrefWidth(BUTTON_WIDTH);
        this.pause.setPrefHeight(BUTTON_HEIGHT);

        this.pause.setOnAction(e -> { 
            final PauseBox pause = new PauseBox(toolStage);
            pause.show();
        });

        this.roll.setOnAction(e -> ViewImpl.getObserver().rollDice());

        for (int i = 1; i <= N_DICE_SIDES; i++) {
            this.diceSides.put(i, "./res/Dice/ClassicDice/DiceSide" + i + ".png");
        }
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
    public ImageView getDice() {
        return this.dice;
    }

    /**
     * Getter of the map which contains all the possible dice images (the path to each image).
     * @return
     *     The dice map
     */
    public Map<Integer, String> getDiceSides() {
        return this.diceSides;
    }

    /**
     * Getter of label that shows the current turn.
     * @return
     *     The turn label
     */
    public Label getTurn() {
        return this.turn;
    }

    /**
     * It links the stage that contains the game scene to the one of this class.
     * @param stage
     *     The stage to link
     */
    public static void setStage(final Stage stage) {
        toolStage = stage;
    }
}
