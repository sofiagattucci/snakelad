package view.scenes.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
import view.scenes.StatisticsScene;
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
    private static final String GOLD_SKIN_KEY = "settings.skin.golden";
    private static final String SILVER_SKIN_KEY = "settings.skin.silver";
    private static final int FONT_SIZE = 30;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int N_SKIN = 5;

    private final HBox box = new HBox();
    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final List<Button> selectorList = new ArrayList<>();
    private final List<Pair<Color, String>> colorList = Arrays.asList(new Pair<>(Color.LIGHTBLUE, BLUE_SKIN_KEY),
            new Pair<>(Color.ANTIQUEWHITE, LIGHT_SKIN_KEY), new Pair<>(Color.DARKMAGENTA, DARK_SKIN_KEY), 
            new Pair<>(Color.GOLD, GOLD_SKIN_KEY), new Pair<>(Color.SILVER, SILVER_SKIN_KEY));

    /**
     * Constructor of this class.
     */
    public SkinSwitcher() {

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);
        this.box.getChildren().add(this.title);
        this.title.setFont(new Font(FONT_SIZE));

        Stream.generate(() -> new Button())
        .limit(N_SKIN)
        .forEach(this.selectorList :: add);

        this.selectorList.get(0).setDisable(true);

        IntStream.range(0, this.selectorList.size())
        .forEach(i -> {
            this.box.getChildren().add(this.selectorList.get(i));
            this.setButtonText(i);
            this.selectorList.get(i).setOnAction(e -> {
                for (final Button b: this.selectorList) {
                    b.setDisable(false);
                }
                this.selectorList.get(i).setDisable(true);
                ViewImpl.getSettingsScene().setSkin(this.colorList.get(i).getFirst());
                ViewImpl.getMenuScene().setSkin(this.colorList.get(i).getFirst());
                Instructions.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(i).getFirst());
                SetUpGame.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(i).getFirst());
                StatisticsScene.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(i).getFirst());
                SinglePlayerGame.getScene(ViewImpl.getAppStage()).setSkin(this.colorList.get(i).getFirst());
                MultiPlayerGameScenes.get(ViewImpl.getAppStage()).setActiveSkin(this.colorList.get(i).getFirst());
            });
        });
    }

    private void setButtonText(final int index) {
        this.selectorList.get(index).setText(LanguageStringMap.get().getMap().get(this.colorList.get(index).getSecond()));
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        IntStream.range(0, this.selectorList.size())
                 .forEach(i -> this.setButtonText(i));
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
