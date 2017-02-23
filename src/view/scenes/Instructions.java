package view.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import view.BasicButton;
import view.LanguageStringMap;

/**
 * This class creates and initializes the instructions scene.
 */
public final class Instructions extends BasicScene {

    private static final String BACK = LanguageStringMap.get().getMap().get("back");
    private static final String TITLE = LanguageStringMap.get().getMap().get("instructions.title");
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 2;
    private static final int FONT_SIZE = 20;
    private static final int TITLE_FONT_SIZE = 60;

    private static Stage instrStage;
    private static Instructions instructionsScene = new Instructions();
    private final Text instr = new Text();
    private final Label title = new Label(TITLE);
    private final Button back = new BasicButton(BACK);
    private final VBox box = new VBox(title, instr, back);

    private Instructions() {

        this.getDefaultLayout().setCenter(box);

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);

        this.back.setOnAction(e -> instrStage.setScene(Menu.getScene(instrStage)));

        this.instr.setFont(new Font(FONT_SIZE));
        this.instr.setTextAlignment(TextAlignment.CENTER);

        this.title.setFont(new Font(TITLE_FONT_SIZE));
    }

    /**
     * Getter of this scene.
     * @param stage
     *     The stage that will be linked to the one of this class
     * @return
     *     The instructions scene
     */
    public static Instructions getScene(final Stage stage) {
        instrStage = stage;
        return instructionsScene;
    }

    /**
     * It sets the instructions' text displayed on the GUI.
     */
    public void setInstructions() {
        this.title.setText(LanguageStringMap.get().getMap().get("instructions.title"));
        this.instr.setText(LanguageStringMap.get().getMap().get("instructions"));
        this.back.setText(LanguageStringMap.get().getMap().get("back"));
    }
}
