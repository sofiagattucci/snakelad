package view.scenes.settings;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.BasicButton;
import view.ViewImpl;
import view.scenes.Instructions;
import view.scenes.game.MultiPlayerGameScenes;
import view.scenes.game.SinglePlayerGame;
import view.scenes.setup.SetUpGame;

/**
 * This class manages the elements of the GUI that allow to change the color the panels.
 */
public class SkinSwitcher {

    private static final int FONT_SIZE = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;

    private final HBox box = new HBox();
    private final Label title = new Label("Select skin:");
    private final Button defaultSkin = new Button("Default skin");
    private final Button lightSkin = new Button("Light skin");
    private final Button darkSkin = new Button("Dark skin");

    /**
     * Constructor of this class.
     */
    public SkinSwitcher() {

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.box.getChildren().addAll(this.title, this.defaultSkin, this.lightSkin, this.darkSkin);
        this.title.setFont(new Font(FONT_SIZE));
        this.defaultSkin.setOnAction(e -> {
            ViewImpl.getSettingsScene().setSkin(Color.LIGHTBLUE);
            ViewImpl.getMenuScene().setSkin(Color.LIGHTBLUE);
            Instructions.getScene(ViewImpl.getAppStage()).setSkin(Color.LIGHTBLUE);
            SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(Color.LIGHTBLUE);
            SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(Color.LIGHTBLUE);
            MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(Color.LIGHTBLUE);
        });
        this.lightSkin.setOnAction(e -> {
            ViewImpl.getSettingsScene().setSkin(Color.ANTIQUEWHITE);
            ViewImpl.getMenuScene().setSkin(Color.ANTIQUEWHITE);
            Instructions.getScene(ViewImpl.getAppStage()).setSkin(Color.ANTIQUEWHITE);
            SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(Color.ANTIQUEWHITE);
            SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(Color.ANTIQUEWHITE);
            MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(Color.ANTIQUEWHITE);
        });
        this.darkSkin.setOnAction(e -> {
            ViewImpl.getSettingsScene().setSkin(Color.DARKMAGENTA);
            ViewImpl.getMenuScene().setSkin(Color.DARKMAGENTA);
            Instructions.getScene(ViewImpl.getAppStage()).setSkin(Color.DARKMAGENTA);
            SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(Color.DARKMAGENTA);
            SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(Color.DARKMAGENTA);
            MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(Color.DARKMAGENTA);
        });

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
