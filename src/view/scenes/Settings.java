package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.BasicButton;
import view.Dimension;
import view.LanguageStringMap;
import view.LanguageSwitcher;
import view.PawnColorSwitcher;

/**
 * It' s a scene of the application. It manages some optional features of the game
 * such as languages, music, colors...
 */
public final class Settings extends BasicScene {

    private static final String TITLE_KEY = "settings.title";
    private static final String BACK_KEY = "back";
    private static final int TITLE_FONT = 60;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 25;

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final LanguageSwitcher langSwitcher = new LanguageSwitcher(settingStage);
    private final PawnColorSwitcher pawnSwitcher = new PawnColorSwitcher();
    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final VBox box = new VBox(this.title, this.pawnSwitcher.getParentNode(), this.langSwitcher.getParentNode(), this.back);

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
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.langSwitcher.updateLanguage();
        this.pawnSwitcher.updateLanguage();
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The scene where this scene is placed
     * @return
     *     The scene unique instance
     */
    public static Settings getScene(final Stage stage) {
        settingStage = stage;
        return settingsScene;
    }
}
