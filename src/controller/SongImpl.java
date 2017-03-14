package controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import utilities.enumeration.AudioTrack;

/**
 * Implementation of Song interface.
 *
 */
public class SongImpl implements Song {

    private static final String DEFAULT_SONG = "./res/music/Snakelad.wav";
    private static final String SECOND = "./res/music/ID(unmixed).wav";
    private static final float MAX = 0;
    private static final float MIN = -30;
    private static final float CURRENT = -8;
    private Clip clip;
    private FloatControl volume;
    private boolean control;

    private void chooseSong(final String path) {
        try {
            clip.open(AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile()));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        this.volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        this.volume.setValue(CURRENT);
    }

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
    public void stop() {
        if (this.control) {
            clip.stop();
            clip.setFramePosition(0);
            clip.close();
        } else {
            throw new IllegalStateException();
        }
    }


    @Override
    public void start(final AudioTrack newSong) {
        try {
            switch (newSong) {
            case SNAKELAD:
                this.chooseSong(DEFAULT_SONG);
                break;
            case ID:
                this.chooseSong(SECOND);
                break;
                default:
                    this.chooseSong(DEFAULT_SONG);
            }
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
