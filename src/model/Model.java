package model;

import java.util.List;
import utilities.Pair;
import utilities.TypesOfDice;

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
     * Sets everything needed in order to start the game.
     * @param data
     *          The list that contains the data (snakes and ladders positions, 
     *          number of cells on the game board) useful to start the game.
     * @param numberOfPlayers
     *          Number of players who want to play the game.
     * @param dice
     *          The dice which players want to play with.
     */
    void startGame(List<Integer> data, int numberOfPlayers, TypesOfDice dice);

    /**
     * Returns the current position of the specified player on the game board 
     * and a Boolean that indicates if the player has to jump or not.
     * @param playerIndex
     *          The index which identifies the player whose position and jump are required.
     * @return a pair of values, the first is an Integer which indicates the current position of the player 
     * on the game board and the second is a Boolean that indicates if the player has to jump or not (a 
     * "jump" means the achievement of either a snake or a ladder).
     */
    Pair<Integer, Boolean> getPlayerPositionAndJump(int playerIndex);

    /**
     * Restarts the game, setting all needed in order to restart it.
     */
    void restartGame();


    /**
     * Quit the game, setting all needed in order to quit it.
     */
    void giveUpGame();

}
