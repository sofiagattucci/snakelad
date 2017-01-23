package model;

/**
 * It represents the model interface in MVC pattern, the interface of the game.
 * Defines the main methods of the Model class.
 */
public interface Game {

    /**
     * Starts the game, setting all needed in order to start it.
     */
    void startGame();


    /**
     * Return a random number rolling the dice.
     * @return the number released by the dice's roll.
     */
    int getNumberFromDice();


    /**
     * Returns the current position of the first player on the game board.
     * @return the current position of the first player on the game board.
     */
    int getPositionFirstPlayer();


    /**
     * Returns the current position of the second player on the game board.
     * @return the current position of the second player on the game board.
     */
    int getPositionSecondPlayer();


    /**
     * Restarts the game, setting all needed in order to restart it.
     */
    void restartGame();


    /**
     * Quit the game, setting all needed in order to quit it.
     */
    void giveUpGame();

}
