package controller;

import java.io.IOException;

import utilities.enumeration.AudioTrack;
import utilities.enumeration.Difficulty;
import utilities.enumeration.GameMode;
import utilities.enumeration.Language;
import utilities.enumeration.Turn;
import utilities.enumeration.TypesOfDice;

/**
 *Interface of Controller.
 *
 */

public interface ViewObserver {

    /**
     * Sets the value of dice.
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
     * Starts new game.
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
     * Sets the language of game.
     * @param language
     *          the language setting
     */
    void setLanguage(Language language);

    /**
     * Starts the background music.
     * @param newSong
     *          the type of song to start.
     */
    void startMusic(AudioTrack newSong);

    /**
     * Changes of the music.
     * @param newSong
     *          the music choose by user.
     */
    void changeMusic(AudioTrack newSong);

    /**
     * Stops the background music.
     */
    void stopMusic();

    /**
     * Sets the volume of music.
     * @param volume
     *          the volume of music to set
     */
    void setVolume(float volume);

    /**
     * Manage the user's login.
     * @param userName
     *          the name of user.
     * @throws IllegalArgumentException if the argument 'name' is empty.
     * @throws IOException if an error about input/output happened.
     */
    void login(String userName) throws IllegalArgumentException, IOException;

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
     * Notify if the game is finish.
     * @param turn
     *          the turn of player that won
     * @throws IOException if an error during writing statistics by Model inside file happened.
     */
    void gameFinished(Turn turn) throws IOException;

    /**
     * Reset the statistic of game.
     * @throws IOException 
     */
    void clearStatistics() throws IOException;

    /**
     * Starts the clip of snakes or ladders.
     */
    void startClipJump();

}
