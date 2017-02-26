package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import utilities.Pair;
import view.pawn.AvailableColor;
import view.pawn.PawnsColor;

/**
 * It manages the elements of the GUI that permit the switch the color of the pawns. 
 */
public class PawnColorSwitcher {

    private static final String PAWN_LABEL_KEY = "settings.pawnLabel";
    private static final String PLAYER_KEY = "game.player";
    private static final String SINGLE_KEY = "setUp.single";
    private static final String MULTI_KEY = "setUp.multi";
    private static final String CPU = "CPU";
    private static final double COMBO_BOX_GAP = BasicButton.getButtonHeight() / 6;
    private static final int FONT_SIZE = 20;
    private static final double BOX_SPACING = BasicButton.getButtonHeight() / 3;
    private static final int MAX_PLAYERS = 6;
    private static final AvailableColor DEFAULT_COLOR = AvailableColor.RED;

    private final Label pawnLabel = new Label(LanguageStringMap.get().getMap().get(PAWN_LABEL_KEY));
    private final Label singleLabel = new Label(LanguageStringMap.get().getMap().get(SINGLE_KEY));
    private final Label multiLabel = new Label(LanguageStringMap.get().getMap().get(MULTI_KEY));
    private final List<Pair<Label, ComboBox<String>>> singlePawnList = new ArrayList<>();
    private final List<Pair<Label, ComboBox<String>>> multiPawnList = new ArrayList<>();
    private final GridPane singleGrid = new GridPane();
    private final GridPane multiGrid = new GridPane();
    private final VBox singleBox = new VBox(this.singleLabel, this.singleGrid);
    private final VBox multiBox = new VBox(this.multiLabel, this.multiGrid);
    private final HBox pawnBox = new HBox(this.singleBox, this.multiBox);
    private final VBox box = new VBox(this.pawnLabel, this.pawnBox);
    private final Font stdFont = new Font(FONT_SIZE);

    /**
     * Constructor of this class.
     */
   public PawnColorSwitcher() {

       this.singleGrid.setHgap(COMBO_BOX_GAP);
       this.singleGrid.setVgap(COMBO_BOX_GAP);
       this.multiGrid.setHgap(COMBO_BOX_GAP);
       this.multiGrid.setVgap(COMBO_BOX_GAP);
       this.box.setAlignment(Pos.CENTER);
       this.box.setSpacing(BOX_SPACING);
       this.singleBox.setSpacing(BOX_SPACING);
       this.multiBox.setSpacing(BOX_SPACING);
       this.pawnBox.setAlignment(Pos.CENTER);
       this.pawnBox.setSpacing(BOX_SPACING);
       this.pawnLabel.setFont(this.stdFont);
       this.singleLabel.setFont(this.stdFont);
       this.multiLabel.setFont(this.stdFont);

       this.singlePawnList.addAll(Arrays.asList(new Pair<>(new Label(LanguageStringMap.get().getMap().get(PLAYER_KEY)), 
               new ComboBox<String>()), new Pair<>(new Label(CPU), new ComboBox<String>())));

       for (int i = 1; i <= 2; i++) {
           this.singlePawnList.get(i - 1).getFirst().setFont(this.stdFont);
           final int j = i;
           final AvailableColor oldColor = PawnsColor.get().getSingleColor(j - 1);
           this.singlePawnList.get(i - 1).getSecond().setOnAction(e -> {
               PawnsColor.get().switchColorSingle(j - 1, this.findColor(this.singlePawnList.get(j - 1).getSecond().getValue()));
               this.updateSingleComboElem(j - 1, this.findColor(this.singlePawnList.get(j - 1).getSecond().getValue()), oldColor);
           });

           this.singlePawnList.get(i - 1).getSecond().setPromptText(
                   LanguageStringMap.get().getMap().get(PawnsColor.get().getSingleColor(i - 1).toString()));
       }

       for (int i = 0; i < 2; i++) {
           this.singleGrid.addRow(i, this.singlePawnList.get(i).getFirst(), this.singlePawnList.get(i).getSecond());
           this.singlePawnList.get(i).getSecond().setPromptText(
                   LanguageStringMap.get().getMap().get(PawnsColor.get().getSingleColor(i).toString()));
       }

       for (int i = 1; i <= MAX_PLAYERS; i++) {
           this.multiPawnList.add(new Pair<>(new Label(LanguageStringMap.get().getMap().get(PLAYER_KEY) + i),
               new ComboBox<String>()));
           this.multiPawnList.get(i - 1).getFirst().setFont(this.stdFont);
           final int j = i;
           final AvailableColor oldColor = PawnsColor.get().getMultiColor(j - 1);
           this.multiPawnList.get(i - 1).getSecond().setOnAction(e -> {
               PawnsColor.get().switchColorMulti(j - 1, this.findColor(this.multiPawnList.get(j - 1).getSecond().getValue()));
               this.updateMultiComboElem(j - 1, this.findColor(this.multiPawnList.get(j - 1).getSecond().getValue()), oldColor);
           });
           this.multiPawnList.get(i - 1).getSecond().setPromptText(
                   LanguageStringMap.get().getMap().get(PawnsColor.get().getMultiColor(i - 1).toString()));
       }

       for (int i = 0; i < MAX_PLAYERS / 2; i++) {  //N.B: MAX_PLAYERS is even
           this.multiGrid.addRow(i, this.multiPawnList.get(i).getFirst(), this.multiPawnList.get(i).getSecond(),
                   this.multiPawnList.get(MAX_PLAYERS / 2 + i).getFirst(), this.multiPawnList.get(MAX_PLAYERS / 2 + i).getSecond());
       }

       for (final AvailableColor c: AvailableColor.values()) {
           if (!PawnsColor.get().getMultiList().contains(c)) {
               for (final Pair<Label, ComboBox<String>> elem: this.multiPawnList) {
                   elem.getSecond().getItems().add(LanguageStringMap.get().getMap().get(c.toString()));
               }
           }
           if (!PawnsColor.get().getSingleList().contains(c)) {
               for (final Pair<Label, ComboBox<String>> elem: this.singlePawnList) {
                   elem.getSecond().getItems().add(LanguageStringMap.get().getMap().get(c.toString()));
               }
           }
       }
   }

   private AvailableColor findColor(final String s) {
       for (final AvailableColor c: AvailableColor.values()) {
           if (LanguageStringMap.get().getMap().get(c.toString()).equals(s)) {
                return c;
            }
        }
        return DEFAULT_COLOR;
    }

   private void updateSingleComboElem(final int index, final AvailableColor newColor, final AvailableColor oldColor) {
       for (int i = 0; i < 2; i++) {
           if (i != index) {
               this.singlePawnList.get(i).getSecond().getItems().remove(LanguageStringMap.get().getMap().get(newColor.toString()));
               this.singlePawnList.get(i).getSecond().getItems().add(LanguageStringMap.get().getMap().get(oldColor.toString()));
           }
       }
   }

   private void updateMultiComboElem(final int index, final AvailableColor newColor, final AvailableColor oldColor) {
       for (int i = 0; i < MAX_PLAYERS; i++) {
           if (i != index) {
               this.multiPawnList.get(i).getSecond().getItems().remove(LanguageStringMap.get().getMap().get(newColor.toString()));
               this.multiPawnList.get(i).getSecond().getItems().add(LanguageStringMap.get().getMap().get(oldColor.toString()));
           }
       }
   }

   /**
    * It updates the language of the elements of this class.
    */
   public void updateLanguage() {
       this.pawnLabel.setText(LanguageStringMap.get().getMap().get(PAWN_LABEL_KEY));
       this.singleLabel.setText(LanguageStringMap.get().getMap().get(SINGLE_KEY));
       this.multiLabel.setText(LanguageStringMap.get().getMap().get(MULTI_KEY));

       final List<String> newItems = new ArrayList<>();
       for (final AvailableColor c: AvailableColor.values()) {
           newItems.add(LanguageStringMap.get().getMap().get(c.toString()));
       }
       for (int i = 1; i <= MAX_PLAYERS; i++) {
           this.multiPawnList.get(i - 1).getFirst().setText(LanguageStringMap.get().getMap().get(PLAYER_KEY) + i);
           this.multiPawnList.get(i - 1).getSecond().setValue((LanguageStringMap.get().getMap().get(
                   PawnsColor.get().getMultiColor(i - 1).toString())));
           this.multiPawnList.get(i - 1).getSecond().getItems().setAll(newItems);
       }
       this.singlePawnList.get(0).getFirst().setText((LanguageStringMap.get().getMap().get(PLAYER_KEY)));
       for (int i = 1; i <= 2; i++) {
           this.singlePawnList.get(i - 1).getSecond().setValue((LanguageStringMap.get().getMap().get(
                   PawnsColor.get().getSingleColor(i - 1).toString())));
           this.singlePawnList.get(i - 1).getSecond().getItems().setAll(newItems);
       }
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
