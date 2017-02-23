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
import view.LanguageStringMap;
import view.ViewImpl;

/**
 * It' s a scene of the application. It manages some optional features of the game
 * such as languages, music, colors...
 */
public final class Settings extends BasicScene {

    private static final String TITLE_KEY = "settings.title";
    private static final String BACK_KEY = "back";
    private static final String PAWN_COLOR = "Change pawns' color";
    private static final String LANGUAGE_MSG_KEY = "settings.languageLabel";
    private static final int TITLE_FONT = 60;
    private static final int FONT = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 10;

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final Button pawnColor = new BasicButton(PAWN_COLOR);
    private final Label langLabel = new Label(LanguageStringMap.get().getMap().get(LANGUAGE_MSG_KEY));
    private final ComboBox<Language> languages = new ComboBox<>();
    private final HBox langBox = new HBox(langLabel, languages);
    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
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
        this.languages.setPromptText(ViewImpl.getDefaultLanguage().name());
        this.languages.setOnAction(e -> {
            ViewImpl.getObserver().setLanguage(this.languages.getValue());
            this.updateLanguage();
            Menu.getScene(settingStage).updateLanguage();
            Instructions.getScene(settingStage).updateLanguage();
            SetUpGame.getScene(settingStage).updateLanguage();
            ViewImpl.getPlayScene().updateLanguage();
        });

        this.back.setOnAction(e -> {
            settingStage.setScene(Menu.getScene(settingStage));
        });
    }

    private void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.langLabel.setText(LanguageStringMap.get().getMap().get(LANGUAGE_MSG_KEY));
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
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
