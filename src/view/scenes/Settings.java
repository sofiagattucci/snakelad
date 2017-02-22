package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.BasicButton;
import view.Dimension;

/**
 * It' s a scene of the application. It manages some optional features of the game
 * such as languages, music, colors...
 */
public final class Settings extends BasicScene {

    private static final String TITLE = "Settings";
    private static final String BACK = "Back";
    private static final String PAWN_COLOR = "Change pawns' color";
    private static final String LANGUAGE_MSG = "Change language";
    private static final int TITLE_FONT = 60; 
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 10;

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Label title = new Label(TITLE);
    private final Button pawnColor = new BasicButton(PAWN_COLOR);
    private final Button language = new BasicButton(LANGUAGE_MSG);
    private final Button back = new BasicButton(BACK);
    private final VBox box = new VBox(title, pawnColor, language, back);

    private Settings() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.title.setFont(new Font(TITLE_FONT));
        this.title.setTranslateY(Y_TITLE_TRANSLATE);
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
