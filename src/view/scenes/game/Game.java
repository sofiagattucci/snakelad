package view.scenes.game;

import utilities.Difficulty;
import utilities.TypesOfDice;
import view.gameboard.GameBoard;

/**
 * Interface for a generic class Game.
 */
public interface Game {

    /**
     * It resets the displayed values at the beginning of each game. Indeed at the beginning 
     * there is no value shown in the GUI for the dice value. 
     */
    void firstTurn(); 

    /**
     * It updates the game screen each turn.
     * @param newDiceValue
     *     The new value of the dice
     */
    void updateInfo(int newDiceValue);

    /**
     * It updates the game screen each turn.
     * @param newDiceValue
     *     The new value of the dice
     * @param finalPosition
     *     The new position after a jump due to a snake/ladder
     */
    void updateInfo(int newDiceValue, int finalPosition);

    /**
     * Updates the scenery and the dice used for the game.
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
     * It holds the number of players in the game. At this time we don' t know the number so an abstract method is needed.
     * @return
     *     The number of players in the game
     */
    int getTag();

    /**
     * It handles the end of the game.
     */
    void gameOver();

    /**
     * It manages the end of the turn, so it updates some informations.
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
}