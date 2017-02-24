package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import utilities.ImageManager;
import utilities.TypesOfDice;
import view.dice.DiceTypes;
import view.scenes.SetUpGame;

/**
 * This class manages the choice of the dice and its elements in the GUI.
 * It implemented by using a circular list defined in the extended class CircularList.
 */
public class DiceCircularList extends ImagesCircularList<TypesOfDice> {

    private static final String CLASSIC_DICE_KEY = "dice.classic";
    private static final String TO10_DICE_KEY = "dice.to10";
    private static final String NEGATIVE_DICE_KEY = "dice.negative";
    private static final String DICE_LABEL_KEY = "setUp.selectDice";
    private static final int NUM_DICE = 3;
    private static final double DICE_SIZE = BasicButton.getButtonHeight() * 1.5;
    private static final int DEFAULT_DICE_IMAGE_INDEX = 5;

    private final Node nextNode;

    /**
     * Constructor of this class.
     * @param next
     *     The next node of the layout graph to show in the GUI
     */
    public DiceCircularList(final Node next) {
        super(NUM_DICE, DICE_LABEL_KEY, DICE_SIZE, TypesOfDice.CLASSIC_DICE);
        this.nextNode = next;
    }

    @Override
    protected void updateDescLabel(final int n) {
        switch(n) {
            case 0: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(CLASSIC_DICE_KEY)); 
                    break;
            case 1: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(TO10_DICE_KEY));
                    break;
            case 2: this.getDescLabel().setText(LanguageStringMap.get().getMap().get(NEGATIVE_DICE_KEY));
                    break;
            default:
        }
    }

    private TypesOfDice calculateDice(final int n) {
        switch(n) {
            case 0: return TypesOfDice.CLASSIC_DICE; 
            case 1: return TypesOfDice._5_TO_10_DICE;
            case 2: return TypesOfDice.NEGATIVE_DICE;
            default: return TypesOfDice.CLASSIC_DICE;
        }
    }

   @Override
   protected void setParameter(final int n) {
       switch(n) {
           case 0: this.setParameterValue(TypesOfDice.CLASSIC_DICE);
                   break;
           case 1: this.setParameterValue(TypesOfDice._5_TO_10_DICE);
                   break;
           case 2: this.setParameterValue(TypesOfDice.NEGATIVE_DICE);
                   break;
           default:
       }
       SetUpGame.setDiceType(this.getParameterValue());
   }

    @Override
    protected Image getImage() {
        return ImageManager.get().readFromFile(
                DiceTypes.get().getSpecificDiceMap(this.calculateDice(this.getCounter())).get(DEFAULT_DICE_IMAGE_INDEX));
    }

    @Override
    protected Node nextToShow() {
        return this.nextNode;
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        super.updateLanguage(DICE_LABEL_KEY);
    }
}
