package view.scenes;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.BasicButton;

/**
 * It' s a scene of the application. It manages some optional features of the game
 * such as languages, music, colors...
 */
public final class Settings extends BasicScene {

    private static final String BACK = "Back";

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Button back = new BasicButton(BACK);

    private Settings() {

        this.getDefaultLayout().setCenter(this.back);
        this.back.setOnAction(e -> {
            settingStage.setScene(Menu.getScene(settingStage));
        });
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The scene where the scene is placed
     * @return
     *     The scene unique instance
     */
    public static Settings getScene(final Stage stage) {
        settingStage = stage;
        return settingsScene;
    }
}
