package controller;
/**
 * Interface of Song.
 *
 */
public interface Song {

    /**
     * Setter for stop field.
     * @param stop
     *          the value of stop field
     */
    void setStop(boolean stop);

    /**
     * Getter for stop field.
     * @return the stop field;
     */
    boolean isStopped();


    /**
     * Start the thread.
     */
    void start();

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
     * Set the volume of music.
     * @param volume
     *          the volume to set
     */
    void setVolume(float volume);
}