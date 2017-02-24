package view.scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utilities.ImageManager;
import utilities.Language;
import utilities.Pair;
import view.BasicButton;
import view.Dimension;
import view.FlagsMap;
import view.LanguageStringMap;
import view.ViewImpl;
import view.pawn.PawnsColor;

/**
 * It' s a scene of the application. It manages some optional features of the game
 * such as languages, music, colors...
 */
public final class Settings extends BasicScene {

    private static final String TITLE_KEY = "settings.title";
    private static final String BACK_KEY = "back";
    private static final String PAWN_LABEL_KEY = "settings.pawnLabel";
    private static final String LANGUAGE_MSG_KEY = "settings.languageLabel";
    private static final String PLAYER_KEY = "game.player";
    private static final String CPU = "CPU";
    private static final int TITLE_FONT = 60;
    private static final int FONT = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double Y_TITLE_TRANSLATE = -Dimension.SCREEN_H / 10;
    private static final double SMALL_FLAG_H = BasicButton.getButtonHeight();
    private static final double SMALL_FLAG_W = SMALL_FLAG_H * 1.8;
    private static final double BIG_FLAG_H = BasicButton.getButtonHeight() * 1.5; 
    private static final double BIG_FLAG_W = BIG_FLAG_H * 1.8;
    private static final double COMBO_BOX_GAP = BasicButton.getButtonHeight() / 6;

    private static Stage settingStage;
    private static Settings settingsScene = new Settings();

    private final Label title = new Label(LanguageStringMap.get().getMap().get(TITLE_KEY));
    private final Label pawnLabel = new Label(LanguageStringMap.get().getMap().get(PAWN_LABEL_KEY));
    private final Label player = new Label(LanguageStringMap.get().getMap().get(PLAYER_KEY));
    private final Label cpu = new Label(CPU);
    private final GridPane singleGrid = new GridPane();
    private final ComboBox<Integer> singleComboP = new ComboBox<>();
    private final ComboBox<Integer> singleComboC = new ComboBox<>();
    private final List<Color> pawnColor = new ArrayList<>();
    private final HBox pawnBox = new HBox(this.singleGrid);
    private final Label langLabel = new Label(LanguageStringMap.get().getMap().get(LANGUAGE_MSG_KEY));
    private final List<Pair<Language, ImageView>> flagList = new ArrayList<>();
    private final HBox flagsBox = new HBox();
    private final Button back = new BasicButton(LanguageStringMap.get().getMap().get(BACK_KEY));
    private final VBox box = new VBox(this.title, this.pawnLabel, this.pawnBox, this.langLabel, this.flagsBox, this.back);

    private Settings() {

        this.getDefaultLayout().setCenter(this.box);
        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);

        this.title.setFont(new Font(TITLE_FONT));
        this.title.setTranslateY(Y_TITLE_TRANSLATE);

        this.singleGrid.add(this.player, 0, 0);
        this.singleGrid.add(this.singleComboP, 1, 0);
        this.singleGrid.add(this.cpu, 0, 1);
        this.singleGrid.add(this.singleComboC, 1, 1);
        this.singleGrid.setHgap(COMBO_BOX_GAP);
        this.singleGrid.setVgap(COMBO_BOX_GAP);

        this.pawnBox.setAlignment(Pos.CENTER);
        this.flagsBox.setAlignment(Pos.CENTER);
        this.langLabel.setFont(new Font(FONT));
        this.pawnLabel.setFont(new Font(FONT));
        this.cpu.setFont(new Font(FONT));
        this.player.setFont(new Font(FONT));

        this.back.setOnAction(e -> {
            settingStage.setScene(Menu.getScene(settingStage));
        });

        for (final Language lang: Language.values()) {
            this.flagList.add(new Pair<>(lang, ImageManager.get().getImageView(FlagsMap.get().getMap().get(lang))));
        }
        for (final Pair<Language, ImageView> elem: this.flagList) {
            this.flagsBox.getChildren().add(elem.getSecond());
            elem.getSecond().setFitHeight(SMALL_FLAG_H);
            elem.getSecond().setFitWidth(SMALL_FLAG_W);
            elem.getSecond().setOnMouseClicked(e -> {
                ViewImpl.getObserver().setLanguage(elem.getFirst());
                this.updateLanguage();
                Menu.getScene(settingStage).updateLanguage();
                Instructions.getScene(settingStage).updateLanguage();
                SetUpGame.getScene(settingStage).updateLanguage();
                for (final Pair<Language, ImageView> image: this.flagList) {
                    image.getSecond().setFitHeight(SMALL_FLAG_H);
                    image.getSecond().setFitWidth(SMALL_FLAG_W);
                }
                elem.getSecond().setFitHeight(BIG_FLAG_H);
                elem.getSecond().setFitWidth(BIG_FLAG_W);
            });
        }
        this.flagList.get(0).getSecond().setFitHeight(BIG_FLAG_H);
        this.flagList.get(0).getSecond().setFitWidth(BIG_FLAG_W);
    }

    private void updateLanguage() {
        this.title.setText(LanguageStringMap.get().getMap().get(TITLE_KEY));
        this.pawnLabel.setText(LanguageStringMap.get().getMap().get(PAWN_LABEL_KEY));
        this.player.setText(LanguageStringMap.get().getMap().get(PLAYER_KEY));
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
