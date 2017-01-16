package view;

import java.awt.Toolkit;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends BasicScene {

    private static final String BACK = "Back";
    private static final String ROLL = "Roll";
    private static final int DICE_DEFAULT_VALUE = 0;
    private static final double BOX_SPACING = 20;
    private static final double BOX_INSETS = 60;
    private static final double BOX_W_PERC = 0.20;
    private static final double BUTTON_WIDTH = 150;
    private static final double BUTTON_HEIGHT = 40;
    private static final int FONT_SIZE = 30;

    private static Play playScene = new Play();
    private static Stage playStage;

    private final Button back = new BasicButton(BACK);
    private final Button roll = new BasicButton(ROLL); 
    private final Label diceValue = new Label(String.valueOf(DICE_DEFAULT_VALUE));
    private final Label turn = new Label(); 
    private final VBox box = new VBox(turn, diceValue, roll, back);

    private Play() {

        this.getDefaultLayout().setRight(this.box);

        box.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * Dimension.SCREEN_W_PERC * BOX_W_PERC);
        this.box.setSpacing(BOX_SPACING);
        box.setPadding(new Insets(BOX_INSETS));

        diceValue.setFont(new Font(FONT_SIZE));
        turn.setFont(new Font(FONT_SIZE));

        roll.setPrefWidth(BUTTON_WIDTH);
        roll.setPrefHeight(BUTTON_HEIGHT);

        back.setPrefWidth(BUTTON_WIDTH);
        back.setPrefHeight(BUTTON_HEIGHT);

        back.setOnAction(e -> { 
            final PauseBox pause = new PauseBox(playStage);
            pause.show();
        });
        roll.setOnAction(e -> ViewImpl.getObserver().rollDice());
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
}
