package controller;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


/**
 * Start the clip for items collision.
 *
 */
public class ItemsClip implements Runnable {
    private static final float MAX = 0;
    private static final float MIN = -30;
//    private static final float CURRENT = -8;
    private Clip clip;
    private String path;
    private final Thread t;
    private float currentVolume;
    private FloatControl volume;
    private boolean control;

    /**
     * Constructor.
     */
    public ItemsClip() {
        this.t = new Thread(this);
        this.t.setDaemon(true);
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (this.clip.getFramePosition() == this.clip.getFrameLength()) {
            synchronized (clip) {
                try {
                    clip.open(AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()));
                    this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    this.setVolume(this.currentVolume);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
                    try {
                        Thread.sleep(this.clip.getMicrosecondLength());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
        }
        System.out.println("Finito while");
        this.clip.close();
    }


    /**
     * Start the clip.
     * @param path
     *          the path of clip to open
     * @param volume
     *          the current volume of music
     */
    public synchronized void start(final String path, final float volume) {
        this.currentVolume = volume;
        this.path = path;
        this.control = true;
        this.t.start();
    }

    /**
     * Stop the clip.
     */
    public void stop() {
        this.clip.close();
//        this.stop = true;
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
