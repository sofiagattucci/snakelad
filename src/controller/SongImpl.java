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

    private static final String PATH = "./res/Music/Snakelad.wav";
    private static final float MAX = 0;
    private static final float MIN = -30;
    private static final float CURRENT = -8;
    private Clip clip;
    private FloatControl volume;

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
    public synchronized void setStop() {
        clip.stop();
        clip.setFramePosition(0);
        clip.close();
    }


    @Override
    public synchronized void start() {
        try {
            clip.open(AudioSystem.getAudioInputStream(new File(PATH).getAbsoluteFile()));
            this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.volume.setValue(CURRENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clip.loop(1000);
    }

    @Override
    public synchronized float getMinimum() {
        return MIN;
    }

    @Override
    public synchronized float getMaximum() {
        return MAX;
    }

    @Override
    public synchronized float getCurrent() {
        return this.volume.getValue();
    }

    @Override
    public synchronized void setVolume(final float volume) {
        this.volume.setValue(volume);
    }
}
