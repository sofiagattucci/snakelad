package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilities.ImageManager;
import view.BasicButton;
import view.LanguageStringMap;
import view.ViewImpl;
import view.dialogboxes.ClosureHandler;
import view.scenes.settings.Settings;
import view.scenes.setup.SetUpGame;
/**
 * This class creates and initializes the main menu scene. 
 * It' s built using a Singleton pattern.
 */
public final class Menu extends BasicScene {

    private static final String PLAY_KEY = "menu.play";
    private static final String STATISTICS_KEY = "menu.statistics";
    private static final String INSTRUCTIONS_KEY = "menu.instructions";
    private static final String SETTINGS_KEY = "menu.settings";
    private static final String QUIT_KEY = "menu.quit";
    private static final String LOGO_PATH = "./res/icons/mainMenuLogo.png";
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final double LOGO_Y_TRANSLATION = BasicButton.getButtonHeight() / 2;
    private static final double LOGO_HEIGHT = view.Dimension.BOARD_H / 3;

    private static final Menu MENU_SCENE = new Menu();
    private static Stage menuStage;
    private final Button play = new BasicButton(LanguageStringMap.get().getMap().get(PLAY_KEY));
    private final Button statistics = new BasicButton(LanguageStringMap.get().getMap().get(STATISTICS_KEY));
    private final Button instructions = new BasicButton(LanguageStringMap.get().getMap().get(INSTRUCTIONS_KEY));
    private final Button settings = new BasicButton(LanguageStringMap.get().getMap().get(SETTINGS_KEY));
    private final Button quit = new BasicButton(LanguageStringMap.get().getMap().get(QUIT_KEY));
    private final ImageView logo = ImageManager.get().getImageView(LOGO_PATH);
    private final VBox box = new VBox(this.play, this.statistics, this.instructions, this.settings, this.quit);
    private final ClosureHandler closure = new ClosureHandler(menuStage);

    private Menu() {

        ViewImpl.setMenuScene(this);

        this.play.setOnAction(e -> {
            SetUpGame.getScene(menuStage).reset();
            ViewImpl.setSetUpScene(SetUpGame.getScene(menuStage));
            menuStage.setScene(SetUpGame.getScene(menuStage));
        });

        this.statistics.setOnAction(e -> {
            ViewImpl.getObserver().gameStatistics();
            menuStage.setScene(StatisticsScene.getScene(menuStage));
        });

        this.instructions.setOnAction(e -> menuStage.setScene(Instructions.getScene(menuStage)));

        this.settings.setOnAction(e -> {
            ViewImpl.setSettingsScene(Settings.getScene(menuStage));
            menuStage.setScene(Settings.getScene(menuStage));
        });

        this.quit.setOnAction(e -> this.closure.show());

        this.getDefaultLayout().setCenter(this.box);

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);

        this.getDefaultLayout().setTop(this.logo);
        BorderPane.setAlignment(this.logo, Pos.CENTER);
        this.logo.setPreserveRatio(true);
        this.logo.setFitHeight(LOGO_HEIGHT);
        this.logo.setTranslateY(LOGO_Y_TRANSLATION);
    }

    /**
     * It updates the language of this scene.
     */
    public void updateLanguage() {
        this.play.setText(LanguageStringMap.get().getMap().get(PLAY_KEY));
        this.statistics.setText(LanguageStringMap.get().getMap().get(STATISTICS_KEY));
        this.instructions.setText(LanguageStringMap.get().getMap().get(INSTRUCTIONS_KEY));
        this.settings.setText(LanguageStringMap.get().getMap().get(SETTINGS_KEY));
        this.quit.setText(LanguageStringMap.get().getMap().get(QUIT_KEY));
        this.closure.updateLanguage();
    }

    /**
     * Getter of the scene.
     * @param stage
     *     The stage that will be linked to the one of this class 
     * @return
     *     The menu scene
     */
    public static Menu getScene(final Stage stage) {
        menuStage = stage;
        return MENU_SCENE;
    }
}
