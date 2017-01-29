package model;

import java.util.List;

import utilities.Pair;

/**
 * It represents the model interface in MVC pattern, the interface of the game.
 * Defines the main methods of Model class.
 */
public interface Game {

    /**
     * Starts the game, setting all needed in order to start it.
     * @param listData
     *          The list that contains the data (snakes and ladders positions, number of 
     *          cells on the game board) to be passed to Model to allow starting the game.
     */
    void startGame(List<Integer> listData);


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
