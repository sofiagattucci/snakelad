package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.BasicButton;
import view.Dimension;
import view.LanguageStringMap;
import view.ViewImpl;
import view.dialogboxes.ClosureHandler;
/**
 * This class creates and initializes the main menu scene. 
 * It' s built using a Singleton pattern.
 */
public final class Menu extends BasicScene {

    private static final String PLAY_KEY = "menu.play";
    private static final String INSTRUCTIONS_KEY = "menu.instructions";
    private static final String SETTINGS_KEY = "menu.settings";
    private static final String QUIT_KEY = "menu.quit";
    private static final String TITLE = "SnakeNLadder";
    private static final double TITLE_TOP_PADDING = Dimension.SCREEN_H / 6;
    private static final int FONT_SIZE = 65;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;

    private static final Menu MENU_SCENE = new Menu();
    private static Stage menuStage;
    private final Button play = new BasicButton(LanguageStringMap.get().getMap().get(PLAY_KEY));
    private final Button instructions = new BasicButton(LanguageStringMap.get().getMap().get(INSTRUCTIONS_KEY));
    private final Button settings = new BasicButton(LanguageStringMap.get().getMap().get(SETTINGS_KEY));
    private final Button quit = new BasicButton(LanguageStringMap.get().getMap().get(QUIT_KEY));
    private final Text title = new Text(TITLE);
    private final VBox box = new VBox(play, instructions, settings, quit);
    private final ClosureHandler closure = new ClosureHandler(menuStage);

    private Menu() {

        this.play.setOnAction(e -> {
            SetUpGame.getScene(menuStage).reset();
            ViewImpl.setSetUpScene(SetUpGame.getScene(menuStage));
            menuStage.setScene(SetUpGame.getScene(menuStage));
        });

        this.instructions.setOnAction(e -> {
            menuStage.setScene(Instructions.getScene(menuStage));
        });

        this.settings.setOnAction(e -> {
            menuStage.setScene(Settings.getScene(menuStage));
        });

        this.quit.setOnAction(e -> this.closure.show());

        this.getDefaultLayout().setCenter(box);

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);

        this.getDefaultLayout().setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        this.title.setFont(new Font(FONT_SIZE));
        this.title.setTranslateY(TITLE_TOP_PADDING);

    }

    /**
     * It updates the language of this scene.
     */
    public void updateLanguage() {
        this.play.setText(LanguageStringMap.get().getMap().get(PLAY_KEY));
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
