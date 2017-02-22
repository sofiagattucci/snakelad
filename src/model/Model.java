package model;

import utilities.Pair;

/**
 * It represents the interface for the model in MVC pattern.
 * Defines the main methods of Model class.
 */
public interface Model {

    /**
     * Return a random number rolling the dice.
     * @return the number released by the dice's roll.
     */
    int getNumberFromDice();

    /**
     * Returns the current position of the specified player on the game board 
     * and a Boolean that indicates if the player has to jump or not.
     * @param playerIndex
     *          The index which identifies the player whose position is required.
     * @return a pair of values, the first is an Integer which indicates the current position of the player 
     * on the game board and the second is a Boolean that indicates if the player has to jump or not (a 
     * "jump" means the achievement of either a snake or a ladder).
     */
    Pair<Integer, Boolean> getPlayerPosition(int playerIndex);

    /**
     * Restarts the game, setting all needed in order to restart it.
     */
    void restartGame();


    /**
     * Quit the game, setting all needed in order to quit it.
     */
    void giveUpGame();

}
