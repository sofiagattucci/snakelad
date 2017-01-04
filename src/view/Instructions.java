package view;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class creates and initializes the instructions scene.
 */
public final class Instructions extends BasicScene {

    private static final String BACK = "Back";

    private static Stage instrStage;
    private static Instructions instructionsScene = new Instructions();
    private final Button back = new Button(BACK);

    private Instructions() {

        this.getDefaultLayout().setCenter(back);
        this.back.setOnAction(e -> instrStage.setScene(Menu.getScene(instrStage)));

    }

    /**
     * Getter of this scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The instructions scene
     */
    public static Instructions getScene(final Stage stage) {
        instrStage = stage;
        return instructionsScene;
    }
}
