package view.dice;

import java.util.Collections;
import java.util.Map;

import javafx.scene.image.Image;
import model.TypesOfDice;
import utilities.ImageManager;
import view.scenes.setup.SetUpGame;

/**
 * This class represents and manages the dice shown in the tool bar. 
 */
public class DiceImpl implements Dice {

    private static final String DEFAULT_DICE = "./res/Dice/ClassicDice/DiceSide1.png";

    private final Image diceImage = ImageManager.get().readFromFile(DEFAULT_DICE);
    private Map<Integer, String> diceSides;

    /**
     * Constructor of this class.
     */
    public DiceImpl() {
        this.diceSides = DiceTypes.get().getSpecificDiceMap(SetUpGame.getSelectedDice());
    }

    @Override
    public Image getDiceImage() {
        return this.diceImage;
    }

    @Override
    public Map<Integer, String> getDiceSides() {
        return Collections.unmodifiableMap(this.diceSides);
    }

    @Override
    public void changeDice(final TypesOfDice newDice) {
        this.diceSides = DiceTypes.get().getSpecificDiceMap(newDice);
    }
}
