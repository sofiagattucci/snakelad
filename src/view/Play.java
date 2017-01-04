package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class creates and initializes the game scene.
 */
public final class Play extends Scene {

    private static final String BACK = "Back";

    private static Play playScene = new Play();
    private static Stage playStage;
    private final BorderPane bp = new BorderPane();

    private final Button back = new Button(BACK); 

    private Play() {
        super(new BorderPane());
        this.setRoot(this.bp);

        bp.setCenter(this.back);
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
