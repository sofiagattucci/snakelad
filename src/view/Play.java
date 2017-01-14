package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends BasicScene {

    private static final String BACK = "Back";
    private static final String ROLL = "Roll";
    private static final int DICE_DEFAULT_VALUE = 0;
    private static final double BOX_SPACING = 20;
    private static final double BOX_INSETS = 100;

    private static Play playScene = new Play();
    private static Stage playStage;

    private final Button back = new BasicButton(BACK);
    private final Button roll = new BasicButton(ROLL); 
    private final Label diceValue = new Label(String.valueOf(DICE_DEFAULT_VALUE));
    private final VBox box = new VBox(diceValue, roll, back);

    private Play() {

        this.getDefaultLayout().setRight(this.box);

        this.box.setSpacing(BOX_SPACING);
        this.box.setTranslateY(100);
        box.setPadding(new Insets(BOX_INSETS));

        back.setOnAction(e -> playStage.setScene(Menu.getScene(playStage)));

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
}
