package view.scenes.settings;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utilities.Pair;
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
    private static final int FONT_SIZE = 30;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;

    private final HBox box = new HBox();
    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final List<Button> selectorList = Arrays.asList(new Button(), new Button(), new Button());
    private final List<Pair<Color, String>> colorList = Arrays.asList(new Pair<>(Color.LIGHTBLUE, BLUE_SKIN_KEY),
            new Pair<>(Color.ANTIQUEWHITE, LIGHT_SKIN_KEY), new Pair<>(Color.DARKMAGENTA, DARK_SKIN_KEY));

    /**
     * Constructor of this class.
     */
    public SkinSwitcher() {

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.box.getChildren().add(this.title);
        this.title.setFont(new Font(FONT_SIZE));
        this.selectorList.get(0).setDisable(true);

        for (int i = 0; i < this.selectorList.size(); i++) {
            final int j = i;
            this.box.getChildren().add(this.selectorList.get(i));
            this.setButtonText(i);
            this.selectorList.get(i).setOnAction(e -> {
                for (final Button b: this.selectorList) {
                    b.setDisable(false);
                }
                this.selectorList.get(j).setDisable(true);
                ViewImpl.getSettingsScene().setSkin(this.colorList.get(j).getFirst());
                ViewImpl.getMenuScene().setSkin(this.colorList.get(j).getFirst());
                Instructions.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(j).getFirst());
                SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(j).getFirst());
                SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(j).getFirst());
                MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(this.colorList.get(j).getFirst());
            });
    }
}
    private void setButtonText(final int index) {
        this.selectorList.get(index).setText(LanguageStringMap.get().getMap().get(this.colorList.get(index).getSecond()));
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        for (int i = 0; i < this.selectorList.size(); i++) {
            this.setButtonText(i);
        }
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
