package controller;

import model.Model;
import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.Language;
import utilities.enumeration.TypesOfDice;
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
     * Manage the game pause.
     */
    void pause();

    /**
     * Resume the game.
     */
    void resume();

    /**
     * Start new game.
     * @param numberOfPlayers
     *          the number of player
     * @param scenery
     *          the scenery choose
     * @param dice
     *          the type of dice choose
     * @param modality
     *          the mode of game choose
     */
    void play(int numberOfPlayers, Difficulty scenery, TypesOfDice dice, GameMode modality);

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

    /**
     * Notify if happen a collision between coin and pawn.
     */
    void collisionHappened();

}
