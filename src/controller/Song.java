package controller;

import utilities.enumeration.AudioTrack;

/**
 * Interface of Song.
 *
 */
public interface Song {

    /**
     * Stop the music.
     */
    void stop();


    /**
     * Start the music.
     * @param newSong
     *          the type of song to play.
     */
    void start(AudioTrack newSong);

    /**
     * Get minimum volume of music.
     * @return minimum volume
     */
    float getMinimum();

    /**
     * Get maximum volume of music.
     * @return maximum volume
     */
    float getMaximum();

    /**
     * Get current volume of music.
     * @return current volume
     */
    float getCurrent();

    /**
     * Get default volume of music.
     * @return default volume
     */
    float getDefault();

    /**
     * Get mute volume.
     * @return mute volume
     */
    float getMute();

    /**
     * Set the volume of music.
     * @param volume
     *          the volume to set
     */
    void setVolume(float volume);
}