package controller;

import java.io.IOException;

import model.Model;
import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.Language;
import utilities.enumeration.Turn;
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
     * @throws IllegalArgumentException if the argument 'name' is empty.
     * @throws IOException if an error about input/output happened.
     */
    void login(String name) throws IllegalArgumentException, IOException;

    /**
     * Notify if happen a collision between coin and pawn.
     * @param position
     *          the position of collision
     */
    void collisionHappened(int position);

    /**
     * Finds user's statistics and returns it by means of a view's method.
     */
    void statistics();

    /**
     * ...TODO.
     * @param turn
     *          ...TODO.
     * @throws IOException if an error during writing statistics by Model inside file happened.
     */
    void gameFinished(Turn turn) throws IOException;

}
