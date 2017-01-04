package view;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends BasicScene {

    private static final String BACK = "Back";

    private static Play playScene = new Play();
    private static Stage playStage;

    private final Button back = new Button(BACK); 

    private Play() {

        this.getDefaultLayout().setCenter(this.back);
        back.setOnAction(e -> playStage.setScene(Menu.getScene(playStage)));
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
}
