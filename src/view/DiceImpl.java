package view;

import java.util.Collections;
import java.util.Map;

import javafx.scene.image.Image;
import utilities.ImageManager;
import utilities.TypesOfDice;
import view.scenes.SetUpGame;

/**
 * This class represents and manages the dice shown in the tool bar. 
 */
public class DiceImpl implements Dice {

    private static final String DEFAULT_DICE = "./res/Dice/ClassicDice/DiceSide1.png";

    private final Image diceImage = ImageManager.get().readFromFile(DEFAULT_DICE);
    private final Map<Integer, String> diceSides;

    /**
     * Constructor of this class.
     */
    public DiceImpl() {
        final TypesOfDice type = SetUpGame.getSelectedDice();
        this.diceSides = DiceTypes.get().getSpecificDiceMap(type);

    }

    @Override
    public Image getDiceImage() {
        return this.diceImage;
    }

    @Override
    public Map<Integer, String> getDiceSides() {
        return Collections.unmodifiableMap(this.diceSides);
    }

}
