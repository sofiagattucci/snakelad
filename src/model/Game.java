package model;

/**
 * It represents the Model interface in MVC pattern, the interface of the game.
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
     * Restarts the game, setting all needed in order to restart it.
     */
    void restartGame();

    /**
     * Quit the game, setting all needed in order to quit it.
     */
    void giveUpGame();

}
