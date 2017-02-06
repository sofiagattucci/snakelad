package view;

import java.util.Map;

import javafx.scene.image.Image;

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

}