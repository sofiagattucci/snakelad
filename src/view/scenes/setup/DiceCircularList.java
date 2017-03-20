package view.scenes.setup;

import javafx.scene.Node;
import javafx.scene.image.Image;
import utilities.ImageManager;
import utilities.enumeration.TypesOfDice;
import view.BasicButton;
import view.LanguageStringMap;
import view.dice.DiceTypes;

/**
 * This class manages the choice of the dice and its elements in the GUI.
 * It implemented by using a circular list defined in the extended class ImagesCircularList.
 */
public class DiceCircularList extends ImagesCircularList<TypesOfDice> {

    private static final String CLASSIC_DICE_KEY = "dice.classic";
    private static final String TO10_DICE_KEY = "dice.to10";
    private static final String NEGATIVE_DICE_KEY = "dice.negative";
    private static final String DICE_LABEL_KEY = "setUp.selectDice";
    private static final double DICE_SIZE = BasicButton.getButtonHeight() * 1.5;
    private static final int CLASSIC_DICE_IMAGE_INDEX = 6;
    private static final int TO10_DICE_IMAGE_INDEX = 10;
    private static final int NEGATIVE_DICE_IMAGE_INDEX = -2;

    /**
     * Constructor of this class.
     * @param next
     *     The next node of the layout graph to show in the GUI
     */
    public DiceCircularList(final Node next) {
        super(TypesOfDice.values().length, DICE_LABEL_KEY, DICE_SIZE, TypesOfDice.CLASSIC_DICE, next);
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
        int diceImageIndex = CLASSIC_DICE_IMAGE_INDEX;
        if (this.getParameterValue() == TypesOfDice._5_TO_10_DICE) {
            diceImageIndex = TO10_DICE_IMAGE_INDEX;
        }
        if (this.getParameterValue() == TypesOfDice.NEGATIVE_DICE) {
            diceImageIndex = NEGATIVE_DICE_IMAGE_INDEX;
        }
        return ImageManager.get().readFromFile(
                DiceTypes.get().getSpecificDiceMap(this.calculateDice(this.getCounter())).get(diceImageIndex));
    }

    /**
     * It updates the language of the elements of this class.
     */
    public void updateLanguage() {
        super.updateLanguage(DICE_LABEL_KEY);
    }
}
