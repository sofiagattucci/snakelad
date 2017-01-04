package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * This class creates and initializes the main menu scene. 
 * It' s built using a Singleton pattern.
 */
public final class Menu extends BasicScene {

    private static final String PLAY = "PLAY";
    private static final String INSTRUCTIONS = "INSTRUCTIONS";
    private static final String QUIT = "QUIT";
    private static final String TITLE = "SnakeNLadder";
    private static final double TITLE_TOP_PADDING = 80;
    private static final int FONT_SIZE = 65;
    private static final double BOX_INSETS = 100;
    private static final double BOX_SPACING = 20;

    private final Button play = new Button(PLAY);
    private final Button instructions = new Button(INSTRUCTIONS);
    private final Button quit = new Button(QUIT);
    private final Text title = new Text(TITLE);
    private final VBox box = new VBox(play, instructions, quit);
    private static final Menu MENU_SCENE = new Menu();
    private static Stage menuStage;

    private Menu() {

        this.play.setOnAction(e -> { });

        this.instructions.setOnAction(e -> menuStage.setScene(Instructions.getScene(menuStage)));

        this.quit.setOnAction(e -> { });

        this.getDefaultLayout().setCenter(box);

        box.setAlignment(Pos.CENTER);
        box.setSpacing(BOX_SPACING);
        box.setPadding(new Insets(BOX_INSETS)); 

        this.getDefaultLayout().setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        title.setFont(new Font(FONT_SIZE));
        title.setTranslateY(TITLE_TOP_PADDING);
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
