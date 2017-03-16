package controller;

/**
 * Abstract class for Song.
 *
 */
public abstract class Song {

    private static final float MAX = 0;
    private static final float MIN = -30;
    private static final float DEFAULT = -8;
    private static final float MUTE = -80;

    /**
     * Stops the music.
     */
    public abstract void stop();

    /**
     * Gets minimum volume of music.
     * @return minimum volume
     */
    public float getMinimum() {
        return MIN;
    }

    /**
     * Gets maximum volume of music.
     * @return maximum volume
     */
    public float getMaximum() {
        return MAX;
    }

    /**
     * Gets current volume of music.
     * @return current volume
     */
    public abstract float getCurrent();

    /**
     * Gets default volume of music.
     * @return default volume
     */
    public float getDefault() {
        return DEFAULT;
    }

    /**
     * Gets mute volume.
     * @return mute volume
     */
    public float getMute() {
        return MUTE;
    }

    /**
     * Sets the volume of music.
     * @param volume
     *          the volume to set
     */
    public abstract void setVolume(float volume);
}