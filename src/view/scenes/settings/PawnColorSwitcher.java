package view.scenes.settings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import utilities.Pair;
import view.BasicButton;
import view.LanguageStringMap;
import view.pawn.AvailableColor;


/**
 * It manages the elements of the GUI that permit the switch the color of the pawns. 
 */
public abstract class PawnColorSwitcher {

    private static final String PLAYER_KEY = "game.player";
    private static final int FONT_SIZE = 20;
    private static final double COMBO_BOX_GAP = BasicButton.getButtonHeight() / 6;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final AvailableColor DEFAULT_COLOR = AvailableColor.RED;

    private final Label title = new Label();
    private final List<Pair<Label, ComboBox<String>>> pawnList = new ArrayList<>();
    private final GridPane grid = new GridPane();
    private final VBox box = new VBox(this.title, this.grid);
    private final Font stdFont = new Font(FONT_SIZE);
    private final List<AvailableColor> prevColor = new ArrayList<>();
    private final int nPlayers;
    private final String titleKey;

    /**
     * Constructor of this class.
     * @param title
     *     The title to put in the title label
     * @param nPlayers
     *     The number of players to manage
     */
    public PawnColorSwitcher(final String title, final int nPlayers) {

        this.grid.setHgap(COMBO_BOX_GAP);
        this.grid.setVgap(COMBO_BOX_GAP);
        this.box.setSpacing(BOX_SPACING);
        this.titleKey = title;
        this.title.setText(LanguageStringMap.get().getMap().get(title));
        this.title.setFont(this.stdFont);
        this.nPlayers = nPlayers;

        IntStream.range(0, nPlayers)
                 .forEach(i -> {
                     this.pawnList.add(new Pair<>(new Label(LanguageStringMap.get().getMap().get(PLAYER_KEY) + (i + 1)),
                             new ComboBox<String>()));
                     this.pawnList.get(i).getFirst().setFont(this.stdFont);
                     final int j = i;
                     this.pawnList.get(i).getSecond().setOnAction(e -> {
                        this.switchColor(j, this.findColor(this.pawnList.get(j).getSecond().getValue()));
                     });
                     this.pawnList.get(i).getSecond().setValue(
                             LanguageStringMap.get().getMap().get(this.getColorKey(i).toString()));
                     this.prevColor.add(this.getColorKey(i));
                     for (final AvailableColor c: AvailableColor.values()) {
                         this.pawnList.get(i).getSecond().getItems().add(LanguageStringMap.get().getMap().get(c.toString()));
                     }
                     this.grid.add(this.pawnList.get(i).getFirst(), i, 0);
                     this.grid.add(this.pawnList.get(i).getSecond(), i, 1);
                 });
        for (final Pair<Label, ComboBox<String>> elem : this.pawnList) {
            elem.getSecond().setId("ComboBox");
        }
    }

    /**
     * It finds the color (element of enumeration AvailableColor) form its name in the current language.
     * @param s
     *     The name of the color
     * @return
     *     The element of the enumeration AvailableColor linked to the selected color
     */
    protected AvailableColor findColor(final String s) {
        for (final AvailableColor c: AvailableColor.values()) {
            if (LanguageStringMap.get().getMap().get(c.toString()).equals(s)) {
                 return c;
             }
         }
         return DEFAULT_COLOR;
     }

    /**
     * Getter of the color of a pawn.
     * @param index
     *     The pawn index 
     * @return
     *     The color active for the selected pawn. Actually an element of enumeration AvailableColor.
     */
    protected abstract AvailableColor getColorKey(int index);

    /**
     * It switches of the color of a pawn.
     * @param index
     *     The pawn index
     * @param c
     *     The new color to put
     */
    protected abstract void switchColor(int index, AvailableColor c);

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {

        this.title.setText(LanguageStringMap.get().getMap().get(this.titleKey));

        IntStream.range(1, this.nPlayers + 1)
                 .forEach(i -> {
                     this.pawnList.get(i - 1).getFirst().setText(LanguageStringMap.get().getMap().get(PLAYER_KEY) + i);
                     this.pawnList.get(i - 1).getSecond().setValue((LanguageStringMap.get().getMap().get(
                             this.getColorKey(i - 1).toString())));
                 });

        for (final Pair<Label, ComboBox<String>> elem: this.pawnList) {
            elem.getSecond().getItems().clear();
        }

        for (final AvailableColor c: AvailableColor.values()) {
                for (final Pair<Label, ComboBox<String>> elem: this.pawnList) {
                    elem.getSecond().getItems().add(LanguageStringMap.get().getMap().get(c.toString()));
            }
        }

        IntStream.range(0, this.nPlayers)
                 .forEach(i -> {
                     this.pawnList.get(i).getSecond().setValue(LanguageStringMap.get().getMap().get(this.prevColor.get(i).toString()));
                 });
    }

    /**
     * It updates the language of the color shown in each combo box.
     */
    public void updatePrompt() {
        IntStream.range(0, this.nPlayers)
        .forEach(i -> {
            this.prevColor.set(i, this.findColor(this.pawnList.get(i).getSecond().getValue()));
        });
    }

   /**
    * Getter of the parent node of the entire pawn color switcher.
    * @return
    *     The parent node of this class elements
    */
   public Node getParentNode() {
       return this.box;
   }

   /**
    * Getter if the list of the elements linked to each pawn (its label and combo box).
    * @return
    *     The (unmodifiable) list of the elements linked to each pawn, a pair Label/ComboxBox
    */
   public List<Pair<Label, ComboBox<String>>> getPawnList() {
       return Collections.unmodifiableList(this.pawnList);
   }
}
