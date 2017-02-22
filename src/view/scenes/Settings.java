package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.Language;
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
    private static final String LANGUAGE_MSG = "Change language: ";
    private static final String DEFAULT_LANGUAGE = Language.EN.name();
    private static final int TITLE_FONT = 60;
    private static final int FONT = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 10;

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Label title = new Label(TITLE);
    private final Button pawnColor = new BasicButton(PAWN_COLOR);
    private final Label langLabel = new Label(LANGUAGE_MSG);
    private final ComboBox<Language> languages = new ComboBox<>();
    private final HBox langBox = new HBox(langLabel, languages);
    private final Button back = new BasicButton(BACK);
    private final VBox box = new VBox(title, pawnColor, langBox, back);

    private Settings() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);

        this.title.setFont(new Font(TITLE_FONT));
        this.title.setTranslateY(Y_TITLE_TRANSLATE);

        this.langBox.setAlignment(Pos.CENTER);
        this.langLabel.setFont(new Font(FONT));
        for (final Language elem: Language.values()) {
            this.languages.getItems().add(elem);
        }
        this.languages.setPromptText(DEFAULT_LANGUAGE);

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
