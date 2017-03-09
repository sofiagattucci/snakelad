package controller;

import model.Model;
import utilities.Difficulty;
import utilities.Language;
import utilities.TypesOfDice;
import view.View;

/**
 *Interface of Controller.
 *
 */

public interface ViewObserver {

    /**
     * Getter for instance of Model.
     * @return the instance of Model
     */
    Model getGame();

    /**
     * Getter for instance of View.
     * @return the instance of View
     */
    View getView();

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

    /**
     * Start the background music.
     */
    void startMusic();

    /**
     * Stop the background music.
     */
    void stopMusic();

    /**
     * Set the volume of music.
     * @param volume
     *          the volume of music to set
     */
    void setVolume(float volume);

    /**
     * Manage the user's login.
     * @param name
     *          the name of user.
     */
    void login(String name);

}
