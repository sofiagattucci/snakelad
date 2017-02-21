package controller;

/**
 *Interface of Controller.
 *
 */

public interface ViewObserver {

    /**
     * Set the value of dice.
     */
    void rollDice();

    /**
     * Get Text file of instruction.
     */
    void getInstructions();

    /**
     * Quit game.
     */
    void quit();

    /**
     * Reset all game.
     */
    void restart();

    /**
     * Start new game.
     * @param numberOfPlayers
     *          the number of player
     * @param scenery
     *          the scenery choose
     * @param dice
     *          the type of dice choose
     */
    void play(int numberOfPlayers, int scenery, int dice);

    /**
     * Give up the game.
     */
    void giveUp();

}
