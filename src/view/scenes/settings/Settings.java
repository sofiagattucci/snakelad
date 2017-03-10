package view.scenes.settings;

import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.BasicButton;
import view.Dimension;
import view.LanguageStringMap;
import view.scenes.BasicScene;
import view.scenes.Menu;

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
    private static final String STYLESHEET_PATH = "./res/style.css";

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final SkinSwitcher skinSwitcher = new SkinSwitcher();
    private final PawnColorSwitcherManager pawnSwitcher = new PawnColorSwitcherManager();
    private final LanguageSwitcher langSwitcher = new LanguageSwitcher(settingStage);
    private final MusicManager musicManager = new MusicManager();
    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final VBox box = new VBox(this.title, this.pawnSwitcher.getParentNode(), this.langSwitcher.getParentNode(),
            this.skinSwitcher.getParentNode(), this.musicManager.getParentNode(), this.back);

    private Settings() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.title.setFont(new Font(TITLE_FONT));
        this.title.setTranslateY(Y_TITLE_TRANSLATE);
        this.back.setOnAction(e -> {
            settingStage.setScene(Menu.getScene(settingStage));
        });
        this.getStylesheets().add(Paths.get(STYLESHEET_PATH).toUri().toString());
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.skinSwitcher.updateLanguage();
        this.langSwitcher.updateLanguage();
        this.pawnSwitcher.updateLanguage();
        this.musicManager.updateLanguage();
        this.back.setText(LanguageStringMap.get().getMap().get(BACK_KEY));
    }

    /**
     * It updates the elements shown in the combo boxes.
     */
    public void updateComboLang() {
        this.pawnSwitcher.updatePrompt();
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

    /**
     * Getter of the music manager.
     * @return
     *     The MusicManager used in this class
     */
    public MusicManager getMusicManger() {
        return this.musicManager;
    }
}
