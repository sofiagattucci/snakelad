package view.scenes.settings;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.ImageManager;
import utilities.Pair;
import utilities.enumeration.Language;
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;
import view.scenes.Instructions;
import view.scenes.Menu;
import view.scenes.setup.SetUpGame;

/**
 * It manages the elements of the GUI that permit the switch of the language. 
 */
public class LanguageSwitcher {

    private static final String LANGUAGE_MSG_KEY = "settings.languageLabel";
    private static final double SMALL_FLAG_H = BasicButton.getButtonHeight();
    private static final double SMALL_FLAG_W = SMALL_FLAG_H * 1.8;
    private static final double BIG_FLAG_H = BasicButton.getButtonHeight() * 1.5; 
    private static final double BIG_FLAG_W = BIG_FLAG_H * 1.8;
    private static final int DEFAULT_LANG_INDEX = 0;
    private static final int FONT_SIZE = 30;

    private final Label langLabel = new Label(LanguageStringMap.get().getMap().get(LANGUAGE_MSG_KEY));
    private final List<Pair<Language, ImageView>> flagList = new ArrayList<>();
    private final HBox flagsBox = new HBox();
    private final VBox box = new VBox(this.langLabel, this.flagsBox);
    private final Font stdFont = new Font(FONT_SIZE);

    /**
     * Constructor of this class.
     * @param stage
     *     The stage that hold the whole application
     */
    public LanguageSwitcher(final Stage stage) {

        this.box.setAlignment(Pos.CENTER);
        this.flagsBox.setAlignment(Pos.CENTER);
        this.langLabel.setFont(this.stdFont);

        for (final Language lang: Language.values()) {
            this.flagList.add(new Pair<>(lang, ImageManager.get().getImageView(FlagsMap.get().getMap().get(lang))));
        }
        for (final Pair<Language, ImageView> elem: this.flagList) {
            this.flagsBox.getChildren().add(elem.getSecond());
            elem.getSecond().setFitHeight(SMALL_FLAG_H);
            elem.getSecond().setFitWidth(SMALL_FLAG_W);
            elem.getSecond().setOnMouseClicked(e -> {
                ViewImpl.getSettingsScene().updateComboLang();
                ViewImpl.getObserver().setLanguage(elem.getFirst());
                Menu.getScene(stage).updateLanguage();
                Instructions.getScene(stage).updateLanguage();
                SetUpGame.getScene(stage).updateLanguage();
                ViewImpl.getSettingsScene().updateLanguage();
                for (final Pair<Language, ImageView> image: this.flagList) {
                    image.getSecond().setFitHeight(SMALL_FLAG_H);
                    image.getSecond().setFitWidth(SMALL_FLAG_W);
                }
                elem.getSecond().setFitHeight(BIG_FLAG_H);
                elem.getSecond().setFitWidth(BIG_FLAG_W);
            });
        }
        this.flagList.get(DEFAULT_LANG_INDEX).getSecond().setFitHeight(BIG_FLAG_H);
        this.flagList.get(DEFAULT_LANG_INDEX).getSecond().setFitWidth(BIG_FLAG_W);
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.langLabel.setText(LanguageStringMap.get().getMap().get(LANGUAGE_MSG_KEY));
    }

    /**
     * Getter of the parent node of the entire language switcher.
     * @return
     *     The parent node of this class elements
     */
    public Node getParentNode() {
        return this.box;
    }
}
