package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * This class creates and initializes the main menu scene. 
 */
public final class Menu extends Scene {

    private static final String PLAY = "PLAY";
    private static final String INSTRUCTIONS = "INSTRUCTIONS";
    private static final String QUIT = "QUIT";
    private static final String TITLE = "SnakeNLadder";
    private static final double TITLE_TOP_PADDING = 80;
    private static final int FONT_SIZE = 65;
    private static final double BOX_INSETS = 100;
    private static final double BOX_SPACING = 20;

    private final BorderPane bp = new BorderPane();
    private final Button play = new Button(PLAY);
    private final Button instructions = new Button(INSTRUCTIONS);
    private final Button quit = new Button(QUIT);
    private final Text title = new Text(TITLE);
    private final VBox box = new VBox(play, instructions, quit);

    private Menu() {
        super(new BorderPane());

        this.setRoot(this.bp);

        this.play.setOnAction(e -> { });

        this.instructions.setOnAction(e -> { });

        this.quit.setOnAction(e -> { });

        this.bp.setCenter(box);

        box.setAlignment(Pos.CENTER);
        box.setSpacing(BOX_SPACING);
        box.setPadding(new Insets(BOX_INSETS)); 

        this.bp.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        title.setFont(new Font(FONT_SIZE));
        title.setTranslateY(TITLE_TOP_PADDING);
    }
}
