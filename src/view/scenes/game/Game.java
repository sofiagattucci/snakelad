package view.scenes.game;

import java.util.Map;

import utilities.enumeration.Difficulty;
import utilities.enumeration.TypesOfDice;
import utilities.enumeration.TypesOfItem;
import view.gameboard.GameBoard;
import view.item.Item;

/**
 * Interface for a generic class Game.
 */
public interface Game {

    /**
     * It resets the elements of the game scene to their default value at the beginning of each game.
     */
    void firstTurn(); 

    /**
     * It updates the game screen each turn. No jump required.
     * @param newDiceValue
     *     The new value of the dice
     */
    void updateInfo(int newDiceValue);

    /**
     * It updates the game screen each turn. Required jump.
     * @param newDiceValue
     *     The new value of the dice
     * @param finalPosition
     *     The new position after a jump due to a snake/ladder
     */
    void updateInfo(int newDiceValue, int finalPosition);

    /**
     * Updates the elements of the game scene used for the game.
     * @param newDiff
     *     The new difficulty
     * @param newDice
     *     The new dice to use
     */
    void updateScene(Difficulty newDiff, TypesOfDice newDice);

    /**
     * It updates the language of this scene.
     */
    void updateLanguage();

    /**
     * It handles the end of the game.
     */
    void gameOver();

    /**
     * It manages the end of the current turn.
     */
    void endTurn();

    /**
     * Getter of the game board used in the game.
     * @return
     *     The game board instance used in the game
     */
     GameBoard getBoard();

     /**
      * It resizes the pawns to fit the game board.
      */
     void resizePawns();

     /**
      * It puts an item in the game scene.
      * @param position
      *     The position where the item must be put
      * @param type
      *     The type of item to put in the GUI
      */
     void putItem(int position, TypesOfItem type);

     /**
      * Getter of the items (unmodifiable) list.
      * @return
      *     The (unmodifiable) list of the items shown in the scene
      */
     Map<Integer, Item> getItemMap();

     /**
      * It removes the selected Item from the GUI.
      */
     void removeItem();

}