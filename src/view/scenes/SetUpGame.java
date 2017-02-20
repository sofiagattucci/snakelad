package view.scenes;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.BasicButton;
import view.Toolbar;
import view.ViewImpl;

/**
 * It's the scene shown when the user pushes the play button. It manages the settings to use for this game. 
 */
public final class SetUpGame extends BasicScene {

    private static final String START = "Start";

    private static Stage setUpStage;
    private static SetUpGame setUpScene = new SetUpGame();

    private final Button start = new BasicButton(START);

    private SetUpGame() {

        this.getDefaultLayout().setCenter(this.start);

        this.start.setOnAction(e -> {
            ViewImpl.setPlayScene(SinglePlayerPlay.getScene(setUpStage));
            ViewImpl.getObserver().play();
            setUpStage.setScene(SinglePlayerPlay.getScene(setUpStage));
        });
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The set up scene
     */
    public static SetUpGame getScene(final Stage stage) {
        setUpStage = stage;
        Toolbar.setStage(stage);
        return setUpScene;
    }
}
