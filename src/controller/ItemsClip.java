package controller;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


/**
 * Start the clip for items collision.
 *
 */
public class ItemsClip extends AbstractSong implements Runnable {
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
                    if (!this.clip.isOpen()) {
                        clip.open(AudioSystem.getAudioInputStream(new File(path)));
                        this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        this.setVolume(this.currentVolume);
                        clip.start();
                    }
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
        this.clip.close();
    }

    @Override
    public void stop() {
        this.clip.close();
    }

    @Override
    public float getCurrent() {
        if (this.control) {
            return this.volume.getValue();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public void setVolume(final float volume) {
        if (this.control) {
            this.volume.setValue(volume);
        } else {
            throw new IllegalStateException();
        }
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
}
