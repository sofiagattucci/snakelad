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

    private static final String SINGLE = "Single player";
    private static final String MULTI = "Multiplayer";

    private static Stage setUpStage;
    private static SetUpGame setUpScene = new SetUpGame();

    private final Button single = new BasicButton(SINGLE);
    private final Button multi = new BasicButton(MULTI);

    private SetUpGame() {

        this.getDefaultLayout().setLeft(this.single);
        this.getDefaultLayout().setRight(this.multi);

        this.single.setOnAction(e -> {
            ViewImpl.setPlayScene(SinglePlayerPlay.getScene(setUpStage));
            ViewImpl.getObserver().play();
            setUpStage.setScene(SinglePlayerPlay.getScene(setUpStage));
        });

        this.multi.setOnAction(e -> {
            //ViewImpl.setSinglePlayScene(MultiplayerPlay.getScene(setUpStage));
            //ViewImpl.getObserver().play();
            //setUpStage.setScene(MultiplayerPlay.getScene(setUpStage));
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
