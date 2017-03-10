package view.dice;

import java.util.Map;

import javafx.scene.image.Image;
import utilities.enumeration.TypesOfDice;

/**
 * Interface for a generic type of dice.
 */
public interface Dice {

    /**
     * Getter of the dice (ImageView).
     * @return
     *     The ImageView containing the dice.
     */
    Image getDiceImage();

    /**
     * Getter of the map which contains all the possible dice images (the path to each image).
     * @return
     *     The dice map
     */
    Map<Integer, String> getDiceSides();

    /**
     * It changes the dice currently used with the one specified.
     * @param newDice
     *     The new dice to use
     */
    void changeDice(TypesOfDice newDice);
}