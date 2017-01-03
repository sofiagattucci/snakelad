package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class creates and initializes the instructions scene.
 */
public final class Instructions extends Scene {

    private static final String BACK = "Back";

    private final BorderPane bp = new BorderPane();
    private static Stage instrStage;
    private static Instructions instructionsScene = new Instructions();
    private final Button back = new Button(BACK);

    private Instructions() {
        super(new BorderPane());
        this.setRoot(this.bp);

        this.bp.setCenter(back);
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
