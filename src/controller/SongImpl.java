package controller;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Implementation of Song interface.
 *
 */
public class SongImpl implements Song {

    private static final String PATH = "./res/music/Snakelad.wav";
    private static final float MAX = 0;
    private static final float MIN = -30;
    private static final float CURRENT = -8;
    private Clip clip;
    private FloatControl volume;
    private boolean control;

    /**
     * Constructor. 
     */
    public SongImpl() {
        try {
            clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setStop() {
        if (this.control) {
            clip.stop();
            clip.setFramePosition(0);
            clip.close();
        } else {
            throw new IllegalStateException();
        }
    }


    @Override
    public void start() {
        try {
            clip.open(AudioSystem.getAudioInputStream(new File(PATH).getAbsoluteFile()));
            this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.volume.setValue(CURRENT);
            this.control = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.loop(1000);
    }

    @Override
    public float getMinimum() {
        if (this.control) {
            return MIN;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public float getMaximum() {
        if (this.control) {
            return MAX;
        } else {
            throw new IllegalStateException();
        }
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
}
