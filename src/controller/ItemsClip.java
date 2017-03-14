package controller;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


/**
 * Start the clip for items collision.
 *
 */
public class ItemsClip {
    private static final float MAX = 0;
    private static final float MIN = -30;
    private static final float CURRENT = -8;
    private Clip clip;
    private FloatControl volume;
    private boolean control;

    /**
     * Constructor.
     */
    public ItemsClip() {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Start the clip.
     * @param path
     *          the path of clip to open
     */
    public void start(final String path) {
        try {
            clip.open(AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()));
            this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.volume.setValue(CURRENT);
            clip.start();
            this.control = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the clip.
     */
    public void stop() {
        if (this.clip.getMicrosecondPosition() == this.clip.getMicrosecondLength()) {
            this.clip.close();
        }
    }
    /**
     * Getter for minimum volume.
     * @return the minimum volume
     */
    public float getMinimum() {
        if (this.control) {
            return MIN;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Getter for maximum volume.
     * @return the maximum volume
     */
    public float getMaximum() {
        if (this.control) {
            return MAX;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Getter for current volume.
     * @return the current volume
     */
    public float getCurrent() {
        if (this.control) {
            return this.volume.getValue();
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Sets the volume.
     * @param volume
     *          the volume to set
     */
    public void setVolume(final float volume) {
        if (this.control) {
            this.volume.setValue(volume);
        } else {
            throw new IllegalStateException();
        }
    }

}
