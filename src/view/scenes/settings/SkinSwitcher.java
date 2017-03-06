package view.scenes.settings;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;
import view.scenes.Instructions;
import view.scenes.game.MultiPlayerGameScenes;
import view.scenes.game.SinglePlayerGame;
import view.scenes.setup.SetUpGame;

/**
 * This class manages the elements of the GUI that allow to change the color the panels.
 */
public class SkinSwitcher {

    private static final String TITLE_KEY = "settings.skinLabel";
    private static final String BLUE_SKIN_KEY = "settings.skin.blue";
    private static final String LIGHT_SKIN_KEY = "settings.skin.light";
    private static final String DARK_SKIN_KEY = "settings.skin.dark";
    private static final int FONT_SIZE = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;

    private final HBox box = new HBox();
    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final Button defaultSkin = new Button(LanguageStringMap.get().getMap().get(BLUE_SKIN_KEY));
    private final Button lightSkin = new Button(LanguageStringMap.get().getMap().get(LIGHT_SKIN_KEY));
    private final Button darkSkin = new Button(LanguageStringMap.get().getMap().get(DARK_SKIN_KEY));

    /**
     * Constructor of this class.
     */
    public SkinSwitcher() {

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.box.getChildren().addAll(this.title, this.defaultSkin, this.lightSkin, this.darkSkin);
        this.title.setFont(new Font(FONT_SIZE));
        this.defaultSkin.setDisable(true);
        this.defaultSkin.setOnAction(e -> {
            this.defaultSkin.setDisable(true);
            this.lightSkin.setDisable(false);
            this.darkSkin.setDisable(false);
            ViewImpl.getSettingsScene().setSkin(Color.LIGHTBLUE);
            ViewImpl.getMenuScene().setSkin(Color.LIGHTBLUE);
            Instructions.getScene(ViewImpl.getAppStage()).setSkin(Color.LIGHTBLUE);
            SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(Color.LIGHTBLUE);
            SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(Color.LIGHTBLUE);
            MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(Color.LIGHTBLUE);
        });
        this.lightSkin.setOnAction(e -> {
            this.lightSkin.setDisable(true);
            this.defaultSkin.setDisable(false);
            this.darkSkin.setDisable(false);
            ViewImpl.getSettingsScene().setSkin(Color.ANTIQUEWHITE);
            ViewImpl.getMenuScene().setSkin(Color.ANTIQUEWHITE);
            Instructions.getScene(ViewImpl.getAppStage()).setSkin(Color.ANTIQUEWHITE);
            SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(Color.ANTIQUEWHITE);
            SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(Color.ANTIQUEWHITE);
            MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(Color.ANTIQUEWHITE);
        });
        this.darkSkin.setOnAction(e -> {
            this.darkSkin.setDisable(true);
            this.defaultSkin.setDisable(false);
            this.defaultSkin.setDisable(false);
            ViewImpl.getSettingsScene().setSkin(Color.DARKMAGENTA);
            ViewImpl.getMenuScene().setSkin(Color.DARKMAGENTA);
            Instructions.getScene(ViewImpl.getAppStage()).setSkin(Color.DARKMAGENTA);
            SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(Color.DARKMAGENTA);
            SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(Color.DARKMAGENTA);
            MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(Color.DARKMAGENTA);
        });

    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.defaultSkin.setText(LanguageStringMap.get().getMap().get(BLUE_SKIN_KEY));
        this.lightSkin.setText(LanguageStringMap.get().getMap().get(LIGHT_SKIN_KEY));
        this.darkSkin.setText(LanguageStringMap.get().getMap().get(DARK_SKIN_KEY));
    }

    /**
     * Getter of the parent node of the entire skin switcher.
     * @return
     *     The parent node of this class elements
     */
    public Node getParentNode() {
        return this.box;
    }
}
