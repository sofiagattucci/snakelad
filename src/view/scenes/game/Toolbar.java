package view.scenes.game;

import java.util.Map;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import utilities.enumeration.TypesOfDice;

/**
 * Interface for a generic tool bar of the game scene.
 */
public interface Toolbar {

    /**
     * It inserts the player' s name in the tool bar.
     * @param scene
     *     The scene where this tool bar is put
     * @param nPlayers
     *     The number of players of the game
     */
    void putLabels(Game scene, int nPlayers);

    /**
     * It updates the images (color) of the pawn shown in the tool bar.
     */
    void updateLabelsColor();


    /**
     * It updates the language of the elements of this class.
     */
    void updateLanguage();

    /**
     * Changes the turn shown in the game screen.
     * @param newTurn
     *     The new turn to set
     */
    void changeTurn(int newTurn);

    /**
     * It resets the elements of the tool bar(turn, dice).
     */
    void reset();

    /**
     * Getter of the box.
     * @return
     *     The box which contains all elements of the tool bar
     */
    VBox getBox();
 
    /**
     * Getter of the dice image view.
     * @return
     *     The dice
     */
    ImageView getDiceImView();

    /**
     * Getter of the map which contains all the possible dice images (the path to each image).
     * @return
     *     The dice map
     */
    Map<Integer, String> getDiceSides();

    /**
     * It sets the roll button enabled again after the pawn finished to move.
     */
    void endTurn();

    /**
     * It updates the dice used.
     * @param newDice
     *     The new dice to use.
     */
    void updateDice(TypesOfDice newDice);
}
