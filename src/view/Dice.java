package view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import utilities.ImageManager;

/**
 * This class represents and manages the dice shown in the tool bar. 
 */
public class Dice {

    private static final String DEFAULT_DICE = "./res/Dice/ClassicDice/DiceSide1.png";
    private static final int N_DICE_SIDES = 6;

    private final Image diceImage = ImageManager.get().readFromFile(DEFAULT_DICE);
    private final Map<Integer, String> diceSides = new HashMap<>();

    /**
     * Constructor of this class.
     */
    public Dice() {
        for (int i = 1; i <= N_DICE_SIDES; i++) {
             this.diceSides.put(i, "./res/Dice/ClassicDice/DiceSide" + i + ".png");
        }
    }

    /**
     * Getter of the dice (ImageView).
     * @return
     *     The ImageView containing the dice.
     */
    public Image getDiceImage() {
        return this.diceImage;
    }

    /**
     * Getter of the map which contains all the possible dice images (the path to each image).
     * @return
     *     The dice map
     */
    public Map<Integer, String> getDiceSides() {
        return Collections.unmodifiableMap(this.diceSides);
    }

}
