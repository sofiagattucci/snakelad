package view.scenes.settings;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import view.BasicButton;
import view.LanguageStringMap;

/**
 * It manages the elements of the GUI that permit the switch the color of the pawns. 
 */
public class PawnColorSwitcherManager {

    private static final String PAWN_LABEL_KEY = "settings.pawnLabel";
    private static final int FONT_SIZE = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int MAX_PLAYERS = 6;

    private final Label pawnLabel = new Label(LanguageStringMap.get().getMap().get(PAWN_LABEL_KEY));
    private final PawnColorSwitcher singleSwitcher = new SinglePawnSwitcher();
    private final PawnColorSwitcher multiSwitcher = new MultiPawnSwitcher(MAX_PLAYERS);
    private final HBox pawnBox = new HBox(this.singleSwitcher.getParentNode(), this.multiSwitcher.getParentNode());
    private final VBox box = new VBox(this.pawnLabel, this.pawnBox);
    private final Font stdFont = new Font(FONT_SIZE);

    /**
     * Constructor of this class.
     */
    public PawnColorSwitcherManager() {

       this.box.setAlignment(Pos.CENTER);
       this.box.setSpacing(BOX_SPACING);
       this.pawnBox.setAlignment(Pos.CENTER);
       this.pawnBox.setSpacing(BOX_SPACING);
       this.pawnLabel.setFont(this.stdFont);

   }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        this.pawnLabel.setText(LanguageStringMap.get().getMap().get(PAWN_LABEL_KEY));
        this.singleSwitcher.updateLanguage();
        this.multiSwitcher.updateLanguage();
    }

    /**
     * It updates the language of the color shown in each combo box.
     */
    public void updatePrompt() {
        this.singleSwitcher.updatePrompt();
        this.multiSwitcher.updatePrompt();
    }

   /**
    * Getter of the parent node of the entire pawn color switcher.
    * @return
    *     The parent node of this class elements
    */
   public Node getParentNode() {
       return this.box;
   }
}
