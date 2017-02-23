package controller;

import utilities.Difficulty;
import utilities.Language;
import utilities.TypesOfDice;

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
    void play(int numberOfPlayers, Difficulty scenery, TypesOfDice dice);

    /**
     * Give up the game.
     */
    void giveUp();

    /**
     * Set the language of game.
     * @param language
     *          the language setting
     */
    void setLanguage(Language language);

}
