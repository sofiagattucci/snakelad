package view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import utilities.ImageManager;

/**
 * This class represents and manages the dice shown in the tool bar. 
 */
public class DiceImpl implements Dice {

    private static final String DEFAULT_DICE = "./res/Dice/ClassicDice/DiceSide1.png";
    private static final int N_DICE_SIDES = 6;

    private final Image diceImage = ImageManager.get().readFromFile(DEFAULT_DICE);
    private final Map<Integer, String> diceSides = new HashMap<>();

    /**
     * Constructor of this class.
     */
    public DiceImpl() {
        for (int i = 1; i <= N_DICE_SIDES; i++) {
             this.diceSides.put(i, "./res/Dice/ClassicDice/DiceSide" + i + ".png");
        }
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
