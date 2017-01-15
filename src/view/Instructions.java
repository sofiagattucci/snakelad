package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class creates and initializes the instructions scene.
 */
public final class Instructions extends BasicScene {

    private static final String BACK = "Back";
    private static final double BOX_SPACING = 30;
    private static final int FONT_SIZE = 20;

    private static Stage instrStage;
    private static Instructions instructionsScene = new Instructions();
    private final Text instr = new Text("Instructions...");
    private final Button back = new BasicButton(BACK);
    private final VBox box = new VBox(instr, back);

    private Instructions() {

        this.getDefaultLayout().setCenter(box);

        this.box.setAlignment(Pos.CENTER);
        this.box.setSpacing(BOX_SPACING);

        this.back.setOnAction(e -> instrStage.setScene(Menu.getScene(instrStage)));

        this.instr.setFont(new Font(FONT_SIZE));
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
     * @param text
     *     The text
     */
    public void setInstructions(final String text) {
        this.instr.setText(text);
    }
}
